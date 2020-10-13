package com.hujia.ebfa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}
