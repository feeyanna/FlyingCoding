package com.laifeiyang.dev.micro.common.service.impl;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.laifeiyang.dev.micro.common.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author laifeiyang
 * @version V1.0
 * @date 2020/6/8 17:22
 */

/**
 *注解了@Repository的类上如果数据库操作中抛出了异常，就能对其进行处理，
 *转而抛出的是翻译后的spring专属数据库异常，方便我们对异常进行排查处理）。
 */
@Repository
/**
 * 加入 @Transactional 注解，使用默认配置，抛出异常之后，事务会自动回滚，数据不会插入到数据库。
 */
@Slf4j
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    /**
     *分页*
     * curPage 当前页
     * size 最大行数
     * sqlName  Mybatis:namespace.sqlid
     * dataSource  数据源
     * conditions 查询条件
     */
    private static Integer curPage = 1;
    private static Integer size = 99999;

    /**
     * 保存对象
     * @param namespace
     * @param condition
     * @return
     * @throws Exception
     */
    public Object save(String namespace, Map<String, Object> condition) throws Exception {
        String datasource = condition.get("datasource").toString();
        DynamicDataSourceContextHolder.push(datasource);
        Object object =  sqlSessionTemplate.insert(namespace, condition);
        DynamicDataSourceContextHolder.clear();
        return object;
    }

    /**
     * 批量更新
     * @param namespace
     * @param objs
     * @return
     * @throws Exception
     */
    public Object batchSave(String namespace, String datasource, List objs)throws Exception{
        DynamicDataSourceContextHolder.push(datasource);
        Object object =  sqlSessionTemplate.insert(namespace, objs);
        DynamicDataSourceContextHolder.clear();
        return object;
    }

    /**
     * 修改对象
     * @param namespace
     * @param condition
     * @return
     * @throws Exception
     */
    public Object update(String namespace, Map<String, Object> condition) throws Exception {
        String datasource = condition.get("datasource").toString();
        DynamicDataSourceContextHolder.push(datasource);
        Object object =  sqlSessionTemplate.update(namespace, condition);
        DynamicDataSourceContextHolder.clear();
        return object;
    }

    /**
     * 批量更新
     * @param namespace
     * @param objs
     * @return
     * @throws Exception
     */
    public void batchUpdate(String namespace, String datasource, List objs)throws Exception{

        SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
        //批量执行器
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH,false);
        try{
            if(objs!=null){
                DynamicDataSourceContextHolder.push(datasource);
                for(int i=0,size=objs.size();i<size;i++){
                    sqlSession.update(namespace, objs.get(i));
                }
                DynamicDataSourceContextHolder.clear();
                sqlSession.flushStatements();
                sqlSession.commit();
                sqlSession.clearCache();
            }
        }finally{
            sqlSession.close();
        }
    }
    /**
     * 删除对象
     * @param namespace
     * @param condition
     * @return
     * @throws Exception
     */
    public Object delete(String namespace, Map<String, Object> condition) throws Exception {
        String datasource = condition.get("datasource").toString();
        DynamicDataSourceContextHolder.push(datasource);
        Object object =  sqlSessionTemplate.delete(namespace, condition);
        DynamicDataSourceContextHolder.clear();
        return object;
    }
    /**
     * 批量删除
     * @param namespace
     * @param datasource
     * @param objs
     * @return
     * @throws Exception
     */
    public Object batchDelete(String namespace, String datasource, List objs )throws Exception{
        DynamicDataSourceContextHolder.push(datasource);
        Object object =  sqlSessionTemplate.delete(namespace,objs);
        DynamicDataSourceContextHolder.clear();
        return object;
    }

    /**
     * 查找返回一个对象
     * @param namespace
     * @param condition
     * @return
     * @throws Exception
     */
    public Object selectForOne(String namespace, Map<String, Object> condition){
        String datasource = condition.get("datasource").toString();
        DynamicDataSourceContextHolder.push(datasource);
        Object object =  sqlSessionTemplate.selectOne(namespace, condition);
        DynamicDataSourceContextHolder.clear();
        return object;
    }
    /**
     * 查找返回多个对象
     * @param namespace
     * @param condition
     * @return
     * @throws Exception
     */
    public List selectForList(String namespace, Map<String, Object> condition){
        String datasource = condition.get("datasource").toString();
        if (condition.get("curPage") == null) {
            condition.put("curPage", curPage);
        }
        if (condition.get("size") == null) {
            condition.put("size", size);
        }
        condition.put("minSize", Integer.parseInt(String.valueOf(condition.get("size"))) * (Integer.parseInt(String.valueOf(condition.get("curPage"))) - 1));
        condition.put("maxSize", condition.get("size"));

        DynamicDataSourceContextHolder.push(datasource);
        List list =  sqlSessionTemplate.selectList(namespace, condition);
        DynamicDataSourceContextHolder.clear();
        return list;
    }

    /**
     * 查询列表总条数
     * @param namespace
     * @param condition
     * @return
     * @throws Exception
     */
    public Long findListTotal(String namespace, Map<String, Object> condition) {
        String datasource = condition.get("datasource").toString();
        DynamicDataSourceContextHolder.push(datasource);
        Long total =  sqlSessionTemplate.selectOne(namespace, condition);
        DynamicDataSourceContextHolder.clear();
        return total;
    }
}
