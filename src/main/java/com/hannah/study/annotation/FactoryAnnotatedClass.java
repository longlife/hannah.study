package com.hannah.study.annotation;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;

/**
 * 工厂注解类
 */
public class FactoryAnnotatedClass {

    private TypeElement typeElement;

    private String id;
    private String qualifiedGroupName;
    private String simpleGroupName;

    public FactoryAnnotatedClass(TypeElement typeElement) {
        this.typeElement = typeElement;

        Factory annotation = typeElement.getAnnotation(Factory.class);
        id = annotation.id();
        if (id == null || id.trim().isEmpty()) {
            id = typeElement.getSimpleName().toString();
        }

        try {
            // The class is already compiled
            Class<?> clazz = annotation.type();
            qualifiedGroupName = clazz.getCanonicalName();
            simpleGroupName = clazz.getSimpleName();
        } catch (MirroredTypeException mte) {
            DeclaredType classTypeMirror = (DeclaredType) mte.getTypeMirror();
            TypeElement classTypeElement = (TypeElement) classTypeMirror.asElement();
            qualifiedGroupName = classTypeElement.getQualifiedName().toString();
            simpleGroupName = classTypeElement.getSimpleName().toString();
        }
    }

    public TypeElement getTypeElement() {
        return typeElement;
    }

    public String getId() {
        return id;
    }

    public String getQualifiedGroupName() {
        return qualifiedGroupName;
    }

    public String getSimpleGroupName() {
        return simpleGroupName;
    }

}
