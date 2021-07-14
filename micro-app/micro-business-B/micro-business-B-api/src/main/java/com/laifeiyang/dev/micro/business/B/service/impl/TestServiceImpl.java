package com.laifeiyang.dev.micro.business.B.service.impl;

import com.laifeiyang.dev.micro.business.A.MicroBusinessAFeignClient;
import com.laifeiyang.dev.micro.business.B.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * <pre>
 *  服务实现类
 * </pre>
 *
 * @author laifeiyang
 */
@Slf4j
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private MicroBusinessAFeignClient microBusinessAFeignClient;


    @Override
    public Map<String,Object> test() {
        Map<String,Object> resultMap = new HashMap<>();
        try {
            log.info("我要开始调用A微服务啦~");
            resultMap = microBusinessAFeignClient.sendmsgOne();
            log.info("我已经调用到A微服务啦~");
        }catch (Exception e){
            log.error(e.getMessage());
            resultMap.put("code", false);
            resultMap.put("message", "动态数据源方式一切换失败");
        }
        return resultMap;
    }

}
