package com.laifeiyang.dev.micro.business.A;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.laifeiyang.dev.micro.common.utils.PrintApplicationInfo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * micro-business-A-api 项目启动入口
 * @author laifeiyang
 */
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@EnableDiscoveryClient
@EnableFeignClients
@EnableTransactionManagement
@MapperScan({"com.laifeiyang.dev.**.mapper"})
@ComponentScan("com.laifeiyang.dev")
public class MicroBusinessAApplication {

    public static void main(String[] args) {
        // micro-business-A-api
        ConfigurableApplicationContext context = SpringApplication.run(MicroBusinessAApplication.class, args);
        // 打印项目信息
        PrintApplicationInfo.print(context);
    }

}
