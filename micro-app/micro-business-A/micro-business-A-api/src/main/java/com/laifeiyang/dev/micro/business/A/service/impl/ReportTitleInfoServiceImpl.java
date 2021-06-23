package com.laifeiyang.dev.micro.business.A.service.impl;

import com.laifeiyang.dev.micro.business.A.entity.ReportTitleInfo;
import com.laifeiyang.dev.micro.business.A.mapper.ReportTitleInfoMapper;
import com.laifeiyang.dev.micro.business.A.service.ReportTitleInfoService;
import com.laifeiyang.dev.micro.business.A.param.ReportTitleInfoQueryParam;
import com.laifeiyang.dev.micro.common.service.impl.BaseServiceImpl;
import com.laifeiyang.dev.micro.common.vo.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


/**
 * <pre>
 *  服务实现类
 * </pre>
 *
 * @author laifeiyang
 * @since 2021-06-23
 */
@Slf4j
@Service
public class ReportTitleInfoServiceImpl extends BaseServiceImpl<ReportTitleInfoMapper, ReportTitleInfo> implements ReportTitleInfoService {

    @Autowired
    private ReportTitleInfoMapper reportTitleInfoMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveReportTitleInfo(ReportTitleInfo reportTitleInfo) throws Exception {
        return super.save(reportTitleInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateReportTitleInfo(ReportTitleInfo reportTitleInfo) throws Exception {
        return super.updateById(reportTitleInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteReportTitleInfo(ReportTitleInfoQueryParam reportTitleInfoQueryParam) throws Exception {
        return super.removeById(reportTitleInfoQueryParam.getId());
    }

    @Override
    public ReportTitleInfo getReportTitleInfoById(ReportTitleInfoQueryParam reportTitleInfoQueryParam) throws Exception {
        return super.getById(reportTitleInfoQueryParam.getId());
    }

    @Override
    public Paging<ReportTitleInfo> getReportTitleInfoPageList(ReportTitleInfoQueryParam reportTitleInfoQueryParam) throws Exception {
        Page page = setPageParam(reportTitleInfoQueryParam, OrderItem.desc("create_time"));
        long total = reportTitleInfoMapper.getReportTitleInfoPageListCount(reportTitleInfoQueryParam);
        IPage<ReportTitleInfo> iPage = reportTitleInfoMapper.getReportTitleInfoPageList(page, reportTitleInfoQueryParam);
        return new Paging(iPage.setTotal(total));
    }

}
