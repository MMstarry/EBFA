package com.hujia.ebfa;


import com.hujia.ebfa.Utils.GlobalUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class EbfaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbfaApplication.class, args);



        GlobalUtil.create(System.getProperty("user.dir"));

        System.err.println(GlobalUtil.assetsList.toString());


    }



}
