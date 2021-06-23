package com.laifeiyang.dev.micro.business.A.controller;

import com.laifeiyang.dev.micro.business.A.entity.ReportTitleInfo;
import com.laifeiyang.dev.micro.business.A.service.ReportTitleInfoService;
import com.laifeiyang.dev.micro.business.A.param.ReportTitleInfoQueryParam;
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
@RequestMapping("/reportTitleInfo")
@Api(" API")
public class ReportTitleInfoController extends BaseController {

    @Autowired
    private ReportTitleInfoService reportTitleInfoService;

    /**
     * 添加
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加ReportTitleInfo对象", notes = "添加", response = ApiResult.class)
    public ApiResult<Boolean> addReportTitleInfo(@Valid @RequestBody ReportTitleInfo reportTitleInfo) throws Exception {
        boolean flag = reportTitleInfoService.saveReportTitleInfo(reportTitleInfo);
        return ApiResult.result(flag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改ReportTitleInfo对象", notes = "修改", response = ApiResult.class)
    public ApiResult<Boolean> updateReportTitleInfo(@Valid @RequestBody ReportTitleInfo reportTitleInfo) throws Exception {
        boolean flag = reportTitleInfoService.updateReportTitleInfo(reportTitleInfo);
        return ApiResult.result(flag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除ReportTitleInfo对象", notes = "删除", response = ApiResult.class)
    public ApiResult<Boolean> deleteReportTitleInfo(@Valid @RequestBody ReportTitleInfoQueryParam reportTitleInfoQueryParam) throws Exception {
        boolean flag = reportTitleInfoService.deleteReportTitleInfo(reportTitleInfoQueryParam);
        return ApiResult.result(flag);
    }

    /**
     * 获取
     */
    @PostMapping("/info")
    @ApiOperation(value = "获取ReportTitleInfo对象详情", notes = "查看", response = ReportTitleInfo.class)
    public ApiResult<ReportTitleInfo> getReportTitleInfo(@Valid @RequestBody ReportTitleInfoQueryParam reportTitleInfoQueryParam) throws Exception {
        ReportTitleInfo reportTitleInfo = reportTitleInfoService.getReportTitleInfoById(reportTitleInfoQueryParam);
        return ApiResult.ok(reportTitleInfo);
    }

    /**
     * 分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取ReportTitleInfo分页列表", notes = "分页列表", response = ReportTitleInfo.class)
    public ApiResult<Paging<ReportTitleInfo>> getReportTitleInfoPageList(@Valid @RequestBody ReportTitleInfoQueryParam reportTitleInfoQueryParam) throws Exception {
        Paging<ReportTitleInfo> paging = reportTitleInfoService.getReportTitleInfoPageList(reportTitleInfoQueryParam);
        return ApiResult.ok(paging);
    }

}

