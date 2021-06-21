package com.laifeiyang.dev.micro.common.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 公共查询参数对象
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("公共查询参数对象")
public class BaseParam extends OrderQueryParam{

    private String id;

}
