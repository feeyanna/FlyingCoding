
package com.laifeiyang.dev.micro.common.enums;

/**
 * 启用禁用状态枚举
 *
 **/
public enum StateEnum implements BaseEnum {
    DISABLE(0, "禁用"),
    ENABLE(1, "启用");

    private Integer code;
    private String desc;

    StateEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}
