package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.OrderGoodsRel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderGoodsRelDao extends BaseMapper<OrderGoodsRel> {
    List<OrderGoodsRel> findByOrderId(@Param("orderId") Integer orderId);

    void deleteByGoodsIdAndOrderId(@Param("goodsId") Integer goodsId, @Param("orderId") Integer orderId);

    int deleteByOrderId(@Param("orderId") Integer orderId);

    @Select("select sum(count) from order_goods_rel")
    Integer totalShopping();

    @Select("select * from order_goods_rel where orderId = #{id}")
    List<OrderGoodsRel> getOrderGoodsByOrderId(Integer id);
}
