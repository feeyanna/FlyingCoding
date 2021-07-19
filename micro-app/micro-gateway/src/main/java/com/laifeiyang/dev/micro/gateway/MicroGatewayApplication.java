package com.laifeiyang.dev.micro.gateway;

import com.laifeiyang.dev.micro.common.utils.PrintApplicationInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableFeignClients
@EnableTransactionManagement
@ComponentScan("com.laifeiyang.dev")
public class MicroGatewayApplication {

    public static void main(String[] args) {
        // 启动micro-gateway
        ConfigurableApplicationContext context = SpringApplication.run(MicroGatewayApplication.class, args);
        // 打印项目信息
        PrintApplicationInfo.print(context);
    }

}
