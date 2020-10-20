package com.hujia.ebfa.Listener;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.hujia.ebfa.Utils.GlobalUtil;
import com.hujia.ebfa.domain.Assets;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @PackageName:com.hujia.ebfa.Listener
 * @ClassName:PrintAssentListener
 * @Description:
 * @author:Starry the Night
 * @Date:2020/10/20 11:35
 */
public class PrintAssentListener extends AnalysisEventListener<Assets> {
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
        System.err.println("已打印数据解析完成！");

        for(Assets assets:list){
            assets.setFlag(true);
        }

        GlobalUtil.printedAssetsList=list;
        System.err.println(list.size());




    }
}

