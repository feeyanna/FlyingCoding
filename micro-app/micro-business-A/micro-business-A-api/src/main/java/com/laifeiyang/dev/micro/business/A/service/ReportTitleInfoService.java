package com.laifeiyang.dev.micro.business.A.service;

import com.laifeiyang.dev.micro.business.A.entity.ReportTitleInfo;
import com.laifeiyang.dev.micro.common.service.BaseService;
import com.laifeiyang.dev.micro.business.A.param.ReportTitleInfoQueryParam;
import com.laifeiyang.dev.micro.common.vo.Paging;

/**
 * <pre>
 *  服务类
 * </pre>
 *
 * @author laifeiyang
 * @since 2021-06-23
 */
public interface ReportTitleInfoService extends BaseService<ReportTitleInfo> {

    /**
     * 保存
     *
     * @param reportTitleInfo
     * @return
     * @throws Exception
     */
    boolean saveReportTitleInfo(ReportTitleInfo reportTitleInfo) throws Exception;

    /**
     * 修改
     *
     * @param reportTitleInfo
     * @return
     * @throws Exception
     */
    boolean updateReportTitleInfo(ReportTitleInfo reportTitleInfo) throws Exception;

    /**
     * 删除
     *
     * @param reportTitleInfoQueryParam
     * @return
     * @throws Exception
     */
    boolean deleteReportTitleInfo(ReportTitleInfoQueryParam reportTitleInfoQueryParam) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param reportTitleInfoQueryParam
     * @return
     * @throws Exception
     */
    ReportTitleInfo getReportTitleInfoById(ReportTitleInfoQueryParam reportTitleInfoQueryParam) throws Exception;

    /**
     * 获取分页对象
     *
     * @param reportTitleInfoQueryParam
     * @return
     * @throws Exception
     */
    Paging<ReportTitleInfo> getReportTitleInfoPageList(ReportTitleInfoQueryParam reportTitleInfoQueryParam) throws Exception;

}
