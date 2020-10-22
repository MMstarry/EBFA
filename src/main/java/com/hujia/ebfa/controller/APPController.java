package com.hujia.ebfa.controller;

import com.hujia.ebfa.Utils.DateUtils;
import com.hujia.ebfa.Utils.Hex;
import com.hujia.ebfa.Utils.GlobalUtil;
import com.hujia.ebfa.domain.Assets;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bin.lee
 * @date 2020/10/16 0016 15:42
 * @Email: libinjava@163.com
 */
@Controller
@RequestMapping("/app")
public class APPController {

    /**
     * 根据编号获取对应资产
     * @param code
     * @return
     */
    @GetMapping("/get")
    @ResponseBody
    public Assets get(String code) {

        List<Assets> data = GlobalUtil.assetsList;
        Assets assets = data.stream().filter(o -> o.getAssetsCode().equals(code)).findAny().orElse(null);


        if(assets == null) {

            return new Assets();
        }else {
            return assets;
        }

    }

    @GetMapping("/getHEX")
    @ResponseBody
    public Assets getHEX(String code) {

        List<Assets> data = GlobalUtil.assetsList;
        Assets assets = data.stream().filter(o -> o.getAssetsCode().equals(Hex.hexStr2Str(code))).findAny().orElse(null);
        return assets;

    }

    @PostMapping("/setCode")
    @ResponseBody
    public void setCode(@RequestBody String codes) {

        List<String> listString = Arrays.asList(codes.split(","));


        GlobalUtil.codeList.addAll(listString);

    }

    @GetMapping("/clearCode")
    @ResponseBody
    public int clearCode() {
        GlobalUtil.codeList = new ArrayList<>();
        return 200;
    }

    @GetMapping("/setUseDate")
    @ResponseBody
    public int setUseDate(String time) {
        GlobalUtil.useTime = time;
        return 200;
    }

    @GetMapping("/getUseDate")
    @ResponseBody
    public Long getUseDate() {

        Date websiteDatetime = DateUtils.getWebsiteDatetime("http://www.baidu.com");
        try {
            Date useDate = DateUtils.sdf.parse(GlobalUtil.useTime);
            return websiteDatetime.getTime()- useDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return -1l;
        }

    }

}
