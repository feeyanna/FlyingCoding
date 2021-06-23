package com.laifeiyang.dev.micro.business.A.controller;

import com.laifeiyang.dev.micro.business.A.entity.ReportSearchInfo;
import com.laifeiyang.dev.micro.business.A.service.ReportSearchInfoService;
import com.laifeiyang.dev.micro.business.A.param.ReportSearchInfoQueryParam;
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
@RequestMapping("/reportSearchInfo")
@Api(" API")
public class ReportSearchInfoController extends BaseController {

    @Autowired
    private ReportSearchInfoService reportSearchInfoService;

    /**
     * 添加
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加ReportSearchInfo对象", notes = "添加", response = ApiResult.class)
    public ApiResult<Boolean> addReportSearchInfo(@Valid @RequestBody ReportSearchInfo reportSearchInfo) throws Exception {
        boolean flag = reportSearchInfoService.saveReportSearchInfo(reportSearchInfo);
        return ApiResult.result(flag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改ReportSearchInfo对象", notes = "修改", response = ApiResult.class)
    public ApiResult<Boolean> updateReportSearchInfo(@Valid @RequestBody ReportSearchInfo reportSearchInfo) throws Exception {
        boolean flag = reportSearchInfoService.updateReportSearchInfo(reportSearchInfo);
        return ApiResult.result(flag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除ReportSearchInfo对象", notes = "删除", response = ApiResult.class)
    public ApiResult<Boolean> deleteReportSearchInfo(@Valid @RequestBody ReportSearchInfoQueryParam reportSearchInfoQueryParam) throws Exception {
        boolean flag = reportSearchInfoService.deleteReportSearchInfo(reportSearchInfoQueryParam);
        return ApiResult.result(flag);
    }

    /**
     * 获取
     */
    @PostMapping("/info")
    @ApiOperation(value = "获取ReportSearchInfo对象详情", notes = "查看", response = ReportSearchInfo.class)
    public ApiResult<ReportSearchInfo> getReportSearchInfo(@Valid @RequestBody ReportSearchInfoQueryParam reportSearchInfoQueryParam) throws Exception {
        ReportSearchInfo reportSearchInfo = reportSearchInfoService.getReportSearchInfoById(reportSearchInfoQueryParam);
        return ApiResult.ok(reportSearchInfo);
    }

    /**
     * 分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取ReportSearchInfo分页列表", notes = "分页列表", response = ReportSearchInfo.class)
    public ApiResult<Paging<ReportSearchInfo>> getReportSearchInfoPageList(@Valid @RequestBody ReportSearchInfoQueryParam reportSearchInfoQueryParam) throws Exception {
        Paging<ReportSearchInfo> paging = reportSearchInfoService.getReportSearchInfoPageList(reportSearchInfoQueryParam);
        return ApiResult.ok(paging);
    }

}

