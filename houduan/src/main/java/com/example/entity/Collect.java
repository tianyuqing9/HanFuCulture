package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;


@TableName("collect")
@Data
public class Collect {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    @TableField(value = "shangpinxinxiID")
    private Integer shangpinxinxiID;

    @TableField(exist = false)
    private Yonghu yonghu;
    @TableField(exist = false)
    private List<Shangpin> productList;
}
