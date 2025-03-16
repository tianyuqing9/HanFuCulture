package com.example.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.CartDao;
import com.example.entity.Cart;
import com.example.entity.Shangpin;
import com.example.exception.CustomException;
import com.example.service.CartService;
import com.example.service.ShangpinService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("cartService")
public class CartServiceImpl extends ServiceImpl<CartDao, Cart> implements CartService {

    @Resource
    private CartDao cartDao;
    @Resource
    private ShangpinService shangpinService;


    public List<Cart> findAll(Integer userId) {
        List<Cart> cartList = cartDao.findCartByUserId(userId);
        for (Cart cart : cartList) {
            Integer productId = cart.getShangpinId();
            Shangpin shangpin = shangpinService.findById(productId);
            if (shangpin != null) {
                cart.setShangpin(shangpin);
            }
        }
        return cartList;
    }

    public Cart findById(Integer id) {
        return cartDao.selectById(id);
    }

    public Cart add(Cart detail) {
        LambdaQueryWrapper<Cart> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ObjectUtils.isNotEmpty(detail.getUserId()),Cart::getUserId,detail.getUserId());
        wrapper.eq(ObjectUtils.isNotEmpty(detail.getShangpinId()),Cart::getShangpinId,detail.getShangpinId());
        List<Cart> infos = cartDao.selectList(wrapper);
        if (CollectionUtil.isEmpty(infos)) {
            // 新增
            detail.setNum(1);
            Shangpin shangpin = shangpinService.findById(detail.getShangpinId());
            detail.setShangpin(shangpin);
            cartDao.insert(detail);
        } else {
            // 更新
            Cart cart = infos.get(0);
            cart.setNum(cart.getNum() + 1);;
            cartDao.updateById(cart);
            throw new CustomException("002","");
        }
        return detail;
    }

    public Cart update(Cart detail) {
        cartDao.updateById(detail);
        return detail;
    }

    public void delete(Integer id) {
        cartDao.deleteById(id);
    }

    public void empty(Integer userId) {
        cartDao.deleteByUserId(userId);
    }

    public void deleteGoods(Integer userId,Integer productId) {
        cartDao.deleteGoods(userId,productId);
    }
}
