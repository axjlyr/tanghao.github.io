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
@TableName("sort")
@ApiModel(value="Sort对象", description="")
public class Sort implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "学校")
    private String school;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "本科/专科")
    private String level;


    @ApiModelProperty(value = "1本/2本")
    private String grade;

    @ApiModelProperty(value = "公办/私办")
    private String pubpri;

    @ApiModelProperty(value = "985/211")
    private String rank;

    @ApiModelProperty(value = "双一流/非双一流")
    private String scale;

}
