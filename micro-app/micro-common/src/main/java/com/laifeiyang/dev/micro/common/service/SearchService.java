package com.laifeiyang.dev.micro.common.service;

import java.util.List;
import java.util.Map;

public interface SearchService {
    /**
     * 获取获取周内是休息日的集合
     * @param param
     * @return
     */
    List queryHoliDayList(Map<String, Object> param);
    /**
     * 获取周末是工作日的数据集合
     * @param param
     * @return
     */
    List queryWorkDayList(Map<String, Object> param);
    /**
     * 获取时间配置
     * @param param
     * @return
     */
    List queryTimeData(Map<String, Object> param);
}
