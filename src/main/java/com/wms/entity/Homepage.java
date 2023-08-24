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
@TableName("homepage")
@ApiModel(value="homepage对象", description="")
public class Homepage implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "院校代码")
    private String schoolCode;

    @ApiModelProperty(value = "学校")
    private String school;

    @ApiModelProperty(value = "专业批次")
    private String majorBatch;

    @ApiModelProperty(value = "文史/理工")
    private String type;

    @ApiModelProperty(value = "985")
    private String nef;

    @ApiModelProperty(value = "211")
    private String too;

    @ApiModelProperty(value = "所在城市")
    private String city;

    @ApiModelProperty(value = "今年招生人数")
    private String thisNumber;

    @ApiModelProperty(value = "去年招生人数")
    private String lastNumber;

    @ApiModelProperty(value = "今年最低分")
    private String thisMin;

    @ApiModelProperty(value = "今年最低位次")
    private String thisMinplace;

    @ApiModelProperty(value = "去年最低分")
    private String lastMin;

    @ApiModelProperty(value = "去年最低位次")
    private String lastMinplace;

    @ApiModelProperty(value = "前年最低分")
    private String llastMin;

    @ApiModelProperty(value = "前年最低位次")
    private String llastMinplace;

}
