package com.hujia.ebfa.Listener;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.hujia.ebfa.Utils.GlobalUtil;
import com.hujia.ebfa.domain.Assets;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSON;

/**
 * @PackageName:com.hujia.ebfa.Listener
 * @ClassName:UploadDataListener
 * @Description:
 * @author:Starry the Night
 * @Date:2020/10/13 15:27
 */
public class UploadAssetsListener extends AnalysisEventListener<Assets> {

   public static List<Assets> list = new ArrayList<>();

    /**
     * 这个每一条数据解析都会来调用
     * @param data
     * @param analysisContext
     * @Author: Starry the Night
     * @Date:  2020/10/13 15:43
     * @return void
     */
    @Override
    public void invoke(Assets data, AnalysisContext analysisContext) {
        //System.err.println("解析到一条数据:{}"+JSON.toJSONString(data));


        if(!("".equals(data.getAssetsCode()))){
            list.add(data);
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.err.println("全部数据解析完成！");
        //System.err.println(list.toString());

        System.err.println(list.size());



        //全部固定资产信息
        String fileName =  GlobalUtil.PATH+"\\assets.xlsx";

        File assets=new File(fileName);

        //文件不存在，创建文件
        if(!assets.exists()){
            try {
                assets.createNewFile();
                EasyExcel.write(fileName, Assets.class).sheet("资产").doWrite(list);
            }catch (Exception e){

            }
        }

        Set<Assets> set = new HashSet<>(GlobalUtil.printedAssetsList);
        Set<Assets> set2 = new HashSet<>(list);
        set.addAll(set2);

        List<Assets> list_1 = new ArrayList<>(set);

        GlobalUtil.setAssetsList(list_1);

    }
}

