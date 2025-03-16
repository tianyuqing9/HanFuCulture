package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Cart;

import java.util.List;

public interface CartService extends IService<Cart> {
    Cart add(Cart cartInfo);
    void delete(Integer id);
    Cart update(Cart cartInfo);
    Cart findById(Integer id);
    void deleteGoods(Integer userId, Integer goodsId);
    void empty(Integer userId);
    List<Cart> findAll(Integer userId);
}
