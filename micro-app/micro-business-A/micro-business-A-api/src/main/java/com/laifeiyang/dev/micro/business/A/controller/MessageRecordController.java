package com.laifeiyang.dev.micro.business.A.controller;

import com.laifeiyang.dev.micro.business.A.entity.MessageRecord;
import com.laifeiyang.dev.micro.business.A.service.MessageRecordService;
import com.laifeiyang.dev.micro.business.A.param.MessageRecordQueryParam;
import com.laifeiyang.dev.micro.common.api.ApiResult;
import com.laifeiyang.dev.micro.common.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

import com.laifeiyang.dev.micro.common.vo.Paging;

/**
 * <pre>
 *  前端控制器
 * </pre>
 *
 * @author laifeiyang
 * @since 2021-06-23
 */
@Slf4j
@RestController
@RequestMapping("/messageRecord")
@Api(" API")
public class MessageRecordController extends BaseController {

    @Autowired
    private MessageRecordService messageRecordService;

    /**
     * 添加
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加MessageRecord对象", notes = "添加", response = ApiResult.class)
    public ApiResult<Boolean> addMessageRecord(@Valid @RequestBody MessageRecord messageRecord) throws Exception {
        boolean flag = messageRecordService.saveMessageRecord(messageRecord);
        return ApiResult.result(flag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改MessageRecord对象", notes = "修改", response = ApiResult.class)
    public ApiResult<Boolean> updateMessageRecord(@Valid @RequestBody MessageRecord messageRecord) throws Exception {
        boolean flag = messageRecordService.updateMessageRecord(messageRecord);
        return ApiResult.result(flag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除MessageRecord对象", notes = "删除", response = ApiResult.class)
    public ApiResult<Boolean> deleteMessageRecord(@Valid @RequestBody MessageRecordQueryParam messageRecordQueryParam) throws Exception {
        boolean flag = messageRecordService.deleteMessageRecord(messageRecordQueryParam);
        return ApiResult.result(flag);
    }

    /**
     * 获取
     */
    @PostMapping("/info")
    @ApiOperation(value = "获取MessageRecord对象详情", notes = "查看", response = MessageRecord.class)
    public ApiResult<MessageRecord> getMessageRecord(@Valid @RequestBody MessageRecordQueryParam messageRecordQueryParam) throws Exception {
        MessageRecord messageRecord = messageRecordService.getMessageRecordById(messageRecordQueryParam);
        return ApiResult.ok(messageRecord);
    }

    /**
     * 分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取MessageRecord分页列表", notes = "分页列表", response = MessageRecord.class)
    public ApiResult<Paging<MessageRecord>> getMessageRecordPageList(@Valid @RequestBody MessageRecordQueryParam messageRecordQueryParam) throws Exception {
        Paging<MessageRecord> paging = messageRecordService.getMessageRecordPageList(messageRecordQueryParam);
        return ApiResult.ok(paging);
    }

}

