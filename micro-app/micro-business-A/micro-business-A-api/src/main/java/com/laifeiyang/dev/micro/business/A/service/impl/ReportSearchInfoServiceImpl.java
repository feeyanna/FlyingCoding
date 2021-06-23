package com.laifeiyang.dev.micro.business.A.service.impl;

import com.laifeiyang.dev.micro.business.A.entity.ReportSearchInfo;
import com.laifeiyang.dev.micro.business.A.mapper.ReportSearchInfoMapper;
import com.laifeiyang.dev.micro.business.A.service.ReportSearchInfoService;
import com.laifeiyang.dev.micro.business.A.param.ReportSearchInfoQueryParam;
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
public class ReportSearchInfoServiceImpl extends BaseServiceImpl<ReportSearchInfoMapper, ReportSearchInfo> implements ReportSearchInfoService {

    @Autowired
    private ReportSearchInfoMapper reportSearchInfoMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveReportSearchInfo(ReportSearchInfo reportSearchInfo) throws Exception {
        return super.save(reportSearchInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateReportSearchInfo(ReportSearchInfo reportSearchInfo) throws Exception {
        return super.updateById(reportSearchInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteReportSearchInfo(ReportSearchInfoQueryParam reportSearchInfoQueryParam) throws Exception {
        return super.removeById(reportSearchInfoQueryParam.getId());
    }

    @Override
    public ReportSearchInfo getReportSearchInfoById(ReportSearchInfoQueryParam reportSearchInfoQueryParam) throws Exception {
        return super.getById(reportSearchInfoQueryParam.getId());
    }

    @Override
    public Paging<ReportSearchInfo> getReportSearchInfoPageList(ReportSearchInfoQueryParam reportSearchInfoQueryParam) throws Exception {
        Page page = setPageParam(reportSearchInfoQueryParam, OrderItem.desc("create_time"));
        long total = reportSearchInfoMapper.getReportSearchInfoPageListCount(reportSearchInfoQueryParam);
        IPage<ReportSearchInfo> iPage = reportSearchInfoMapper.getReportSearchInfoPageList(page, reportSearchInfoQueryParam);
        return new Paging(iPage.setTotal(total));
    }

}
