package com.laifeiyang.dev.micro.business.A.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.laifeiyang.dev.micro.common.param.BaseParam;

/**
 * <pre>
 *  查询参数对象
 * </pre>
 *
 * @author laifeiyang
 * @date 2021-06-23
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ReportSearchInfoQueryParam对象", description = "查询参数")
public class ReportSearchInfoQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
}
