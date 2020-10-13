package com.hujia.ebfa.Listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.hujia.ebfa.domain.Test;

import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.JSON;

/**
 * @PackageName:com.hujia.ebfa.Listener
 * @ClassName:UploadDataListener
 * @Description:
 * @author:Starry the Night
 * @Date:2020/10/13 15:27
 */
public class UploadDataListener extends AnalysisEventListener<Test> {

    List<Test> list = new ArrayList<>();

    /**
     * 这个每一条数据解析都会来调用
     * @param data
     * @param analysisContext
     * @Author: Starry the Night
     * @Date:  2020/10/13 15:43
     * @return void
     */
    @Override
    public void invoke(Test data, AnalysisContext analysisContext) {
        System.err.println("解析到一条数据:{}"+JSON.toJSONString(data));
        list.add(data);
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.err.println("所有数据解析完成！");
        System.err.println(list.toString());

        System.err.println(list.size());

    }
}

