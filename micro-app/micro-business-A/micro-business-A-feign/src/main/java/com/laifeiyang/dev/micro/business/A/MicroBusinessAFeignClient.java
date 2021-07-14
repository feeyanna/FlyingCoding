package com.laifeiyang.dev.micro.business.A;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/**
 * @program: micro-business-A-api
 * @description:
 * @author: laifeiyang
 * @create: 2021-07-14 20:35
 **/
@FeignClient(name = "micro-business-A-api")
public interface MicroBusinessAFeignClient {

    @PostMapping(value = {"test/sendmsgOne"}, consumes = {"application/json"})
    Map<String, Object> sendmsgOne();

}