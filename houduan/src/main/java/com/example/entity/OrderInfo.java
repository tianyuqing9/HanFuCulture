package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class OrderInfo {
    /**
     * 主键id
     */

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String orderId;
    private Double totalPrice;
    private Integer userId;
    private String linkAddress;
    private String linkPhone;
    private String linkMan;
    private String createTime;
    private String status;
    @TableField(exist = false)
    private Yonghu yonghu;

    @TableField(exist = false)
    private List<Shangpin> shangpinList;
}
