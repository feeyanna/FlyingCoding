/**
 * com.cn Inc.
 * Copyright (c) 2011-2021 All Rights Reserved.
 */
package com.laifeiyang.dev.micro.task.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

/**
 * 测试
 * @author laife
 * @version Id: TestJob.java, v 0.1 2021/6/24 15:04 laife Exp $$
 */
@Component
@Slf4j
public class TestJob implements Job {

    @Override
    public void execute(JobExecutionContext arg0){
        log.info("=============测试开始==================");
        //业务部分
        log.info("我来啦！");
        log.info("=============测试结束==================");
    }

}
