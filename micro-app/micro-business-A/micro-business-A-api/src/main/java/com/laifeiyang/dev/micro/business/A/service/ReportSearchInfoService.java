package com.laifeiyang.dev.micro.business.A.service;

import com.laifeiyang.dev.micro.business.A.entity.ReportSearchInfo;
import com.laifeiyang.dev.micro.common.service.BaseService;
import com.laifeiyang.dev.micro.business.A.param.ReportSearchInfoQueryParam;
import com.laifeiyang.dev.micro.common.vo.Paging;

/**
 * <pre>
 *  服务类
 * </pre>
 *
 * @author laifeiyang
 * @since 2021-06-23
 */
public interface ReportSearchInfoService extends BaseService<ReportSearchInfo> {

    /**
     * 保存
     *
     * @param reportSearchInfo
     * @return
     * @throws Exception
     */
    boolean saveReportSearchInfo(ReportSearchInfo reportSearchInfo) throws Exception;

    /**
     * 修改
     *
     * @param reportSearchInfo
     * @return
     * @throws Exception
     */
    boolean updateReportSearchInfo(ReportSearchInfo reportSearchInfo) throws Exception;

    /**
     * 删除
     *
     * @param reportSearchInfoQueryParam
     * @return
     * @throws Exception
     */
    boolean deleteReportSearchInfo(ReportSearchInfoQueryParam reportSearchInfoQueryParam) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param reportSearchInfoQueryParam
     * @return
     * @throws Exception
     */
    ReportSearchInfo getReportSearchInfoById(ReportSearchInfoQueryParam reportSearchInfoQueryParam) throws Exception;

    /**
     * 获取分页对象
     *
     * @param reportSearchInfoQueryParam
     * @return
     * @throws Exception
     */
    Paging<ReportSearchInfo> getReportSearchInfoPageList(ReportSearchInfoQueryParam reportSearchInfoQueryParam) throws Exception;

}
