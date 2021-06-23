package com.laifeiyang.dev.micro.business.A.entity;

import com.laifeiyang.dev.micro.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("report_search_info")
@ApiModel(value = "ReportSearchInfo对象", description = "")
public class ReportSearchInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "从1自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(message = "从1自增主键不能为空")
    private Integer id;

    @ApiModelProperty(value = "报表ID")
    @TableField("report_id")
    private Integer reportId;

    @ApiModelProperty(value = "表名")
    @TableField("table_name")
    private String tableName;

    @ApiModelProperty(value = "字段名")
    @TableField("colm_name")
    private String colmName;

    @ApiModelProperty(value = "展示名称")
    @TableField("show_title")
    private String showTitle;

    @ApiModelProperty(value = "字段展现类型")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "数据来源，constant常量，sql数据库，interface接口")
    @TableField("col_from")
    private String colFrom;

    @ApiModelProperty(value = "是否默认最大数据账期")
    @TableField("is_max_date")
    private String isMaxDate;

    @ApiModelProperty(value = "常量或sql或接口ID")
    @TableField("data_value")
    private String dataValue;

    @ApiModelProperty(value = "是否联动")
    @TableField("is_link")
    private String isLink;

    @ApiModelProperty(value = "子节点字段名")
    @TableField("child_id")
    private String childId;

    @ApiModelProperty(value = "父节点字段名")
    @TableField("parent_id")
    private String parentId;

}
