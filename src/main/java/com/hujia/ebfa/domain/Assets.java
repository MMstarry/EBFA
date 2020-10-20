package com.hujia.ebfa.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @PackageName:com.hujia.ebfa.domain
 * @ClassName:Test
 * @Description:
 * @author:Starry the Night
 * @Date:2020/10/13 15:21
 */
@Data
public class Assets {
    @ExcelProperty("资产编号")
    private String assetsCode;

    @ExcelProperty("资产国标大类")
    private String nationalStandardClassification;

    @ExcelProperty("资产分类")
    private String classification;

    @ExcelProperty("资产名称")
    private String assetsName;

    @ExcelProperty("财务入账日期")
    private String financialEntryDate;

    @ExcelProperty("价值类型")
    private String valueType;

    @ExcelProperty("价值")
    private String value;

    @ExcelProperty("取得方式")
    private String acquiredMode;

    @ExcelProperty("取得日期")
    private String acquiredDate;

    @ExcelProperty("使用状况")
    private String useStatus;

    @ExcelProperty("使用部门")
    private String useDepartment;

    @ExcelProperty("管理部门")
    private String managementDepartment;


    @ExcelIgnore
    private Boolean flag=false;


}
