/**
 * com.cn Inc.
 * Copyright (c) 2011-2020 All Rights Reserved.
 */
package com.laifeiyang.dev.micro.task.config;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

/**
 * 配置任务调度中心（服务启动时启动）
 * @author laife
 * @version Id: QuartzConfig.java, v 0.1 2020/8/5 17:13 laife Exp $$
 */
@Configuration
public class QuartzConfig {

    @Autowired
    private MyJobFactory myFactory;  //自定义的factory

    @Value("${spring.datasource.dynamic.datasource.master.url}")
    private String url;// 数据源地址

    @Value("${spring.datasource.dynamic.datasource.master.username}")
    private String userName;// 用户名

    @Value("${spring.datasource.dynamic.datasource.master.password}")
    private String password;// 密码

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        try {
            schedulerFactoryBean.setQuartzProperties(quartzProperties());
            schedulerFactoryBean.setJobFactory(myFactory); //指向自建的调度工厂，用于解决方法类无法注入的问题
        } catch (IOException e) {
            e.printStackTrace();
        }
        return schedulerFactoryBean;
    }

    @Bean
    public Scheduler scheduler() throws IOException, SchedulerException {
        Scheduler scheduler = schedulerFactoryBean().getScheduler();
        scheduler.start();// 服务启动
        return scheduler;
    }
    /**
     *
     * @Title: quartzProperties
     * @Description: 设置quartz属性
     * @param @return
     * @param @throws IOException    参数
     * @return Properties    返回类型
     * @throws
     */
    public Properties quartzProperties() throws IOException {
        Properties prop = new Properties();
        prop.put("org.quartz.scheduler.instanceName", "quartzScheduler");// 调度器的实例名
        prop.put("org.quartz.scheduler.instanceId", "AUTO");// 实例的标识
        prop.put("org.quartz.scheduler.skipUpdateCheck", "true");// 检查quartz是否有版本更新（true 不检查）
        prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        prop.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
        prop.put("org.quartz.jobStore.tablePrefix", "QRTZ_");// 表名前缀
        prop.put("org.quartz.jobStore.isClustered", "true");// 集群开关
        prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");// 线程池的名字
        prop.put("org.quartz.threadPool.threadCount", "10");// 指定线程数量
        prop.put("org.quartz.threadPool.threadPriority", "5");// 线程优先级（1-10）默认为5
        prop.put("org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread", "true");
        prop.put("org.quartz.jobStore.dataSource", "quartzDataSource");

        prop.put("org.quartz.dataSource.quartzDataSource.driver", "com.mysql.jdbc.Driver");
        prop.put("org.quartz.dataSource.quartzDataSource.URL", url);
        prop.put("org.quartz.dataSource.quartzDataSource.user", userName);
        prop.put("org.quartz.dataSource.quartzDataSource.password", password);
        prop.put("org.quartz.dataSource.quartzDataSource.maxConnections", "50");
        return prop;
    }

}
