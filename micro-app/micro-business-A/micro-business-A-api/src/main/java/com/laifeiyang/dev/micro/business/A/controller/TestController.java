/**
 * com.cn Inc.
 * Copyright (c) 2011-2021 All Rights Reserved.
 */
package com.laifeiyang.dev.micro.business.A.controller;

import com.laifeiyang.dev.micro.business.A.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 *前端控制器
 * @author laife
 * @version Id: TestController.java, v 0.1 2021/6/22 15:29 laife Exp $$
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    /**
     * 测试方式一
     */
    @PostMapping("/info")
    @ResponseBody
    public Map<String,Object> test() {
        return testService.test();
    }

    /**
     * 测试方式二
     */
    @PostMapping("/infoTwo")
    @ResponseBody
    public Map<String,Object> testTwo() {
        return testService.testTwo();
    }

    /**
     * 测试：发送短信的url
     */
    @PostMapping("/sendmsgOne")
    public Map<String,Object> sendmsgOne() {
        return testService.sendmsgOne();
    }

    /**
     * 测试：发送短信的手机号
     */
    @PostMapping("/sendmsgTwo")
    public Map<String,Object> sendmsgTwo() {
        return testService.sendmsgTwo();
    }

    /**
     * 测试：不同业务需求的发送短信的手机号
     */
    @PostMapping("/sendmsgThree")
    public Map<String,Object> sendmsgThree() {
        return testService.sendmsgThree();
    }

}
