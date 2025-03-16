package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


@Data
public class OrderGoodsRel {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private Integer orderId;

    private Integer shangpinId;

    private Integer count;
}
