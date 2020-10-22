package com.hujia.ebfa.Utils;


import org.apache.poi.ss.usermodel.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @PackageName:com.hujia.ebfa.Utils
 * @ClassName:ExcelUtils
 * @Description:
 * @author:Starry the Night
 * @Date:2020/10/22 11:31
 */
public class ExcelUtils {

    public static boolean checkExeclRow(InputStream inputStream){

        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(inputStream);
            inputStream.close();
            //工作表对象
            Sheet sheet = workbook.getSheetAt(0);
            //总行数
            int rowLength = sheet.getLastRowNum() -1;
            if(rowLength<1){
                return false;
            }

            //工作表的列
            Row row = sheet.getRow(0);

            //总列数
            int colLength = row.getLastCellNum();
            //得到指定的单元格
            Cell cell = row.getCell(0);

            int count=0;
            List<String> stringList=new ArrayList<>();
            for(int i=0;i<colLength;i++){
                stringList.add(row.getCell(i).toString());

            }

            String[] strings=new String[]{"资产编号","使用部门"};
            for(int j=0;j<strings.length;j++){
                if(stringList.contains(strings[j])){
                    count+=1;
                }
            }

            if(count==strings.length){
                return true;
            }



        }catch (Exception e){

        }
        return false;
    }
}
