package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("cart")
@Data
public class Cart {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer num;
    private Integer shangpinId;
    private Integer userId;
    @TableField(exist = false)
    private Shangpin shangpin;

}
