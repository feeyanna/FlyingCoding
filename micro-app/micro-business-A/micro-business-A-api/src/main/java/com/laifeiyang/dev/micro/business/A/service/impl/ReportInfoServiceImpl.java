package com.laifeiyang.dev.micro.business.A.service.impl;

import com.laifeiyang.dev.micro.business.A.entity.ReportInfo;
import com.laifeiyang.dev.micro.business.A.mapper.ReportInfoMapper;
import com.laifeiyang.dev.micro.business.A.service.ReportInfoService;
import com.laifeiyang.dev.micro.business.A.param.ReportInfoQueryParam;
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
public class ReportInfoServiceImpl extends BaseServiceImpl<ReportInfoMapper, ReportInfo> implements ReportInfoService {

    @Autowired
    private ReportInfoMapper reportInfoMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveReportInfo(ReportInfo reportInfo) throws Exception {
        return super.save(reportInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateReportInfo(ReportInfo reportInfo) throws Exception {
        return super.updateById(reportInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteReportInfo(ReportInfoQueryParam reportInfoQueryParam) throws Exception {
        return super.removeById(reportInfoQueryParam.getId());
    }

    @Override
    public ReportInfo getReportInfoById(ReportInfoQueryParam reportInfoQueryParam) throws Exception {
        return super.getById(reportInfoQueryParam.getId());
    }

    @Override
    public Paging<ReportInfo> getReportInfoPageList(ReportInfoQueryParam reportInfoQueryParam) throws Exception {
        Page page = setPageParam(reportInfoQueryParam, OrderItem.desc("create_time"));
        long total = reportInfoMapper.getReportInfoPageListCount(reportInfoQueryParam);
        IPage<ReportInfo> iPage = reportInfoMapper.getReportInfoPageList(page, reportInfoQueryParam);
        return new Paging(iPage.setTotal(total));
    }

}
