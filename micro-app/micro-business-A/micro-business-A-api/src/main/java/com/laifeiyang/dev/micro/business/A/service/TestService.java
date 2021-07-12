package com.laifeiyang.dev.micro.business.A.service;

import java.util.Map;

/**
 * <pre>
 *  服务类
 * </pre>
 *
 * @author laifeiyang
 */
public interface TestService {

    /**
     * 测试
     *
     * @return
     */
    Map<String,Object> test();

    /**
     * 测试
     *
     * @return
     */
    Map<String,Object> testTwo();

    /**
     * 测试：发送短信的url
     *
     * @return
     */
    Map<String,Object> sendmsgOne();

    /**
     * 测试：发送短信的手机号
     *
     * @return
     */
    Map<String,Object> sendmsgTwo();

    /**
     * 测试：不同业务需求的发送短信的手机号
     *
     * @return
     */
    Map<String,Object> sendmsgThree();

}
