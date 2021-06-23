package com.laifeiyang.dev.micro.business.A.entity;

import com.laifeiyang.dev.micro.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <pre>
 * 
 * </pre>
 *
 * @author laifeiyang
 * @since 2021-06-23
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("message_record")
@ApiModel(value = "MessageRecord对象", description = "")
public class MessageRecord extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "短信ID")
    @TableId(value = "record_id", type = IdType.AUTO)
    @NotNull(message = "短信ID不能为空")
    private Integer recordId;

    @ApiModelProperty(value = "短信编码(可为空)")
    @TableField("business_code")
    private String businessCode;

    @ApiModelProperty(value = "发送号码")
    @TableField("message_tel")
    private String messageTel;

    @ApiModelProperty(value = "短信内容")
    @TableField("message_content")
    private String messageContent;

    @ApiModelProperty(value = "插入内容")
    @TableField("inner_message")
    private String innerMessage;

    @ApiModelProperty(value = "发送内容")
    @TableField("outer_message")
    private String outerMessage;

    @ApiModelProperty(value = "短信发送时间")
    @TableField("send_time")
    private Date sendTime;

    @ApiModelProperty(value = "短信是否发送成功(0:未成功；1:成功)")
    @TableField("send_status")
    private Integer sendStatus;

    @ApiModelProperty(value = "关联模板ID")
    @TableField("model_id")
    private Integer modelId;

    @ApiModelProperty(value = "创建人")
    @TableField("create_staff")
    private Long createStaff;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_date")
    private Date createDate;

    @ApiModelProperty(value = "操作类型")
    @TableField("oper_type")
    private String operType;

    @ApiModelProperty(value = "修改的员工账号")
    @TableField("update_staff")
    private Long updateStaff;

    @ApiModelProperty(value = "记录修改的时间")
    @TableField("update_date")
    private Date updateDate;

}
