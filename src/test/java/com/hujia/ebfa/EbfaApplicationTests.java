package com.hujia.ebfa;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.FileUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.hujia.ebfa.domain.Assets;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.*;

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
    void ww() throws IOException {
//        String fileName = "E:\\happyDays\\ebfa\\depend\\execl\\printedAssents.xlsx";
//
//        File file=new File(fileName);
//        if(!file.exists()){
//            file.createNewFile();
//            EasyExcel.write(fileName, Assets.class).sheet("资产").doWrite(data());
//        }
    }

    @Test
    void ee(){
// 方法1 如果写到同一个sheet
//        String fileName = "E:\\happyDays\\ebfa\\depend\\execl\\printedAssents.xlsx";
//        ExcelWriter excelWriter = null;
//        try {
//            // 这里 需要指定写用哪个class去写
//            excelWriter = EasyExcel.write(fileName, Assets.class).build();
//            // 这里注意 如果同一个sheet只要创建一次
//            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
//            // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来
//            for (int i = 0; i < 5; i++) {
//                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
//
//                excelWriter.write(data(), writeSheet);
//            }
//        } finally {
//            // 千万别忘记finish 会帮忙关闭流
//            if (excelWriter != null) {
//                excelWriter.finish();
//            }
//        }
    }

    @Test
    void contextLoads() {


        Set<Person> set=new HashSet<>();
        set.add(new Person("贾宝玉",18));
        set.add(new Person("林黛玉",16));
        set.add(new Person("薛宝钗",20));
        set.add(new Person("林黛玉",16));
        set.add(new Person("林黛玉",17));
        System.out.println("重写equals和hashCode方法");
        for (Person person : set) {
            System.out.println(person.toString());
        }



    }
    class Person{
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return name.equals(person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}

