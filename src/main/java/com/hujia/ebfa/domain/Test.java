package com.hujia.ebfa.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @PackageName:com.hujia.ebfa.domain
 * @ClassName:Test
 * @Description:
 * @author:Starry the Night
 * @Date:2020/10/13 15:21
 */
@Data
public class Test {
    @ExcelProperty("资产编号")
    private String name;
}
