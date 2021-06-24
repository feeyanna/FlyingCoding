/**
 * com.cn Inc.
 * Copyright (c) 2011-2020 All Rights Reserved.
 */
package com.laifeiyang.dev.micro.task.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 管理定时任务
 * @author laife
 * @version Id: Task.java, v 0.1 2020/8/5 17:22 laife Exp $$
 */
@Data
public class Task implements Serializable {

    private static final long serialVersionUID = -8054692082716173379L;

    /**
     * 增加或修改标识
     */
    private int id;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务分组
     */
    private String jobGroup;

    /**
     * 任务描述
     */
    private String jobDescription;

    /**
     * 任务状态
     */
    private String jobStatus;

    /**
     * 任务表达式
     */
    private String cronExpression;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 间隔时间（毫秒）
     */
    private String milliSeconds;

    /**
     * 重复次数
     */
    private String repeatCount;

    /**
     * 起始时间
     */
    private String startDate;

    /**
     * 终止时间
     */
    private String endDate;

}
