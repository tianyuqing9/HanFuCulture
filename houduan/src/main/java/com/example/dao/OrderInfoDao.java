package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderInfoDao extends BaseMapper<OrderInfo> {

    List<OrderInfo> findByUserId(@Param("userId") Integer userId);
    List<OrderInfo> findByEndUserId(@Param("userId") Integer userId,
                                    @Param("status") String status);

    int deleteById(Integer orderId);

    OrderInfo findById(Integer orderId);

    @Update("update order_info set status = #{status} where id = #{id}")
    void updateStatus(@Param("id") Integer id, @Param("status") String status);

    @Select("SELECT e.`name`, SUM(e.price) as price FROM\n" +
            "            (SELECT c.`product_name` as `name`, a.total_price as price  FROM order_info AS a\n" +
            "            JOIN order_goods_rel AS b ON a.id = b.order_id\n" +
            "            JOIN product AS c ON b.product_id = c.product_id) as e\n" +
            "            GROUP BY e.`name`")
    List<Map<String, Object>> echartsValue();
}
