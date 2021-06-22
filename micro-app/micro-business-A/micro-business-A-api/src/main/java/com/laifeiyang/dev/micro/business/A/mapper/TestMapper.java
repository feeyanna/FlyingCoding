package com.laifeiyang.dev.micro.business.A.mapper;

import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * <pre>
 *  Mapper 接口
 * </pre>
 *
 * @author laifeiyang
 */
@Repository
public interface TestMapper{

    /**
     * 测试
     *
     * @return
     */
    Map<String,Object> getTest();

}
