package com.example.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.OrderGoodsRelDao;
import com.example.dao.OrderInfoDao;
import com.example.entity.OrderGoodsRel;
import com.example.entity.OrderInfo;
import com.example.entity.Shangpin;
import com.example.entity.Yonghu;
import com.example.service.CartService;
import com.example.service.OrderInfoService;
import com.example.service.ShangpinService;
import com.example.service.YonghuService;
import com.example.vo.OrderInfoVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("orderInfoService")
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoDao, OrderInfo> implements OrderInfoService {

    @Resource
    private OrderInfoDao orderInfoDao;
    @Resource
    private ShangpinService shangpinService;
	@Resource
	private YonghuService yonghuService;
    @Resource
    private OrderGoodsRelDao orderGoodsRelDao;
    @Resource
    private CartService cartService;

    /**
     * 根据id查询订单信息
     */
    public OrderInfo findById(Long id) {
        OrderInfo orderInfo = orderInfoDao.selectById(id);
        packOrder(orderInfo);
        return orderInfo;
    }

    public List<OrderInfo> findAll(Integer userId) {
        List<OrderInfo> orderInfos = orderInfoDao.findByUserId(userId);
        for (OrderInfo orderInfo : orderInfos) {
            packOrder(orderInfo);
        }
        return orderInfos;
    }

    @Override
    public List<Map<String, Object>> orderEcharts() {
        List<Map<String, Object>> orderInfos = orderInfoDao.echartsValue();
        return orderInfos;
    }

    /**
     * 包装订单的用户和图书信息
     */
    private void packOrder(OrderInfo orderInfo) {
        Integer orderId = orderInfo.getId();
        List<OrderGoodsRel> rels = orderGoodsRelDao.findByOrderId(orderId);
        orderInfo.setYonghu(getUserInfo(orderInfo.getUserId()));
        List<Shangpin> goodsList = CollUtil.newArrayList();
        orderInfo.setShangpinList(goodsList);
        for (OrderGoodsRel rel : rels) {
            Shangpin goodsDetailInfo = shangpinService.findById(rel.getShangpinId());
            if (goodsDetailInfo != null) {
                // 注意这里返回的count是用户加入图书的数量，而不是图书的库存
                goodsDetailInfo.setKucun(rel.getCount());
                goodsList.add(goodsDetailInfo);
            }
        }
    }

    /**
     * 分页查询订单信息
     */
    public OrderInfoVo findEndPages(Integer pageNum, Integer pageSize, HttpServletRequest request) {
        List<OrderInfo> orderInfos;
        LambdaQueryWrapper<OrderInfo> wrapper = Wrappers.lambdaQuery();
        Page<OrderInfo> page = new Page<>(pageNum,pageSize);
        orderInfoDao.selectPage(page,wrapper);
        orderInfos = page.getRecords();
        for (OrderInfo orderInfo : orderInfos) {
            packOrder(orderInfo);
        }
        OrderInfoVo orderInfoVo = new OrderInfoVo();
        orderInfoVo.setList(orderInfos);
        orderInfoVo.setPages(page.getPages());
        orderInfoVo.setTotal(page.getTotal());
        orderInfoVo.setCurrent(page.getCurrent());
        orderInfoVo.setCurrentNum(page.getSize());
        return orderInfoVo;
    }

    public OrderInfoVo findFrontPages(Long userId, Integer level, Integer pageNum, Integer pageSize) {
        List<OrderInfo> orderInfos;
        LambdaQueryWrapper<OrderInfo> wrapper = Wrappers.lambdaQuery();
        Page<OrderInfo> page = new Page<>(pageNum,pageSize);
        if (userId != null){
            wrapper.eq(ObjectUtils.isNotEmpty(userId), OrderInfo::getUserId, userId);
            orderInfoDao.selectPage(page,wrapper);
            orderInfos = page.getRecords();
        } else {
            orderInfos = new ArrayList<>();
        }
        for (OrderInfo orderInfo : orderInfos) {
            packOrder(orderInfo);
        }
        OrderInfoVo orderInfoVo = new OrderInfoVo();
        orderInfoVo.setList(orderInfos);
        orderInfoVo.setPages(page.getPages());
        orderInfoVo.setTotal(page.getTotal());
        orderInfoVo.setCurrent(page.getCurrent());
        orderInfoVo.setCurrentNum(page.getSize());
        return orderInfoVo;
    }

    /**
     * 下单
     */
    @Transactional
    public OrderInfo add(OrderInfo info) {
        Integer userId = info.getUserId();
        List<Shangpin> goodsList = info.getShangpinList();
        // 这里面创建一个订单
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(userId);
        orderInfo.setStatus("待付款");
        orderInfo.setLinkAddress(info.getLinkAddress());
        orderInfo.setLinkMan(info.getLinkMan());
        orderInfo.setLinkPhone(info.getLinkPhone());
        orderInfo.setCreateTime(DateUtil.formatDateTime(new Date()));
        // 订单id：用户id + 当前年月日时分 + 4位流水号
        String orderId = userId + DateUtil.format(new Date(), "yyyyMMddHHmm") + RandomUtil.randomNumbers(4);
        orderInfo.setOrderId(orderId);
        // 生成订单
        orderInfoDao.insert(orderInfo);
        double totalPrice = 0;
        for (Shangpin shangpin : goodsList) {
            Integer goodsId = shangpin.getId();
            Shangpin goodsDetail = shangpinService.findById(goodsId);
            if (goodsDetail != null) {
                Integer orderCount = shangpin.getKucun() == null ? 0 : shangpin.getKucun();
                Integer goodsCount = goodsDetail.getKucun()  == null ? 0 : goodsDetail.getKucun();
                // 扣库存
                if (orderCount <= goodsCount) {
                    goodsDetail.setKucun(goodsCount - orderCount);
                    // 销量 +count
//                    int sales = goodsDetail.getShangpinSales() == null ? 0 : goodsDetail.getShangpinSales();
//                    goodsDetail.setShangpinSales(sales + orderCount);
//                    shangpinService.update(goodsDetail);
                    // 建立关系
                    OrderGoodsRel orderGoodsRel = new OrderGoodsRel();
                    orderGoodsRel.setShangpinId(goodsId);
                    orderGoodsRel.setOrderId(orderInfo.getId());
                    orderGoodsRel.setCount(orderCount);
                    orderGoodsRelDao.insert(orderGoodsRel);
                    totalPrice += Float.parseFloat(goodsDetail.getJiage()) * orderCount;

                    shangpinService.update(goodsDetail);
                }
            }
        }
            orderInfo.setTotalPrice(totalPrice);
            // 更新订单信息
            orderInfoDao.updateById(orderInfo);
            List<Shangpin> productList = info.getShangpinList();
        for (Shangpin product : productList) {
            cartService.deleteGoods(info.getUserId(),product.getId());
        }
        return info;
    }

    @Transactional
    public void delete(Integer id) {
        orderInfoDao.deleteById(id);
        orderGoodsRelDao.deleteByOrderId(id);
    }

    public void deleteGoods(Integer goodsId, Integer orderId) {
        orderGoodsRelDao.deleteByGoodsIdAndOrderId(goodsId, orderId);
    }

    public OrderInfo findByOrderId(Integer orderId) {
        return orderInfoDao.findById(orderId);
    }

    public void changeStatus(Integer id, String status) {
        OrderInfo order = orderInfoDao.findById(id);
        Integer userId = order.getUserId();
        orderInfoDao.updateStatus(id, status);
    }

    private Yonghu getUserInfo(Integer userId) {
        Yonghu account = yonghuService.findById(userId);
        return account;
    }
}
