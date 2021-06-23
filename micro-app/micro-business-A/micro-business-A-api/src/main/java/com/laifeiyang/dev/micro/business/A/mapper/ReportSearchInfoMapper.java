package com.laifeiyang.dev.micro.business.A.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laifeiyang.dev.micro.business.A.entity.ReportSearchInfo;
import com.laifeiyang.dev.micro.business.A.param.ReportSearchInfoQueryParam;
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
public interface ReportSearchInfoMapper extends BaseMapper<ReportSearchInfo> {

    /**
     * 获取分页对象记录总数
     *
     * @param reportSearchInfoQueryParam
     * @return
     */
    long getReportSearchInfoPageListCount(@Param("param") ReportSearchInfoQueryParam reportSearchInfoQueryParam);

    /**
     * 获取分页对象
     *
     * @param page
     * @param reportSearchInfoQueryParam
     * @return
     */
    IPage<ReportSearchInfo> getReportSearchInfoPageList(@Param("page") Page page, @Param("param") ReportSearchInfoQueryParam reportSearchInfoQueryParam);

}
