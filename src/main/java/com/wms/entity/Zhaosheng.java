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
@TableName("zhaosheng")
@ApiModel(value="zhaosheng对象", description="")
public class Zhaosheng implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "院校代码")
    private String schoolCode;

    @ApiModelProperty(value = "学校")
    private String school;

    @ApiModelProperty(value = "专业")
    private String major;

    @ApiModelProperty(value = "专业批次")
    private String majorBatch;

    @ApiModelProperty(value = "所在城市")
    private String city;

    @ApiModelProperty(value = "专业代码")
    private String majorCode;

    @ApiModelProperty(value = "文史/理工")
    private String type;

    @ApiModelProperty(value = "学费")
    private String tuition;

    @ApiModelProperty(value = "招生人数")
    private String number;

    @ApiModelProperty(value = "包含的具体专业")
    private String allMajor;
}
