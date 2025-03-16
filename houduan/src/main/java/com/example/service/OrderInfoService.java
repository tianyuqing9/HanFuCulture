package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.OrderInfo;
import com.example.vo.OrderInfoVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface OrderInfoService extends IService<OrderInfo> {
    OrderInfo add(OrderInfo orderInfo);
    void delete(Integer id);
    OrderInfo findByOrderId(Integer orderId);

    void changeStatus(Integer id, String status);

    void deleteGoods(Integer goodsId, Integer id);

    OrderInfoVo findEndPages(Integer pageNum, Integer pageSize, HttpServletRequest request);

    List<OrderInfo> findAll(Integer userId);

    List<Map<String, Object>> orderEcharts();
}
