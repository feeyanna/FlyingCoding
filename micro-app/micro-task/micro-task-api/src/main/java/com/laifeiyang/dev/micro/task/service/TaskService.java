/**
 * com.cn Inc.
 * Copyright (c) 2011-2020 All Rights Reserved.
 */
package com.laifeiyang.dev.micro.task.service;

import com.laifeiyang.dev.micro.task.entity.Task;
import com.laifeiyang.dev.micro.task.param.TaskQueryParam;

import java.util.List;

/**
 * @author laife
 * @version Id: TaskService.java, v 0.1 2020/8/6 9:42 laife Exp $$
 */
public interface TaskService {

    /**
     * 获取集合
     *
     * @return
     * @throws Exception
     */
    List<Task> queryJobList() throws Exception;

    /**
     * 简单调度
     *
     * @param task
     * @return
     * @throws Exception
     */
    boolean setSimpleTriggerJob(Task task) throws Exception;

    /**
     * 保存定时任务
     *
     * @param task
     * @return
     * @throws Exception
     */
    boolean addJob(Task task) throws Exception;

    /**
     * 修改定时任务
     *
     * @param task
     * @return
     * @throws Exception
     */
    boolean editJob(Task task) throws Exception;

    /**
     * 删除定时任务
     *
     * @param taskQueryParam
     * @return
     * @throws Exception
     */
    boolean deleteJob(TaskQueryParam taskQueryParam) throws Exception;

    /**
     * 暂停定时任务
     *
     * @param taskQueryParam
     * @return
     * @throws Exception
     */
    boolean pauseJob(TaskQueryParam taskQueryParam) throws Exception;

    /**
     * 恢复暂停任务
     *
     * @param taskQueryParam
     * @return
     * @throws Exception
     */
    boolean resumeJob(TaskQueryParam taskQueryParam) throws Exception;

}
