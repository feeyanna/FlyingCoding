package com.laifeiyang.dev.micro.business.A.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.laifeiyang.dev.micro.business.A.constant.Constants;
import com.laifeiyang.dev.micro.business.A.mapper.TestMapper;
import com.laifeiyang.dev.micro.business.A.service.TestService;
import com.laifeiyang.dev.micro.common.utils.WebTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
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
@DS("test-B")
public class TestServiceImpl implements TestService {

    @Autowired
    private Constants constants;

    @Autowired
    private TestMapper testMapper;

    @Override
    public Map<String,Object> test() {
        Map<String,Object> resultMap = new HashMap<>();
        try {
            //动态数据源切换方式一
            DynamicDataSourceContextHolder.push("test");
            //从表中获取测试数据
            Map<String,Object> map = testMapper.getTest();
            //方式一做相关数据库操作后进行清理，后续此微服务查询其它sql会使用默认数据源master
            DynamicDataSourceContextHolder.clear();
            //做相关业务，此不演示。
            resultMap.put("code", true);
            resultMap.put("message", "动态数据源方式一切换成功");
        }catch (Exception e){
            log.error(e.getMessage());
            resultMap.put("code", false);
            resultMap.put("message", "动态数据源方式一切换失败");
        }
        return resultMap;
    }

    @Override
    @DS("test-A")
    public Map<String,Object> testTwo() {
        Map<String,Object> resultMap = new HashMap<>();
        try {
            //从表中获取测试数据
            Map<String,Object> map = testMapper.getTest();
            //做相关业务，此不演示。
            resultMap.put("code", true);
            resultMap.put("message", "动态数据源方式一切换成功");
        }catch (Exception e){
            log.error(e.getMessage());
            resultMap.put("code", false);
            resultMap.put("message", "动态数据源方式一切换失败");
        }
        return resultMap;
    }

    @Override
    public Map<String,Object> sendmsgOne() {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("url",constants.getSendMsgUrl());
        log.info("测试：发送短信的url: "+ constants.getSendMsgUrl());
        return resultMap;

    }

    @Override
    public Map<String,Object> sendmsgTwo() {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("url",constants.getSendMsgTel());
        log.info("测试：发送短信的手机号: "+ constants.getSendMsgTel());
        return resultMap;
    }

    @Override
    public Map<String,Object> sendmsgThree() {
        Map<String,Object> resultMap = new HashMap<>();
        //取值方式：json
        JSONObject jsonTel = JSONObject.parseObject(constants.getSendMsgBusinessTel());
        resultMap.put("A-BUSINESS","A-BUSINESS: "+jsonTel.getString("A-BUSINESS"));
        resultMap.put("B-BUSINESS","B-BUSINESS: "+jsonTel.getString("B-BUSINESS"));
        resultMap.put("C-BUSINESS","C-BUSINESS: "+jsonTel.getString("C-BUSINESS"));
        log.info("测试：不同业务需求的发送短信的手机号: "+ constants.getSendMsgBusinessTel());

        //取值方式二：
        List<Map> list = JSONArray.parseArray(constants.getSendMsgBusinessTelTest(), Map.class);
        for (Map map : list) {
            log.info("测试：不同业务需求的发送短信的手机号: "+ map.get("url").toString());
        }
        return resultMap;
    }

}
