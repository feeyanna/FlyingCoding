/**
 * com.cn Inc.
 * Copyright (c) 2011-2020 All Rights Reserved.
 */
package com.laifeiyang.dev.micro.task.service.impl;

import com.laifeiyang.dev.micro.common.utils.DateUtil;
import com.laifeiyang.dev.micro.task.entity.Task;
import com.laifeiyang.dev.micro.task.param.TaskQueryParam;
import com.laifeiyang.dev.micro.task.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * 任务处理类
 * @author laife
 * @version Id: TaskServiceImpl.java, v 0.1 2020/8/5 17:24 laife Exp $$
 */
@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private Scheduler scheduler;

    /**
     *
     * @Title: list
     * @Description: 任务列表
     * @param @return    参数
     * @return List<TaskInfo>    返回类型
     * @throws
     */
    public List<Task> queryJobList() {
        log.info("TaskService--data-s-->queryJobList()");
        List<Task> list = new ArrayList<>();
        try {
            for (String groupJob : scheduler.getJobGroupNames()) {
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.<JobKey> groupEquals(groupJob))) {
                    List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                    for (Trigger trigger : triggers) {
                        Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                        String cronExpression = "";
                        String createTime = "";
                        String milliSeconds = "";
                        String repeatCount = "";
                        String startDate = "";
                        String endDate = "";
                        if (trigger instanceof CronTrigger) {
                            CronTrigger cronTrigger = (CronTrigger) trigger;
                            cronExpression = cronTrigger.getCronExpression();
                            createTime = cronTrigger.getDescription();
                        } else if (trigger instanceof SimpleTrigger) {
                            SimpleTrigger simpleTrigger = (SimpleTrigger) trigger;
                            milliSeconds = simpleTrigger.getRepeatInterval()+ "";
                            repeatCount = simpleTrigger.getRepeatCount() + "";
                            startDate = DateUtil.getDateStr(
                                    simpleTrigger.getStartTime(),
                                    DateUtil.FORMAT_HOUR_DATE_TIME);
                            endDate = DateUtil.getDateStr(simpleTrigger.getEndTime(),DateUtil.FORMAT_HOUR_DATE_TIME);
                        }
                        Task task = new Task();
                        task.setJobName(jobKey.getName());
                        task.setJobGroup(jobKey.getGroup());
                        task.setJobDescription(jobDetail.getDescription());
                        task.setJobStatus(triggerState.name());
                        task.setCronExpression(cronExpression);
                        task.setCreateTime(createTime);
                        task.setRepeatCount(repeatCount);
                        task.setStartDate(startDate);
                        task.setMilliSeconds(milliSeconds);
                        task.setEndDate(endDate);
                        list.add(task);
                    }
                }
            }
            log.info("任务的数量为：---------------->" + list.size());
        } catch (SchedulerException e) {
            log.info("查询任务失败，原因是：------------------>" + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    /**
     *
     * @Title: setSimpleTrigger
     * @Description: 简单调度
     * @param @param inputMap
     * @param @return    参数
     * @return Boolean    返回类型
     * @throws
     */
    @SuppressWarnings({ "unchecked" })
    public boolean setSimpleTriggerJob(Task task) {
        boolean flag = false;
        log.info("TaskService--data-s-->setSimpleTriggerJob()" + task);
        String jobName = task.getJobName();
        String jobGroup = task.getJobGroup();
        String jobDescription = task.getJobDescription();
        Long milliSeconds = Long.parseLong(task.getMilliSeconds());
        Integer repeatCount = Integer.parseInt(task.getRepeatCount());
        Date startDate = DateUtil.parseDate(task.getStartDate());
        Date endDate = DateUtil.parseDate(task.getEndDate());
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);// 触发器的key值
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);// job的key值
            if (checkExists(jobName, jobGroup)) {
                log.info(
                        "===> AddJob fail, job already exist, jobGroup:{}, jobName:{}",
                        jobGroup, jobName);
            }
            /* 简单调度 */
            SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder
                    .newTrigger()
                    .withIdentity(triggerKey)
                    .startAt(startDate)
                    .withSchedule(
                            SimpleScheduleBuilder.simpleSchedule()
                                    .withIntervalInMilliseconds(milliSeconds)
                                    .withRepeatCount(repeatCount))
                    .endAt(endDate).build();
            Class<? extends Job> clazz = (Class<? extends Job>) Class
                    .forName(jobName);
            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(jobKey)
                    .withDescription(jobDescription).build();
            scheduler.scheduleJob(jobDetail, trigger);
            flag = true;
        } catch (SchedulerException | ClassNotFoundException e) {
            log.info("任务添加失败！--->简单调度" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    /**
     *
     * @Title: addJob
     * @Description: 保存定时任务
     * @param @param task    参数
     * @return boolean    返回类型
     * @throws
     */
    @SuppressWarnings("unchecked")
    public boolean addJob(Task task) {
        boolean flag = false;
        log.info("TaskService--data-s-->addJob()" + task);
        String jobName = task.getJobName(), jobGroup = task.getJobGroup(), cronExpression = task
                .getCronExpression(), jobDescription = task.getJobDescription(), createTime = DateFormatUtils
                .format(new Date(), "yyyy-MM-dd HH:mm:ss");
        try {
            if (checkExists(jobName, jobGroup)) {
                log.info(
                        "===> AddJob fail, job already exist, jobGroup:{}, jobName:{}",
                        jobGroup, jobName);
            }

            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);

            CronScheduleBuilder schedBuilder = CronScheduleBuilder
                    .cronSchedule(cronExpression)
                    .withMisfireHandlingInstructionDoNothing();
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerKey).withDescription(createTime)
                    .withSchedule(schedBuilder).build();

            Class<? extends Job> clazz = (Class<? extends Job>) Class
                    .forName(jobName);
            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(jobKey)
                    .withDescription(jobDescription).build();
            scheduler.scheduleJob(jobDetail, trigger);
            flag = true;
        } catch (SchedulerException | ClassNotFoundException e) {
            log.info("保存定时任务-->类名不存在或执行表达式错误--->复杂调度" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    /**
     *
     * @Title: edit
     * @Description: 修改定时任务
     * @param @param task    参数
     * @return boolean    返回类型
     * @throws
     */
    public boolean editJob(Task task) {
        boolean flag = false;
        log.info("TaskService--data-s-->editJob()" + task);
        String jobName = task.getJobName(), jobGroup = task.getJobGroup(), cronExpression = task
                .getCronExpression(), jobDescription = task.getJobDescription(), createTime = DateFormatUtils
                .format(new Date(), "yyyy-MM-dd HH:mm:ss");
        try {
            if (!checkExists(jobName, jobGroup)) {
                log.debug(String.format("Job不存在, jobName:{%s},jobGroup:{%s}",
                        jobName, jobGroup));
            }
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            JobKey jobKey = new JobKey(jobName, jobGroup);
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder
                    .cronSchedule(cronExpression)
                    .withMisfireHandlingInstructionDoNothing();
            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerKey).withDescription(createTime)
                    .withSchedule(cronScheduleBuilder).build();

            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            jobDetail.getJobBuilder().withDescription(jobDescription);
            HashSet<Trigger> triggerSet = new HashSet<>();
            triggerSet.add(cronTrigger);

            scheduler.scheduleJob(jobDetail, triggerSet, true);
            flag = true;
        } catch (SchedulerException e) {
            log.info("修改定时任务-->类名不存在或执行表达式错误--->复杂调度" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    /**
     *
     * @Title: delete
     * @Description: 删除定时任务
     * @param @param taskQueryParam
     * @return boolean    返回类型
     * @throws
     */
    public boolean deleteJob(TaskQueryParam taskQueryParam) {
        boolean flag = false;
        log.info("TaskService--data-s-->deleteJob()--jobName:" + taskQueryParam.getJobName()
                + "jobGroup:" + taskQueryParam.getJobGroup());
        TriggerKey triggerKey = TriggerKey.triggerKey(taskQueryParam.getJobName(), taskQueryParam.getJobGroup());
        try {
            if (checkExists(taskQueryParam.getJobName(), taskQueryParam.getJobGroup())) {
                scheduler.pauseTrigger(triggerKey);
                scheduler.unscheduleJob(triggerKey);
                log.info("===> delete, triggerKey:{}", triggerKey);
                flag = true;
            }
        } catch (SchedulerException e) {
            log.info("删除定时任务-->复杂调度" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    /**
     *
     * @Title: pause
     * @Description: 暂停定时任务
     * @param @param taskQueryParam
     * @return boolean    返回类型
     * @throws
     */
    public boolean pauseJob(TaskQueryParam taskQueryParam) {
        boolean flag = false;
        log.info("TaskService--data-s-->pauseJob()--jobName:" + taskQueryParam.getJobName()
                + "jobGroup:" + taskQueryParam.getJobGroup());
        TriggerKey triggerKey = TriggerKey.triggerKey(taskQueryParam.getJobName(), taskQueryParam.getJobGroup());
        try {
            if (checkExists(taskQueryParam.getJobName(), taskQueryParam.getJobGroup())) {
                scheduler.pauseTrigger(triggerKey);
                flag = true;
                log.info("===> Pause success, triggerKey:{}", triggerKey);
            }
        } catch (SchedulerException e) {
            log.info("暂停定时任务-->复杂调度" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    /**
     *
     * @Title: resume
     * @Description: 恢复暂停任务
     * @param @param taskQueryParam
     * @return boolean    返回类型
     * @throws
     */
    public boolean resumeJob(TaskQueryParam taskQueryParam) {
        boolean flag = false;
        log.info("TaskService--data-s-->resumeJob()--jobName:" + taskQueryParam.getJobName()
                + "jobGroup:" + taskQueryParam.getJobGroup());
        TriggerKey triggerKey = TriggerKey.triggerKey(taskQueryParam.getJobName(), taskQueryParam.getJobGroup());
        try {
            if (checkExists(taskQueryParam.getJobName(), taskQueryParam.getJobGroup())) {
                scheduler.resumeTrigger(triggerKey);
                flag = true;
                log.info("===> Resume success, triggerKey:{}", triggerKey);
            }
        } catch (SchedulerException e) {
            log.info("重新开始任务-->复杂调度" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    /**
     *
     * @Title: checkExists
     * @Description: 验证任务是否存在
     * @param @param jobName
     * @param @param jobGroup
     * @param @return
     * @param @throws SchedulerException    参数
     * @return boolean    返回类型
     * @throws
     */
    private boolean checkExists(String jobName, String jobGroup)
            throws SchedulerException {
        log.info("TaskService--data-s-->checkExists()--jobName:" + jobName
                + "jobGroup:" + jobGroup);
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        return scheduler.checkExists(triggerKey);
    }


}
