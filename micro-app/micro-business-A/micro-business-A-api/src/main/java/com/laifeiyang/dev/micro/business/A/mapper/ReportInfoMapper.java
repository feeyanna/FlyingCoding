package com.laifeiyang.dev.micro.business.A.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laifeiyang.dev.micro.business.A.entity.ReportInfo;
import com.laifeiyang.dev.micro.business.A.param.ReportInfoQueryParam;
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
public interface ReportInfoMapper extends BaseMapper<ReportInfo> {

    /**
     * 获取分页对象记录总数
     *
     * @param reportInfoQueryParam
     * @return
     */
    long getReportInfoPageListCount(@Param("param") ReportInfoQueryParam reportInfoQueryParam);

    /**
     * 获取分页对象
     *
     * @param page
     * @param reportInfoQueryParam
     * @return
     */
    IPage<ReportInfo> getReportInfoPageList(@Param("page") Page page, @Param("param") ReportInfoQueryParam reportInfoQueryParam);

}
