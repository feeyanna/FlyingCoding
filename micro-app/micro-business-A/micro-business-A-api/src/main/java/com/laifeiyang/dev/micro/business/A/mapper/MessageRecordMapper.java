package com.laifeiyang.dev.micro.business.A.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laifeiyang.dev.micro.business.A.entity.MessageRecord;
import com.laifeiyang.dev.micro.business.A.param.MessageRecordQueryParam;
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
public interface MessageRecordMapper extends BaseMapper<MessageRecord> {

    /**
     * 获取分页对象记录总数
     *
     * @param messageRecordQueryParam
     * @return
     */
    long getMessageRecordPageListCount(@Param("param") MessageRecordQueryParam messageRecordQueryParam);

    /**
     * 获取分页对象
     *
     * @param page
     * @param messageRecordQueryParam
     * @return
     */
    IPage<MessageRecord> getMessageRecordPageList(@Param("page") Page page, @Param("param") MessageRecordQueryParam messageRecordQueryParam);

}
