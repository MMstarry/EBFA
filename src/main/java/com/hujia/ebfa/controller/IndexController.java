package com.hujia.ebfa.controller;

import com.alibaba.excel.EasyExcel;
import com.hujia.ebfa.Listener.UploadAssetsListener;
import com.hujia.ebfa.domain.Assets;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @RequestMapping("/importExp")
    @ResponseBody
    public String importExp(@RequestParam("file") MultipartFile file) throws IOException {
        System.err.println(file.getOriginalFilename());

        EasyExcel.read(file.getInputStream(), Assets.class, new UploadAssetsListener()).sheet().doRead();
        return "01";
    }
}
