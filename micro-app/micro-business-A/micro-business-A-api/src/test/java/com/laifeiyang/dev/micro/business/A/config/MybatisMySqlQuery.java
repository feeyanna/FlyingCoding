
package com.laifeiyang.dev.micro.business.A.config;

import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;

/**
 * MySQL代码生成查询是否为空的列
 *
 * @author laifeiyang
 **/
public class MybatisMySqlQuery extends MySqlQuery {

    @Override
    public String[] fieldCustom() {
        return new String[]{"null", "default"};
    }

}
