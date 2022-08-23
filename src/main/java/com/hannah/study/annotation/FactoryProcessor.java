package com.hannah.study.annotation;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Factory注解处理器
 * @see <a href="http://hannesdorfmann.com/annotation-processing/annotationprocessing101/">annotationprocessing101</a>
 */
@AutoService(Processor.class)
public class FactoryProcessor extends AbstractProcessor {

    private Types typeUtils;
    private Elements elementUtils;
    private Filer filer;
    private Messager messager;

    private Map<String, FactoryGroupedClasses> factoryClassesMap = new LinkedHashMap<String, FactoryGroupedClasses>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        typeUtils = processingEnv.getTypeUtils();
        elementUtils = processingEnv.getElementUtils();
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(Factory.class.getCanonicalName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Element element = null;
        try {
            for (Element annotatedElement : roundEnv.getElementsAnnotatedWith(Factory.class)) {
                element = annotatedElement;

                // check Element Kind
                if (annotatedElement.getKind() != ElementKind.CLASS) {
                    throw new UnsupportedOperationException("Only classes can be annotated with @" + Factory.class.getSimpleName());
                }

                TypeElement typeElement = (TypeElement) annotatedElement;
                FactoryAnnotatedClass annotatedClass = new FactoryAnnotatedClass(typeElement);

                checkValidClass(annotatedClass);

                String qualifiedGroupName = annotatedClass.getQualifiedGroupName();
                FactoryGroupedClasses factoryGroupedClasses = factoryClassesMap.get(qualifiedGroupName);
                if (factoryGroupedClasses == null) {
                    factoryGroupedClasses = new FactoryGroupedClasses(qualifiedGroupName);
                    factoryClassesMap.put(qualifiedGroupName, factoryGroupedClasses);
                }
                factoryGroupedClasses.add(annotatedClass);

                // generate code
                for (FactoryGroupedClasses groupedClasses : factoryClassesMap.values()) {
                    groupedClasses.generateCode(elementUtils, filer);
                }
                factoryClassesMap.clear();
            }
        } catch (Exception e) {
            messager.printMessage(Diagnostic.Kind.ERROR, e.getMessage(), element);
        }
        return false;
    }

    /**
     * Checks if the annotated element observes our rules
     */
    private void checkValidClass(FactoryAnnotatedClass item) {

        // Cast to TypeElement, has more type specific methods
        TypeElement classElement = item.getTypeElement();

        if (!classElement.getModifiers().contains(Modifier.PUBLIC)) {
            throw new UnsupportedOperationException(String.format("The class %s is not public.",
                    classElement.getQualifiedName().toString()));
        }

        // Check if it's an abstract class
        if (classElement.getModifiers().contains(Modifier.ABSTRACT)) {
            throw new UnsupportedOperationException(String.format(
                    "The class %s is abstract. You can't annotate abstract classes with @%",
                    classElement.getQualifiedName().toString(), Factory.class.getSimpleName()));
        }

        // Check inheritance: Class must be childclass as specified in @Factory.type();
        TypeElement superClassElement = elementUtils.getTypeElement(item.getQualifiedGroupName());
        if (superClassElement.getKind() == ElementKind.INTERFACE) {
            // Check interface implemented
            if (!classElement.getInterfaces().contains(superClassElement.asType())) {
                throw new UnsupportedOperationException(String.format(
                        "The class %s annotated with @%s must implement the interface %s",
                        classElement.getQualifiedName().toString(), Factory.class.getSimpleName(), item.getQualifiedGroupName()));
            }
        } else {
            // Check subclassing
            TypeElement currentClass = classElement;
            while (true) {
                TypeMirror superClassType = currentClass.getSuperclass();

                if (superClassType.getKind() == TypeKind.NONE) {
                    // Basis class (java.lang.Object) reached, so exit
                    throw new UnsupportedOperationException(String.format(
                            "The class %s annotated with @%s must inherit from %s",
                            classElement.getQualifiedName().toString(), Factory.class.getSimpleName(), item.getQualifiedGroupName()));
                }

                if (superClassType.toString().equals(item.getQualifiedGroupName())) {
                    // Required super class found
                    break;
                }

                // Moving up in inheritance tree
                currentClass = (TypeElement) typeUtils.asElement(superClassType);
            }
        }

        // Check if an empty public constructor is given
        for (Element enclosed : classElement.getEnclosedElements()) {
            if (enclosed.getKind() == ElementKind.CONSTRUCTOR) {
                ExecutableElement constructorElement = (ExecutableElement) enclosed;
                if (constructorElement.getParameters().size() == 0
                        && constructorElement.getModifiers().contains(Modifier.PUBLIC)) {
                    // Found an empty constructor
                    return;
                }
            }
        }

        // No empty constructor found
        throw new UnsupportedOperationException(String.format(
                "The class %s must provide an public empty default constructor",
                classElement.getQualifiedName().toString()));
    }

}
