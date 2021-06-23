package com.laifeiyang.dev.micro.business.A.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laifeiyang.dev.micro.business.A.entity.ReportTitleInfo;
import com.laifeiyang.dev.micro.business.A.param.ReportTitleInfoQueryParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 *  Mapper 接口
 * </pre>
 *
 * @author laifeiyang
 * @since 2021-06-23
 */
@Repository
public interface ReportTitleInfoMapper extends BaseMapper<ReportTitleInfo> {

    /**
     * 获取分页对象记录总数
     *
     * @param reportTitleInfoQueryParam
     * @return
     */
    long getReportTitleInfoPageListCount(@Param("param") ReportTitleInfoQueryParam reportTitleInfoQueryParam);

    /**
     * 获取分页对象
     *
     * @param page
     * @param reportTitleInfoQueryParam
     * @return
     */
    IPage<ReportTitleInfo> getReportTitleInfoPageList(@Param("page") Page page, @Param("param") ReportTitleInfoQueryParam reportTitleInfoQueryParam);

}
