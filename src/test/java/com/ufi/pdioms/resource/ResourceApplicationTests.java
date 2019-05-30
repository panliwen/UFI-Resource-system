package com.ufi.pdioms.resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResourceApplicationTests {

    @Test
    public void contextLoads() {
        String fileOriginName= "我是图片dfdf5";
        String str= "kk55";
        System.out.println(new String("helloWord ").getBytes());

        String suffix = fileOriginName.substring(fileOriginName.lastIndexOf(".") +1);
        System.out.println(suffix);
    }

}
