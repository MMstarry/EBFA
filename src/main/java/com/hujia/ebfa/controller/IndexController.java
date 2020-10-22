package com.hujia.ebfa.controller;

import com.alibaba.excel.EasyExcel;
import com.hujia.ebfa.Listener.UploadAssetsListener;
import com.hujia.ebfa.Utils.GlobalUtil;
import com.hujia.ebfa.domain.Assets;
import com.hujia.ebfa.Utils.Hex;
import com.hujia.ebfa.Utils.PageUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @PackageName:com.hujia.ebfa.controller
 * @ClassName:IndexController
 * @Description:
 * @author:Starry the Night
 * @Date:2020/10/13 14:56
 */
@Controller
public class IndexController {

    @GetMapping({ "/index" })
    String index() {


        return "index";
    }

    /**
     * execl上传
     * @param file
     * @Author: Starry the Night
     * @Date:  2020/10/13 16:17
     * @return java.lang.String
     */
    @RequestMapping("/importExp")
    @ResponseBody
    public String importExp(@RequestParam("file") MultipartFile file) throws IOException {


        EasyExcel.read(file.getInputStream(), Assets.class, new UploadAssetsListener()).sheet().doRead();

        return "01";
    }



    @GetMapping("/user")
    String user() {
        return "user";
    }

    /**
     * 分页
     * @param params
     * @return
     */
    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params){

        int offset = Integer.parseInt(params.get("offset").toString());
        int limit = Integer.parseInt(params.get("limit").toString());
        params.put("offset", offset);
        params.put("page", offset / limit + 1);
        params.put("limit", limit);
        List<Assets> list = GlobalUtil.assetsList;

        if (list.size() == 0) return null;
        if (!params.get("assetsCode").equals("") ) {
           list = list.stream().filter(e -> e.getAssetsCode().equals(params.get("assetsCode"))).collect(Collectors.toList());
        }
        if (!params.get("classification").equals("")) {
            list = list.stream().filter(e -> e.getClassification().equals(params.get("classification"))).collect(Collectors.toList());
        }
        if (!params.get("assetsName").equals("") ) {
            list = list.stream().filter(e -> e.getAssetsName().indexOf(params.get("assetsName").toString())!=-1).collect(Collectors.toList());
        }
        if (!params.get("acquiredDate").equals("") ) {
            list = list.stream().filter(e -> e.getFinancialEntryDate().equals(params.get("acquiredDate"))).collect(Collectors.toList());
        }

        int total = list.size();
        List<Assets> assets = new ArrayList<>();
        if (total > (offset + limit)) {
            assets = list.subList(offset, offset + limit);
        } else {
            assets = list.subList(offset, total);
        }



        PageUtils pageUtils = new PageUtils(assets, total);
        return pageUtils;
    }


    @GetMapping("/downLoad")
    @ResponseBody
    public void exportPromotionByExcel07(HttpServletResponse response) throws IOException {


        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");

        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("盘点", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");


        EasyExcel.write(response.getOutputStream(), Assets.class).sheet("sheet1").doWrite(GlobalUtil.assetsList);
    }

    @GetMapping("/getHEX")
    @ResponseBody
    String getHEX(@RequestParam String code) {
        String s = "";
        if (code.length() % 2 == 0) {
            s = Hex.str2HexStr(code);
        } else {
            s = Hex.str2HexStr("0"+code);
        }
        return s;
    }


    @GetMapping("/setWriter")
    String setWriter() {
        return "device";
    }

    @PostMapping("/getRow")
    @ResponseBody
    void getRow(String code) {

        Assets assets = GlobalUtil.assetsList.stream().filter(o -> o.getAssetsCode().equals(code)).findAny().orElse(null);
        System.out.println(assets);
        GlobalUtil.printedAssetsListAdd(assets);

    }


    @GetMapping("/scan")
    String scan() {
        return "scan";
    }

    /**
     * @param params
     * @return
     */
    @ResponseBody
    @GetMapping("/listResult")
    public PageUtils listResult(@RequestParam Map<String, Object> params){

        List<Assets> list = GlobalUtil.assetsList.stream().filter((Assets assets) -> GlobalUtil.codeList.contains(assets.getAssetsCode())).collect(Collectors.toList());

        int total = list.size();

        PageUtils pageUtils = new PageUtils(list, total);
        return pageUtils;
    }

}
