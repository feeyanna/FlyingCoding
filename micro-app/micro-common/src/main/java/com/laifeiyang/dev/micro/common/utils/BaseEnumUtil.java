
package com.laifeiyang.dev.micro.common.utils;

import com.laifeiyang.dev.micro.common.enums.BaseEnum;
import com.laifeiyang.dev.micro.common.vo.EnumVo;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * BaseEnum枚举工具类
 *
 */
public class BaseEnumUtil {

    private static final Map<String, List<EnumVo>> enumMap = new LinkedHashMap<>();
    private static final Map<String, Map<Integer, String>> enumClassMap = new LinkedHashMap<>();

    /**
     * 通过类型获取枚举Map
     *
     * @param clazz
     * @return
     */
    public static Map<Integer, String> getMap(Class<? extends BaseEnum> clazz) {
        return enumClassMap.get(clazz.getName());
    }

    /**
     * 通过类型获取枚举code集合
     *
     * @param clazz
     * @return
     */
    public static Set<Integer> getCodeSet(Class<? extends BaseEnum> clazz) {
        Map<Integer, String> map =  enumClassMap.get(clazz.getName());
        if (MapUtils.isEmpty(map)){
            return null;
        }
        return map.keySet();
    }

    /**
     * 通过类型获取枚举desc集合
     *
     * @param clazz
     * @return
     */
    public static Collection<String> getDescList(Class<? extends BaseEnum> clazz) {
        Map<Integer, String> map =  enumClassMap.get(clazz.getName());
        if (MapUtils.isEmpty(map)){
            return null;
        }
        return map.values();
    }

    /**
     * 通过code获取name
     *
     * @param clazz
     * @param code
     * @return
     */
    public static String getDesc(Class<? extends BaseEnum> clazz, Integer code) {
        Map<Integer, String> map = enumClassMap.get(clazz.getName());
        if (MapUtils.isEmpty(map)) {
            return null;
        }
        return map.get(code);
    }

    /**
     * 判断code在枚举中是否存在
     *
     * @param clazz
     * @param code
     * @return
     */
    public static boolean exists(Class<? extends BaseEnum> clazz, Integer code) {
        String name = getDesc(clazz, code);
        if (StringUtils.isBlank(name)) {
            return false;
        }
        return true;
    }

    /**
     * 判断code在枚举中是否不存在
     *
     * @param clazz
     * @param code
     * @return
     */
    public static boolean notExists(Class<? extends BaseEnum> clazz, Integer code) {
        return !exists(clazz, code);
    }

    public static Map<String, List<EnumVo>> getEnumMap(){
        return enumMap;
    }

    public static Map<String, Map<Integer, String>> getEnumClassMap(){
        return enumClassMap;
    }

}
