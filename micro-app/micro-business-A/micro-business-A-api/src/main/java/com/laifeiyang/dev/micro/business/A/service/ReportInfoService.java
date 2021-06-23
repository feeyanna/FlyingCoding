package com.laifeiyang.dev.micro.business.A.service;

import com.laifeiyang.dev.micro.business.A.entity.ReportInfo;
import com.laifeiyang.dev.micro.common.service.BaseService;
import com.laifeiyang.dev.micro.business.A.param.ReportInfoQueryParam;
import com.laifeiyang.dev.micro.common.vo.Paging;

/**
 * <pre>
 *  服务类
 * </pre>
 *
 * @author laifeiyang
 * @since 2021-06-23
 */
public interface ReportInfoService extends BaseService<ReportInfo> {

    /**
     * 保存
     *
     * @param reportInfo
     * @return
     * @throws Exception
     */
    boolean saveReportInfo(ReportInfo reportInfo) throws Exception;

    /**
     * 修改
     *
     * @param reportInfo
     * @return
     * @throws Exception
     */
    boolean updateReportInfo(ReportInfo reportInfo) throws Exception;

    /**
     * 删除
     *
     * @param reportInfoQueryParam
     * @return
     * @throws Exception
     */
    boolean deleteReportInfo(ReportInfoQueryParam reportInfoQueryParam) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param reportInfoQueryParam
     * @return
     * @throws Exception
     */
    ReportInfo getReportInfoById(ReportInfoQueryParam reportInfoQueryParam) throws Exception;

    /**
     * 获取分页对象
     *
     * @param reportInfoQueryParam
     * @return
     * @throws Exception
     */
    Paging<ReportInfo> getReportInfoPageList(ReportInfoQueryParam reportInfoQueryParam) throws Exception;

}
