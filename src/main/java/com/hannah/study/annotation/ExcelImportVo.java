package com.hannah.study.annotation;

import lombok.Data;

import java.io.Serializable;

/**
 * Excel导入VO
 */
@Data
public class ExcelImportVo implements Serializable {

    @ExcelHeader(title = "城市")
    private String city;

    @ExcelHeader(title = "编码")
    private String code;

    @ExcelHeader(title = "名称", width = 200)
    private String name;

    @ExcelHeader(title = "备注", width = 300)
    private String remark;

}
