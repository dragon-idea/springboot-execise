package com.longdi.swagger.controller;

import com.longdi.swagger.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * @author: 龍弟
 * @description
 * @date: 2021/10/11 18:14
 */
@RestController
public class HelloController {
    @GetMapping(value="/hello")
    public String hello(){
        return "hello";
    }
    //只要我们的接口中，返回值中存在实体类，它就会被扫描到Swagger中
    @PostMapping(value="/user")
    public User getUser(){
        return new User();
    }
    //Operation接口，不是放在类上的，是方法
    @ApiOperation("龍弟的接口")
    @GetMapping("/hello2")
    public String kuang(@ApiParam("用户名")String username){
        return "hello"+username;
    }

}
