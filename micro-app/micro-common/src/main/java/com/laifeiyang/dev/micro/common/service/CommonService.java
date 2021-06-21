package com.laifeiyang.dev.micro.common.service;

import java.util.List;
import java.util.Map;

public interface CommonService{
    /**
     * 保存对象
     * @param namespace
     * @param condition
     * @return
     * @throws Exception
     */
    public Object save(String namespace, Map<String, Object> condition) throws Exception;

    /**
     * 批量更新
     * @param namespace
     * @param objs
     * @return
     * @throws Exception
     */
    public Object batchSave(String namespace, String datasource, List objs)throws Exception;

    /**
     * 修改对象
     * @param namespace
     * @param condition
     * @return
     * @throws Exception
     */
    public Object update(String namespace, Map<String, Object> condition) throws Exception;

    /**
     * 批量更新
     * @param namespace
     * @param objs
     * @return
     * @throws Exception
     */
    public void batchUpdate(String namespace, String datasource, List objs)throws Exception;
    /**
     * 删除对象
     * @param namespace
     * @param condition
     * @return
     * @throws Exception
     */
    public Object delete(String namespace, Map<String, Object> condition) throws Exception;
    /**
     * 批量删除
     * @param namespace
     * @param datasource
     * @param objs
     * @return
     * @throws Exception
     */
    public Object batchDelete(String namespace, String datasource, List objs)throws Exception;

    /**
     * 查找返回一个对象
     * @param namespace
     * @param condition
     * @return
     * @throws Exception
     */
    public Object selectForOne(String namespace, Map<String, Object> condition);
    /**
     * 查找返回多个对象
     * @param namespace
     * @param condition
     * @return
     * @throws Exception
     */
    public List selectForList(String namespace, Map<String, Object> condition);

    /**
     * 查询列表总条数
     * @param namespace
     * @param condition
     * @return
     * @throws Exception
     */
    public Long findListTotal(String namespace, Map<String, Object> condition);
}
