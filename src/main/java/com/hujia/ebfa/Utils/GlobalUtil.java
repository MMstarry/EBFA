package com.hujia.ebfa.Utils;

import com.alibaba.excel.EasyExcel;
import com.hujia.ebfa.Listener.PrintAssentListener;
import com.hujia.ebfa.Listener.UploadAssetsListener;
import com.hujia.ebfa.domain.Assets;

import javax.naming.ldap.PagedResultsControl;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @PackageName:com.hujia.ebfa.Utils
 * @ClassName:GlobalUtil
 * @Description:
 * @author:Starry the Night
 * @Date:2020/10/14 15:50
 */
public class GlobalUtil {

    public static String PATH="";

    public static String useTime = "2020-12-30 23:59:59";

    public static SimpleDateFormat dmy_hms = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    /**
     * 全部资产信息 list 网页端展示数据
     */
    public static List<Assets> assetsList=new ArrayList<>();


    /**
     * 手持机传来的编码
     */
    public static List<String> codeList=new ArrayList<>();

    /**
     * 已打印的资产信息 list 两集合差集就是未打印的资产
     */
    public static List<Assets> printedAssetsList=new ArrayList<>();


    public static void setAssetsList(List<Assets> assets){
       assetsList=assets.stream().sorted(Comparator.comparing(Assets::getFinancialEntryDate).reversed()).collect(Collectors.toList());
    }



    public static void printedAssetsListAdd(Assets asset){
        asset.setFlag(true);
        printedAssetsList.add(asset);


        Set<Assets> set=new HashSet<>(printedAssetsList);
        Set<Assets> set2=new HashSet<>(assetsList);
        set.addAll(set2);
        setAssetsList(new ArrayList<>(set));

        //已经打印的资产编号
        String fileName =  PATH +"\\printedAssents.xlsx";

        File printedAssents=new File(fileName);

        //文件不存在，创建文件,添加数据
        if(!printedAssents.exists()){
            try {
                printedAssents.createNewFile();
                EasyExcel.write(fileName, Assets.class).sheet("资产").doWrite(printedAssetsList);
            }catch (Exception e){

            }
        }else {
            EasyExcel.write(fileName, Assets.class).sheet("资产").doWrite(printedAssetsList);
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



        //已打印固定资产信息
        String print =  execlPath+"\\printedAssents.xlsx";

        File printAssets=new File(print);

        //文件 存在读取
        if(printAssets.exists()){
            try {
                EasyExcel.read(printAssets, Assets.class, new PrintAssentListener()).sheet().doRead();

            }catch (Exception e){

            }
        }


        //全部固定资产信息
        String fileName =  execlPath+"\\assets.xlsx";

        File assets=new File(fileName);

        //文件存在，读取
        if(assets.exists()){
            try {
                EasyExcel.read(assets, Assets.class, new UploadAssetsListener()).sheet().doRead();
            }catch (Exception e){

            }
        }





    }
}
