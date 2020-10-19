package com.hujia.ebfa.Utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.constant.ExcelXmlConstants;
import com.hujia.ebfa.Listener.UploadAssetsListener;
import com.hujia.ebfa.domain.Assets;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PackageName:com.hujia.ebfa.Utils
 * @ClassName:GlobalUtil
 * @Description:
 * @author:Starry the Night
 * @Date:2020/10/14 15:50
 */
public class GlobalUtil {

    public static String PATH="";

    /**
     * 全部资产信息 list 网页端展示数据
     */
    public static List<Assets> assetsList=new ArrayList<>();

    /**
     * 全部资产信息 map 手持机查询时使用
     */
    public static Map<String,Assets> assetsMap=new HashMap<>();

    /**
     * 已打印的资产信息 list 两集合差集就是未打印的资产
     */
    public static List<Assets> printedAssetsList=new ArrayList<>();


    public static void setAssetsList(List<Assets> assets){
       assetsList=assets;
    }

    public static void assetsMapAdd(Assets asset){
        assetsMap.put(asset.getAssetsCode(),asset);
    }


    public static void printedAssetsListAdd(Assets asset){
        printedAssetsList.add(asset);

        //已经打印的资产编号
        String fileName =  PATH +"\\printedAssents.xlsx";

        File printedAssents=new File(fileName);

        //文件不存在，创建文件
        if(!printedAssents.exists()){
            try {
                printedAssents.createNewFile();
            }catch (Exception e){

            }
        }else {
            //读取execl数据赋值给 printedAssetsList
        }

    }

     /**
     *
     * @param projectPath 项目当前路径
     * @Author: Starry the Night
     * @Date:  2020/10/14 15:52
     * @return void
     */
    public static void create(String projectPath){

        String execlPath=projectPath+"//depend//execl";

        PATH= execlPath;
        File file=new File(execlPath);

        //文件夹不存在，创建文件夹
        if(!file.exists()){
            file.mkdirs();
        }

        //全部固定资产信息
        String fileName =  execlPath+"\\assets.xlsx";

        File assets=new File(fileName);

        //文件存在，创建文件
        if(assets.exists()){
            try {
                EasyExcel.read(assets, Assets.class, new UploadAssetsListener()).sheet().doRead();
            }catch (Exception e){

            }
        }

    }
}
