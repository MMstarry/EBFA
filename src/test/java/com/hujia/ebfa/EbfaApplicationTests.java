package com.hujia.ebfa;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.FileUtils;
import com.hujia.ebfa.domain.Assets;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class EbfaApplicationTests {

    private List<Assets> data() {
        List<Assets> list = new ArrayList<Assets>();
        for (int i = 0; i < 10; i++) {
            Assets data = new Assets();
            data.setAssetsName("字符串" + i);
            list.add(data);
        }
        return list;
    }

    @Test
    void contextLoads() {

        /*String fileName =  "C:\\Users\\Administrator\\Desktop\\simpleWrite.xlsx";
        EasyExcel.write(fileName, Assets.class).sheet("模板").doWrite(data());*/
    }

}

