package com.hannah.study.annotation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Excel导入
 */
public class ExcelImporter {

    public static <T> List<T> readXls(InputStream in, Class<T> clazz) {
        // key/value：title/field
        Map<String, String> fieldMap = new HashMap<>();
        // 解析@ExcelHeader注解
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            ExcelHeader header = field.getAnnotation(ExcelHeader.class);
            if (header != null) {
                fieldMap.put(header.title(), field.getName());
            }
        }

        // 读取excel
        List<T> list = new ArrayList<>();
        List<String> headerList = new ArrayList<>();
        /*
        Workbook book = WorkbookFactory.create(in);
        Sheet sheet = book.getSheetAt(0);
        for (int rowIndex = 0; rowIndex < sheet.getLastRowNum() + 1; rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                continue;
            }
            // 初始化row实体
            T entity = null;
            if (rowIndex > 0) {
                entity = clazz.newInstance();
                list.add(entity);
            }
            // 遍历列
            int cellNum = row.getLastCellNum();
            for (int columnIndex = 0; columnIndex < cellNum; columnIndex++) {
                HSSFCell cell = row.getCell(columnIndex);
                if (cell != null) {
                    String value = getCellValue(cell);
                    // 首行：标题行
                    if (rowIndex == 0) {
                        if (value != null && value.length() > 0) {
                            headerList.add(value);
                        }
                    }
                    // 内容行：设置值
                    else {
                        String title = headerList.get(columnIndex);
                        if (fieldMap.containsKey(title)) {
                            PropertyUtil.setProperty(entity, fieldMap.get(title), value);
                        }
                    }
                }
            }
        }
        ...
         */
        return list;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("");
        List<ExcelImportVo> list = ExcelImporter.readXls(new FileInputStream(file), ExcelImportVo.class);
        System.out.println(list);
    }
}
