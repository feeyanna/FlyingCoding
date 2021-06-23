package com.laifeiyang.dev.micro.business.A.service.impl;

import com.laifeiyang.dev.micro.business.A.entity.MessageRecord;
import com.laifeiyang.dev.micro.business.A.mapper.MessageRecordMapper;
import com.laifeiyang.dev.micro.business.A.service.MessageRecordService;
import com.laifeiyang.dev.micro.business.A.param.MessageRecordQueryParam;
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
public class MessageRecordServiceImpl extends BaseServiceImpl<MessageRecordMapper, MessageRecord> implements MessageRecordService {

    @Autowired
    private MessageRecordMapper messageRecordMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveMessageRecord(MessageRecord messageRecord) throws Exception {
        return super.save(messageRecord);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateMessageRecord(MessageRecord messageRecord) throws Exception {
        return super.updateById(messageRecord);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteMessageRecord(MessageRecordQueryParam messageRecordQueryParam) throws Exception {
        return super.removeById(messageRecordQueryParam.getId());
    }

    @Override
    public MessageRecord getMessageRecordById(MessageRecordQueryParam messageRecordQueryParam) throws Exception {
        return super.getById(messageRecordQueryParam.getId());
    }

    @Override
    public Paging<MessageRecord> getMessageRecordPageList(MessageRecordQueryParam messageRecordQueryParam) throws Exception {
        Page page = setPageParam(messageRecordQueryParam, OrderItem.desc("create_time"));
        long total = messageRecordMapper.getMessageRecordPageListCount(messageRecordQueryParam);
        IPage<MessageRecord> iPage = messageRecordMapper.getMessageRecordPageList(page, messageRecordQueryParam);
        return new Paging(iPage.setTotal(total));
    }

}
