package com.hujia.ebfa.controller;

import com.hujia.ebfa.Utils.Hex;
import com.hujia.ebfa.Utils.GlobalUtil;
import com.hujia.ebfa.domain.Assets;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        return assets;
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


        GlobalUtil.codeList = new ArrayList<>();
        GlobalUtil.codeList = listString;

    }

}
