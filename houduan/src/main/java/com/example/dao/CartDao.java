package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Cart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartDao extends BaseMapper<Cart> {
    List<Cart> findCartByUserId(@Param("userId") Integer userId);

    List<Cart> findAll();

    @Delete("delete from cart where user_id = #{userId}")
    int deleteByUserId(@Param("userId") Integer userId);

    @Delete("delete from cart where user_id = #{userId} and shangpin_id = #{goodsId}")
    int deleteGoods(@Param("userId") Integer userId,
                    @Param("goodsId") Integer goodsId);
}
