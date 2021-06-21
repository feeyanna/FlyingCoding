package com.laifeiyang.dev.micro.business.B;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.laifeiyang.dev.micro.common.utils.PrintApplicationInfo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * micro-business-B-api 项目启动入口
 * @author laifeiyang
 * @since 2020-04-23
 */
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@EnableDiscoveryClient
@EnableTransactionManagement
@MapperScan({"com.laifeiyang.dev.**.mapper"})
@ComponentScan("com.laifeiyang.dev")
public class MicroBusinessBApplication {

    public static void main(String[] args) {
        // micro-business-B-api
        ConfigurableApplicationContext context = SpringApplication.run(MicroBusinessBApplication.class, args);
        // 打印项目信息
        PrintApplicationInfo.print(context);
    }

}
