package com.example.controller;

import com.example.exception.CustomException;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.entity.*;
import com.example.service.*;
import com.example.service.impl.CollectServiceImpl;
import com.example.vo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/front")
public class FrontController {
    @Autowired
    private HanfuhuodongService hanfuhuodongService;
    @Autowired
    private ShangpinService shangpinService;
    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private KepuxinxiService kepuxinxiService;
    @Autowired
    private HanfuleixingService hanfuleixingService;

    @Autowired
    private PinglunService pinglunService;
    @Autowired
    private BianhuantuService bianhuantuService;
    @Autowired
    private XinwentongzhiService xinwentongzhiService;
    @Autowired
    private LiuyanbanService liuyanbanService;
    @Resource
    private CollectServiceImpl collectService;
    @Resource
    private OrderInfoService orderInfoService;
    @Resource
    private CartService cartService;

    @Autowired
    private BankuaiService  bankuaiService;
    @Autowired
    private TieziService  tieziService;


    @PostMapping("/hanfuhuodong")
    public Result<HanfuhuodongVo> hanfuhuodong(@RequestBody HanfuhuodongVo hanfuhuodongVo) {
        return Result.success(hanfuhuodongService.findPage(hanfuhuodongVo));
    }

    //以下是论坛专用
    @PostMapping("/bankuai")
    public Result<BankuaiVo> bankuai(@RequestBody BankuaiVo bankuaiVo) {
        return Result.success(bankuaiService.findPage(bankuaiVo));
    }
    @GetMapping("/gettiezidetail/{id}")
    public Result<Tiezi> gettiezidetail(@PathVariable Integer id) {
        return Result.success(tieziService.findById(id));
    }
    @PostMapping("/tiezi")
    public Result<TieziVo> tiezi(@RequestBody TieziVo tieziVo) {
        return Result.success(tieziService.findPage(tieziVo));
    }
    @PutMapping("/tiezi")
    public Result tieziupdt(@RequestBody Tiezi tiezi) {
        tieziService.update(tiezi);
        return Result.success();
    }
    @PutMapping("/bankuai")
    public Result bankuaiupdt(@RequestBody Bankuai bankuai) {
        bankuaiService.update(bankuai);
        return Result.success();
    }
    @GetMapping("/tiezi")
    public Result tiezi() {
        return Result.success(tieziService.list());
    }
    @PostMapping("/posttiezi")
    public Result<Tiezi> posttiezi(@RequestBody Tiezi tiezi) {
        return Result.success(tieziService.add(tiezi));
    }
    @PostMapping("/addCart")
    public Result<Cart> add(@RequestBody Cart detailInfo) {
        return Result.success(cartService.add(detailInfo));
    }

    @DeleteMapping("/cart/deleteGoods/{userId}/{goodsId}")
    public Result deleteGoods(@PathVariable Integer userId, @PathVariable Integer goodsId) {
        cartService.deleteGoods(userId, goodsId);
        return Result.success();
    }

    @DeleteMapping("/cart/empty/{userId}")
    public Result empty(@PathVariable Integer userId) {
        cartService.empty(userId);
        return Result.success();
    }

    @PostMapping("/addOrder")
    public Result<OrderInfo> addOrder(@RequestBody OrderInfo orderInfo) {
        Integer userId = orderInfo.getUserId();
        List<Shangpin> goodsList = orderInfo.getShangpinList();
        if (userId == null || goodsList == null || goodsList.size() == 0) {
            throw new CustomException(ResultCode.PARAM_ERROR);
        }
        return Result.success(orderInfoService.add(orderInfo));
    }

    @GetMapping("/getUserOrder/{userId}")
    public Result<List<OrderInfo>> getUserOrder(@PathVariable Integer userId) {
        return Result.success(orderInfoService.findAll(userId));
    }

    @GetMapping("/orderStatus/{id}/{status}")
    public Result orderStatus(@PathVariable Integer id, @PathVariable String status) {
        orderInfoService.changeStatus(id, status);
        return Result.success();
    }

    @DeleteMapping("/deleteOrder/{id}")
    public Result deleteOrder(@PathVariable Integer id) {
        orderInfoService.delete(id);
        return Result.success();
    }

    @PostMapping("/getUserCart")
    public Result<List<Cart>> findAll(@RequestBody Cart cart) {
        return Result.success(cartService.findAll(cart.getUserId()));
    }

    @PostMapping("/updateCart")
    public Result<Cart> update(@RequestBody Cart detailInfo) {
        if (detailInfo.getId() == null) {
            throw new CustomException(ResultCode.PARAM_ERROR);
        }
        return Result.success(cartService.update(detailInfo));
    }

    @DeleteMapping("/deleteCart/{id}")
    public Result deleteCart(@PathVariable Integer id) {
        cartService.delete(id);
        return Result.success();
    }

    @GetMapping("/gethanfuhuodongdetail/{id}")
    public Result gethanfuhuodongdetail(@PathVariable Integer id) {
        return Result.success(hanfuhuodongService.findById(id));
    }

    @GetMapping("/getshangpindetail/{id}")
    public Result getshangpindetail(@PathVariable Integer id) {
        return Result.success(shangpinService.findById(id));
    }

    @PostMapping("/shangpin")
    public Result<ShangpinVo> shangpin(@RequestBody ShangpinVo shangpinVo) {
        return Result.success(shangpinService.findPage(shangpinVo));
    }

    @GetMapping("/getyonghudetail/{id}")
    public Result getyonghudetail(@PathVariable Integer id) {
        return Result.success(yonghuService.findById(id));
    }

    @PostMapping("/yonghu")
    public Result<YonghuVo> yonghu(@RequestBody YonghuVo yonghuVo) {
        return Result.success(yonghuService.findPage(yonghuVo));
    }

    @PostMapping("/loginyonghu")
    public Result loginyonghu(@RequestBody Yonghu yonghu) {
        return Result.success(yonghuService.login(yonghu));
    }

    @GetMapping("/getkepuxinxidetail/{id}")
    public Result getkepuxinxidetail(@PathVariable Integer id) {
        return Result.success(kepuxinxiService.findById(id));
    }

    @PostMapping("/kepuxinxi")
    public Result<KepuxinxiVo> kepuxinxi(@RequestBody KepuxinxiVo kepuxinxiVo) {
        return Result.success(kepuxinxiService.findPage(kepuxinxiVo));
    }


    @PostMapping("/postpinglun")
    public Result<Pinglun> postpinglun(@RequestBody Pinglun pinglun) {
        return Result.success(pinglunService.add(pinglun));
    }

    @PostMapping("/pinglun")
    public Result<PinglunVo> pinglun(@RequestBody PinglunVo pinglunVo) {
        return Result.success(pinglunService.findPage(pinglunVo));
    }

    @GetMapping("/bianhuantu")
    public Result bianhuantu() {
        return Result.success(bianhuantuService.list());
    }

    @GetMapping("/xinwentongzhi")
    public Result xinwentongzhi() {
        return Result.success(xinwentongzhiService.list());
    }

    @GetMapping("/getxinwentongzhi/{id}")
    public Result getxinwentongzhi(@PathVariable Integer id) {
        return Result.success(xinwentongzhiService.findById(id));
    }

    @PostMapping("/addLiuyan")
    public Result addComment(@RequestBody Liuyanban liuyanban) {
        liuyanbanService.add(liuyanban);
        return Result.success();
    }

    @GetMapping("/getLiuyan")
    public Result<List<Liuyanban>> getCommentTiezi() {
        return Result.success(liuyanbanService.getLiuyan());
    }

    @PostMapping("/addCollect")
    public Result addCollect(@RequestBody Collect collect) {
        Collect collect1 = collectService.add(collect);
        if (collect1 == null) {
            return Result.success("请勿重复收藏");
        }
        return Result.success();
    }

    @DeleteMapping("/deleteCollect/{id}")
    public Result deleteCollect(@PathVariable Integer id) {
        collectService.delete(id);
        return Result.success();
    }

    @PostMapping("/getUserCollect")
    public Result getUserCollect(@RequestBody Collect collect) {
        return Result.success(collectService.findFrontPages(collect.getUserId()));
    }

    @DeleteMapping("/deleteCollect/{id}/{userId}")
    public Result deleteCollect(@PathVariable Integer id, @PathVariable Integer userId) {
        collectService.deleteByUserId(id, userId);
        return Result.success();
    }

    @PutMapping("/hanfuhuodong")
    public Result updateHanfuhuodong(@RequestBody HanfuhuodongVo hanfuhuodong) {
        hanfuhuodongService.update(hanfuhuodong);
        return Result.success();
    }

    @PutMapping("/shangpin")
    public Result updateShangpin(@RequestBody ShangpinVo shangpin) {
        shangpinService.update(shangpin);
        return Result.success();
    }

    @PutMapping("/yonghu")
    public Result updateYonghu(@RequestBody YonghuVo yonghu) {
        yonghuService.update(yonghu);
        return Result.success();
    }

    @PutMapping("/kepuxinxi")
    public Result updateKepuxinxi(@RequestBody KepuxinxiVo kepuxinxi) {
        kepuxinxiService.update(kepuxinxi);
        return Result.success();
    }

    @PutMapping("/hanfuleixing")
    public Result updateHanfuleixing(@RequestBody HanfuleixingVo hanfuleixing) {
        hanfuleixingService.update(hanfuleixing);
        return Result.success();
    }


}
