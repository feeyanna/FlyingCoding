package com.laifeiyang.dev.micro.common.service.impl;

import com.laifeiyang.dev.micro.common.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class SearchServiceImpl extends CommonServiceImpl implements SearchService {

    /**
     * 获取获取周内是休息日的集合
     * @param param
     * @return
     */
    @Override
    public List queryHoliDayList(Map<String, Object> param) {
        List list = new ArrayList();
        try {
            param.put("datasource", "master");
            list = this.selectForList("searchMapper.queryHoliDayList", param);
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
        return list;
    }

    /**
     * 获取周末是工作日的数据集合
     * @param param
     * @return
     */
    @Override
    public List queryWorkDayList(Map<String, Object> param) {
        List list = new ArrayList();
        try {
            param.put("datasource", "master");
            list = this.selectForList("searchMapper.queryWorkDayList", param);
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
        return list;
    }

    /**
     * 获取时间配置
     * @param param
     * @return
     */
    @Override
    public List queryTimeData(Map<String, Object> param) {
        List list = new ArrayList();
        try {
            param.put("datasource", "master");
            list = this.selectForList("searchMapper.queryTimeData", param);
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
        return list;
    }
}
