/**
 * com.cn Inc.
 * Copyright (c) 2011-2020 All Rights Reserved.
 */
package com.laifeiyang.dev.micro.task.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * 解决不能spring注入bean的问题
 * @author laife
 * @version Id: MyJobFactory.java, v 0.1 2020/8/5 17:11 laife Exp $$
 */

@Component
public class MyJobFactory extends AdaptableJobFactory {

    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance = super.createJobInstance(bundle);
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }

}
