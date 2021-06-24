/**
 * com.cn Inc.
 * Copyright (c) 2011-2020 All Rights Reserved.
 */
package com.laifeiyang.dev.micro.task.param;

import com.laifeiyang.dev.micro.common.param.BaseParam;
import lombok.Data;

/**
 * @author laife
 * @version Id: TaskQueryParam.java, v 0.1 2020/8/6 10:04 laife Exp $$
 */
@Data
public class TaskQueryParam extends BaseParam {

    private static final long serialVersionUID = 1L;

    private String jobName;

    private String jobGroup;

}
