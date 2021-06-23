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
@TableName("report_info")
@ApiModel(value = "ReportInfo对象", description = "")
public class ReportInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "从10000自增主键")
    @TableField("id")
    @NotNull(message = "从10000自增主键不能为空")
    private Integer id;

    @ApiModelProperty(value = "报表名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "是否增加左侧树结构")
    @TableField("is_tree")
    private String isTree;

    @ApiModelProperty(value = "数据源")
    @TableField("datasource")
    private String datasource;

    @ApiModelProperty(value = "数据源类型mysql,oracle,db2")
    @TableField("datasource_type")
    private String datasourceType;

    @ApiModelProperty(value = "是否关联登录层级")
    @TableField("is_login_level")
    private String isLoginLevel;

    @ApiModelProperty(value = "是否有搜索区0无，1有")
    @TableField("is_search")
    private String isSearch;

    @ApiModelProperty(value = "是否有导出按钮0无，1有")
    @TableField("is_export")
    private String isExport;

    @ApiModelProperty(value = "导出文件类型")
    @TableField("export_type")
    private String exportType;

    @ApiModelProperty(value = "导出文件是否加密")
    @TableField("file_is_password")
    private String fileIsPassword;

    @ApiModelProperty(value = "导出文件是否增加水印")
    @TableField("file_is_water")
    private String fileIsWater;

    @ApiModelProperty(value = "页面表格是否增加水印")
    @TableField("table_is_water")
    private String tableIsWater;

    @ApiModelProperty(value = "是否自定义表头")
    @TableField("myself")
    private String myself;

    @ApiModelProperty(value = "表头vue代码")
    @TableField("title_code")
    private String titleCode;

    @ApiModelProperty(value = "查询sql")
    @TableField("data_sql")
    private String dataSql;

    @ApiModelProperty(value = "总数sql")
    @TableField("count_sql")
    private String countSql;

    @ApiModelProperty(value = "报表访问地址")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "预览页面")
    @TableField("show_text")
    private String showText;

    @ApiModelProperty(value = "报表页面")
    @TableField("html_text")
    private String htmlText;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "状态，0无效，1有效")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "报表说明")
    @TableField("remark")
    private String remark;

}
