package com.laifeiyang.dev.micro.task.controller;

import com.laifeiyang.dev.micro.common.api.ApiResult;
import com.laifeiyang.dev.micro.common.controller.BaseController;
import com.laifeiyang.dev.micro.common.vo.Paging;
import com.laifeiyang.dev.micro.task.entity.Task;
import com.laifeiyang.dev.micro.task.param.TaskQueryParam;
import com.laifeiyang.dev.micro.task.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * <pre>
 *  前端控制器
 * </pre>
 *
 * @author laifeiyang
 * @since 2020-08-06
 */
@Slf4j
@RestController
@RequestMapping("/task")
@Api(" API")
public class TaskController extends BaseController {

    @Autowired
    private TaskService taskService;

    /**
     * 获取集合
     */
    @PostMapping("/queryJobList")
    @ApiOperation(value = "获取task列表", notes = "列表", response = Task.class)
    public ApiResult<Paging<Task>> queryJobList() throws Exception {
        List<Task> list = taskService.queryJobList();
        return ApiResult.ok(list);
    }

    /**
     * 简单调度
     */
    @PostMapping("/setSimpleTriggerJob")
    @ApiOperation(value = "Task对象", notes = "调度", response = ApiResult.class)
    public ApiResult<Boolean> setSimpleTriggerJob(@Valid @RequestBody Task task) throws Exception {
        boolean flag = taskService.setSimpleTriggerJob(task);
        return ApiResult.result(flag);
    }

    /**
     * 保存定时任务
     */
    @PostMapping("/addJob")
    @ApiOperation(value = "添加Task对象", notes = "添加", response = ApiResult.class)
    public ApiResult<Boolean> addJob(@Valid @RequestBody Task task) throws Exception {
        boolean flag = taskService.addJob(task);
        return ApiResult.result(flag);
    }

    /**
     * 修改定时任务
     */
    @PostMapping("/editJob")
    @ApiOperation(value = "修改Task对象", notes = "修改", response = ApiResult.class)
    public ApiResult<Boolean> editJob(@Valid @RequestBody Task task) throws Exception {
        boolean flag = taskService.editJob(task);
        return ApiResult.result(flag);
    }

    /**
     * 删除定时任务
     */
    @PostMapping("/deleteJob")
    @ApiOperation(value = "删除SpiderExpertInfo对象", notes = "删除", response = ApiResult.class)
    public ApiResult<Boolean> deleteJob(@Valid @RequestBody TaskQueryParam taskQueryParam) throws Exception {
        boolean flag = taskService.deleteJob(taskQueryParam);
        return ApiResult.result(flag);
    }

    /**
     * 暂停定时任务
     */
    @PostMapping("/pauseJob")
    @ApiOperation(value = "暂停定时任务", notes = "暂停", response = ApiResult.class)
    public ApiResult<Task> pauseJob(@Valid @RequestBody TaskQueryParam taskQueryParam) throws Exception {
        boolean flag = taskService.pauseJob(taskQueryParam);
        return ApiResult.ok(flag);
    }

    /**
     * 恢复暂停任务
     */
    @PostMapping("/resumeJob")
    @ApiOperation(value = "恢复暂停任务", notes = "恢复", response = ApiResult.class)
    public ApiResult<Task> resumeJob(@Valid @RequestBody TaskQueryParam taskQueryParam) throws Exception {
        boolean flag = taskService.resumeJob(taskQueryParam);
        return ApiResult.ok(flag);
    }

}

