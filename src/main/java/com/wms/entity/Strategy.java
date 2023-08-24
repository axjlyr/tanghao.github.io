package com.wms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("strategy")
@ApiModel(value="Strategy对象", description="")
public class Strategy implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "价格")
    private String price;

    @ApiModelProperty(value = "过期时间")
    private String gqtime;


    @ApiModelProperty(value = "名称 0体验卡，1正式卡")
    private String name;

    @ApiModelProperty(value = "试用天数")
    private String sytime;

    @ApiModelProperty(value="payment 0未支付，1已支付")
    private String payment;

    @ApiModelProperty(value = "策略 0体验卡，1正式卡")
    private String type;

    @ApiModelProperty(value = "终止时间")
    private String zztime;
}
