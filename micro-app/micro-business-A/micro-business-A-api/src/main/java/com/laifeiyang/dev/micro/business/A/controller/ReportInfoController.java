package com.laifeiyang.dev.micro.business.A.controller;

import com.laifeiyang.dev.micro.business.A.entity.ReportInfo;
import com.laifeiyang.dev.micro.business.A.service.ReportInfoService;
import com.laifeiyang.dev.micro.business.A.param.ReportInfoQueryParam;
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
@RequestMapping("/reportInfo")
@Api(" API")
public class ReportInfoController extends BaseController {

    @Autowired
    private ReportInfoService reportInfoService;

    /**
     * 添加
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加ReportInfo对象", notes = "添加", response = ApiResult.class)
    public ApiResult<Boolean> addReportInfo(@Valid @RequestBody ReportInfo reportInfo) throws Exception {
        boolean flag = reportInfoService.saveReportInfo(reportInfo);
        return ApiResult.result(flag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改ReportInfo对象", notes = "修改", response = ApiResult.class)
    public ApiResult<Boolean> updateReportInfo(@Valid @RequestBody ReportInfo reportInfo) throws Exception {
        boolean flag = reportInfoService.updateReportInfo(reportInfo);
        return ApiResult.result(flag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除ReportInfo对象", notes = "删除", response = ApiResult.class)
    public ApiResult<Boolean> deleteReportInfo(@Valid @RequestBody ReportInfoQueryParam reportInfoQueryParam) throws Exception {
        boolean flag = reportInfoService.deleteReportInfo(reportInfoQueryParam);
        return ApiResult.result(flag);
    }

    /**
     * 获取
     */
    @PostMapping("/info")
    @ApiOperation(value = "获取ReportInfo对象详情", notes = "查看", response = ReportInfo.class)
    public ApiResult<ReportInfo> getReportInfo(@Valid @RequestBody ReportInfoQueryParam reportInfoQueryParam) throws Exception {
        ReportInfo reportInfo = reportInfoService.getReportInfoById(reportInfoQueryParam);
        return ApiResult.ok(reportInfo);
    }

    /**
     * 分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取ReportInfo分页列表", notes = "分页列表", response = ReportInfo.class)
    public ApiResult<Paging<ReportInfo>> getReportInfoPageList(@Valid @RequestBody ReportInfoQueryParam reportInfoQueryParam) throws Exception {
        Paging<ReportInfo> paging = reportInfoService.getReportInfoPageList(reportInfoQueryParam);
        return ApiResult.ok(paging);
    }

}

