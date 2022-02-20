package com.lake.springboot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lake.springboot.Bean.User;
import com.lake.springboot.Service.ProductService;
import com.lake.springboot.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@SpringBootTest
class IGeekShopDemoApplicationTests {

    @Autowired
    UserService userService;


    @Test
    void contextLoads() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", "001");
        userQueryWrapper.eq("password", "123");
        User one = userService.getOne(userQueryWrapper);
        System.out.println(one);
    }
}
