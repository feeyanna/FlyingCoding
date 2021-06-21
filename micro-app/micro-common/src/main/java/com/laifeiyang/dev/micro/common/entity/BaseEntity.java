
package com.laifeiyang.dev.micro.common.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 实体父类
 */
@Data
@ApiModel("BaseEntity")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public abstract class BaseEntity implements Serializable{

}
