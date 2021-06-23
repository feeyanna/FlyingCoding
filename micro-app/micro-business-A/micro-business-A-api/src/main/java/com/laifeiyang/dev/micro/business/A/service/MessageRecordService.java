package com.laifeiyang.dev.micro.business.A.service;

import com.laifeiyang.dev.micro.business.A.entity.MessageRecord;
import com.laifeiyang.dev.micro.common.service.BaseService;
import com.laifeiyang.dev.micro.business.A.param.MessageRecordQueryParam;
import com.laifeiyang.dev.micro.common.vo.Paging;

/**
 * <pre>
 *  服务类
 * </pre>
 *
 * @author laifeiyang
 * @since 2021-06-23
 */
public interface MessageRecordService extends BaseService<MessageRecord> {

    /**
     * 保存
     *
     * @param messageRecord
     * @return
     * @throws Exception
     */
    boolean saveMessageRecord(MessageRecord messageRecord) throws Exception;

    /**
     * 修改
     *
     * @param messageRecord
     * @return
     * @throws Exception
     */
    boolean updateMessageRecord(MessageRecord messageRecord) throws Exception;

    /**
     * 删除
     *
     * @param messageRecordQueryParam
     * @return
     * @throws Exception
     */
    boolean deleteMessageRecord(MessageRecordQueryParam messageRecordQueryParam) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param messageRecordQueryParam
     * @return
     * @throws Exception
     */
    MessageRecord getMessageRecordById(MessageRecordQueryParam messageRecordQueryParam) throws Exception;

    /**
     * 获取分页对象
     *
     * @param messageRecordQueryParam
     * @return
     * @throws Exception
     */
    Paging<MessageRecord> getMessageRecordPageList(MessageRecordQueryParam messageRecordQueryParam) throws Exception;

}
