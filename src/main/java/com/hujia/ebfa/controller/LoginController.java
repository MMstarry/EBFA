package com.hujia.ebfa.controller;

import com.alibaba.excel.util.StringUtils;
import com.hujia.ebfa.Utils.DateUtils;
import com.hujia.ebfa.Utils.GlobalUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
 * @author bin.lee
 * @date 2020/10/21 0021 15:00
 * @Email: libinjava@163.com
 */
@Controller
public class LoginController {

    @GetMapping("/login" )
    String login() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
                        HttpSession session){
        //验证用户名和密码，输入正确，跳转到dashboard
        if("admin".equals(username)&&"123".equals(password)){

            Date useDate = null ;
            Date websiteDatetime = DateUtils.getWebsiteDatetime("http://www.baidu.com");
            try {
                useDate = GlobalUtil.dmy_hms.parse(GlobalUtil.useTime);
                if (useDate.getTime() > websiteDatetime.getTime()) {
                    session.invalidate();
                    return "2";
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            session.setAttribute("userName",username);
            System.out.println("----" + username);

            return "0";

        }
        else  //输入错误，清空session，提示用户名密码错误
        {
            session.invalidate();
            return "1";
        }
    }

}
