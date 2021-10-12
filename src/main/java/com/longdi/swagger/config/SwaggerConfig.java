package com.longdi.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author: 龍弟
 * @description
 * @date: 2021/10/11 18:24
 */
@Configuration
@EnableSwagger2        //开启Swagger2
public class SwaggerConfig {
    @Bean
    public Docket docket1(Environment environment) {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .groupName("A") ;// 配置分组
    }
    @Bean
    public Docket docket5(Environment environment){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }
    @Bean
    public Docket docket2(Environment environment) {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .groupName("B") ;// 配置分组
    }
    @Bean
    public Docket docket3(Environment environment) {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .groupName("C") ;// 配置分组
    }
    @Bean
    public Docket docket(Environment environment) {

        // 设置要显示swagger的环境
        Profiles of = Profiles.of("dev", "test");
        // 判断当前是否处于该环境
        boolean b = environment.acceptsProfiles(of);
        // 通过 enable() 接收此参数判断是否要显示
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("龍弟")
                .enable(b) //配置是否启用Swagger，如果是false，在浏览器将无法访问
                .select()// 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
                .apis(RequestHandlerSelectors.basePackage("com.longdi.swagger.controller"))
                // 配置如何通过path过滤,即这里只扫描请求以/longdi开头的接口
                .paths(PathSelectors.ant("/longdi/**"))
                .build();
    }

    //配置文档信息
    private ApiInfo apiInfo() {
        Contact contact = new Contact("龍弟", "https://blog.csdn.net/weixin_48838340", "联系人邮箱");
        return new ApiInfo(
                "龍弟的Swagger学习文档", // 标题
                "学习演示如何配置Swagger", // 描述
                "v1.0", // 版本
                "https://blog.csdn.net/weixin_48838340", // 组织链接
                contact, // 联系人信息
                "Apach 2.0 许可", // 许可
                "许可链接", // 许可连接
                new ArrayList<>()// 扩展
        );
    }
}
