package com.example.controller;

import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.entity.OrderInfo;
import com.example.entity.Shangpin;
import com.example.exception.CustomException;
import com.example.service.OrderInfoService;
import com.example.vo.OrderInfoVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/orderInfo")
public class OrderInfoController {
    @Resource
    private OrderInfoService orderInfoService;

    /**
     * 根据订单id查询
     */
    @GetMapping("/{orderId}")
    public Result<OrderInfo> findByOrderId(@PathVariable Integer orderId) {
        return Result.success(orderInfoService.findByOrderId(orderId));
    }


    /**
     * 查询所有信息（不分页）
     */
    @GetMapping
    public Result<List<OrderInfo>> findAll(@RequestParam Integer userId) {
        return Result.success(orderInfoService.findAll(userId));
    }
    @GetMapping("/orderEcharts")
    public Result<List<Map<String, Object>>> orderEcharts() {
        return Result.success(orderInfoService.orderEcharts());
    }

    /**
     * 查询所有信息（分页）
     */
    @GetMapping("/page")
    public Result<OrderInfoVo> findPage(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                        @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                        HttpServletRequest request) {
        return Result.success(orderInfoService.findEndPages(pageNum, pageSize, request));
    }

    /**
     * 下单
     *
     * @return
     */
    @PostMapping
    public Result<OrderInfo> add(@RequestBody OrderInfo orderInfo) {
        Integer userId = orderInfo.getUserId();
        List<Shangpin> goodsList = orderInfo.getShangpinList();
        if (userId == null || goodsList == null || goodsList.size() == 0) {
            throw new CustomException(ResultCode.PARAM_ERROR);
        }
        return Result.success(orderInfoService.add(orderInfo));
    }


    /**
     * 修改订单状态
     */
    @PostMapping("/status/{id}/{status}")
    public Result status(@PathVariable Integer id, @PathVariable String status) {
        orderInfoService.changeStatus(id, status);
        return Result.success();
    }


    /**
     * 删除历史订单
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        orderInfoService.delete(id);
        return Result.success();
    }

    @DeleteMapping()
    public Result delete(@RequestParam Integer goodsId, @RequestParam Integer id) {
        orderInfoService.deleteGoods(goodsId, id);
        return Result.success();
    }
}
