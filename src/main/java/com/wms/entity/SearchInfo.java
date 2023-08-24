package com.wms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="接受查询请求参数的类", description="")
public class SearchInfo {
    private String input;
//    private String schoolName;
//    private String majorName;
    private boolean is211;
    private boolean is985;
    private int isZizhu;
    private String searchFlag;

    private String majorBatch;
    private String type;

    private int pageNum;
    private int pageSize;

    public int getIs985() {
        return is985 ? 1 : 0;
    }

    public int getIs211(){
        return is211 ? 1 : 0;
    }
}
