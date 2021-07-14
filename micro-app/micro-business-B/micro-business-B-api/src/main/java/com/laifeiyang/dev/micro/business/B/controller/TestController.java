/**
 * com.cn Inc.
 * Copyright (c) 2011-2021 All Rights Reserved.
 */
package com.laifeiyang.dev.micro.business.B.controller;

import com.laifeiyang.dev.micro.business.B.service.TestService;
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
     * 测试
     */
    @PostMapping("/test")
    @ResponseBody
    public Map<String,Object> test() {
        return testService.test();
    }

}
