package com.wms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("admission")
@ApiModel(value="admission对象", description="")
public class Admission implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "学校")
    private String school;

    @ApiModelProperty(value = "专业")
    private String major;

    @ApiModelProperty(value = "文史/理工")
    private String type;

    @ApiModelProperty(value = "批次")
    private String majorBatch;

    @ApiModelProperty(value = "招生年份")
    private String year;

    @ApiModelProperty(value = "招生人数")
    private String number;

    @ApiModelProperty(value = "最低分")
    private String min;

    @ApiModelProperty(value = "最低位次")
    private String minplace;

    @ApiModelProperty(value = "平均分")
    private String ave;

    @ApiModelProperty(value = "平均位次")
    private String aveplace;

    @ApiModelProperty(value = "最高分")
    private String max;

    @ApiModelProperty(value = "最高位次")
    private String maxplace;
}
