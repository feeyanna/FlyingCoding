/**
 * com.cn Inc.
 * Copyright (c) 2011-2019 All Rights Reserved.
 */
package com.laifeiyang.dev.micro.business.A;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author laifeiyang
 * @version Id: TaskMybatisGenerator.java, v 0.1 2020/04/15 16:10 laifeiyang Exp $$
 */
@Configuration
@SpringBootTest
@RunWith(SpringRunner.class)
public class TaskMybatisGenerator {

    /**
     * mysql-url
     */
    @Value("${spring.datasource.dynamic.datasource.master.url}")
    private String url;

    /**
     * mysql-username
     */
    @Value("${spring.datasource.dynamic.datasource.master.username}")
    private String username;

    /**
     * mysql-password
     */
    @Value("${spring.datasource.dynamic.datasource.master.password}")
    private String password;

    /**
     * mysql-driver
     */
    @Value("${spring.datasource.dynamic.datasource.master.driver-class-name}")
    private String driverClassName;

    /**
     * RUN THIS
     */
    @Test
    public void init() {

        //--------变量配置begin-------
        //主生成结构包路径(/为分割符号)
        String path = "com/laifeiyang/dev/micro/business/A";
        //主生成结构包路径(.为分割符号)
        String pathSplit = "com.laifeiyang.dev.micro.business.A";
        //需要生成的表数组
        String[] tables = {
                "report_info","report_search_info","report_title_info"
        };
        //--------变量配置end-------

        CodeGenerator codeGenerator = new CodeGenerator();
        // 公共配置
        // 数据库配置
        codeGenerator
                .setUserName(username)
                .setPassword(password)
                .setDriverName(driverClassName)
                .setDriverUrl(url);

        // 包信息
        codeGenerator
                .setProjectPackagePath(path)
                .setParentPackage(pathSplit)
                .setParentPackages("com.laifeiyang.dev.micro");

        // 组件作者等配置
        codeGenerator
                .setAuthor("laifeiyang")
                .setPkIdColumnName("id");

        // 生成策略
        codeGenerator
                .setGeneratorStrategy(CodeGenerator.GeneratorStrategy.ALL)
                .setPageListOrder(true)
                .setParamValidation(true);

        // 生成实体映射相关代码,可用于数据库字段更新
        // 当数据库字段更新时，可自定义自动生成哪些那文件
        codeGenerator
                .setGeneratorEntity(true)
                .setGeneratorQueryParam(true)
                .setGeneratorQueryVo(true);

        // 生成业务相关代码
        codeGenerator
                .setGeneratorController(true)
                .setGeneratorService(true)
                .setGeneratorServiceImpl(true)
                .setGeneratorMapper(true)
                .setGeneratorMapperXml(true);

        // 是否生成Shiro RequiresPermissions注解
        codeGenerator.setRequiresPermissions(false);

        // 是否覆盖已有文件
        codeGenerator.setFileOverride(true);

        // 初始化公共变量
        codeGenerator.init();

        // 循环生成
        for (String table : tables) {
            // 设置需要生成的表名称
            codeGenerator.setTableName(table);
            // 生成代码
            codeGenerator.generator();
        }
    }

}
