package com.laifeiyang.dev.micro.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BaseUtil {

    /**
     *分页*
     * curPage 当前页
     * size 最大行数
     * sqlName  Mybatis:namespace.sqlid
     * dataSource  数据源
     * conditions 查询条件
     */
    private static Integer curPage = 1;
    private static Integer size = 20;

    /**
     * 获取项目根路径
     *
     * @return
     */
    public static String getResourceBasePath() {
        // 获取跟目录
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            // nothing to do
        }
        if (path == null || !path.exists()) {
            path = new File("");
        }

        String pathStr = path.getAbsolutePath();
        // 如果是在idea中运行，则和target同级目录,如果是jar部署到服务器，则默认和jar包同级
        pathStr = pathStr.replace("/bin", "");
        return pathStr;
    }

    /**
     * 环比
     * @param lastMonth(上月)
     * @param lastLastMonth(上上月)
     * @return
     */
    public static String getCompare(double lastMonth,double lastLastMonth) {
        double compareSum = (lastMonth-lastLastMonth) / lastLastMonth * 100;
        return getTwoPoint(compareSum);
    }

    /*
    * 获取两位小数，并四舍五入
    * */
    public static String getTwoPoint(double compareSum) {
        BigDecimal bigDecimal = new BigDecimal(compareSum);
        compareSum = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return String.valueOf(compareSum);
    }

    /**
     * 元转万元，转换为bigDecimal在toString,保留两位小数
     * @return
     */
    public static String yuanToMillion(String price) {
        //把元转换为万元
        double priceDouble = BigDecimal.valueOf(Long.valueOf(price)).divide(new BigDecimal(10000)).doubleValue();
        return getTwoPoint(priceDouble);
    }

    /**
     * 分转万元，转换为bigDecimal在toString,保留两位小数
     * @return
     */
    public static String minuteToMillion(String price) {
        //把分转换为万元
        double priceDouble = BigDecimal.valueOf(Long.valueOf(price)).divide(new BigDecimal(1000000)).doubleValue();
        return getTwoPoint(priceDouble);
    }

    /**
     * 分页组合分页条件
     *
     * @param param 原条件
     * @return Map
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getConditions(JSONObject param) {
        Map<String, Object> resultParams = new HashMap<String, Object>();
        Map<String, Object> par = JSONObject.toJavaObject(param, Map.class);
        if (!par.isEmpty()) {
            String conditionKey = "";
            for (Map.Entry<String, Object> entry : par.entrySet()) {
                conditionKey = entry.getKey();
                Object mapValue = entry.getValue();
                if(conditionKey.startsWith("conditions")){
                    resultParams.put(conditionKey.substring(conditionKey.indexOf(".")+1),mapValue);
                }
            }
        }

        if (resultParams.get("curPage") == null) {
            resultParams.put("curPage", curPage);
        }
        if (resultParams.get("size") == null) {
            resultParams.put("size", size);
        }

        resultParams.put("minSize", size * (curPage - 1) + 1);
        resultParams.put("maxSize", size * curPage);

        return resultParams;
    }

    /**
     * 包装页面传来参数
     *
     * @param param 原条件
     * @return Map
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> makeCondition(JSONObject param) {
        Map<String, Object> resultParams = new HashMap<String, Object>();
        Map<String, Object> par = JSONObject.toJavaObject(param, Map.class);
        if (!par.isEmpty()) {
            String conditionKey = "";
            for (Map.Entry<String, Object> entry : par.entrySet()) {
                conditionKey = entry.getKey();
                Object mapValue = entry.getValue();
                if(conditionKey.startsWith("conditions")){
                    resultParams.put(conditionKey.substring(conditionKey.indexOf(".")+1),mapValue);
                }
            }
        }
        return resultParams;
    }

}
