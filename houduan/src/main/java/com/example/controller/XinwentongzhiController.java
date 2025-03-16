package com.example.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.entity.Xinwentongzhi;
import com.example.exception.CustomException;
import com.example.service.XinwentongzhiService;
import com.example.utils.MapWrapperUtils;
import com.example.utils.jwt.JwtUtil;
import com.example.vo.XinwentongzhiVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/xinwentongzhi")
public class XinwentongzhiController {

    @Resource
    private XinwentongzhiService xinwentongzhiService;

    @PostMapping
    public Result<Xinwentongzhi> add(@RequestBody XinwentongzhiVo xinwentongzhi) {
        xinwentongzhiService.add(xinwentongzhi);
           return Result.success(xinwentongzhi);
    }
	
	

    @PostMapping("/deleteList")
    public Result<Xinwentongzhi> deleteList(@RequestBody XinwentongzhiVo xinwentongzhi) {
        xinwentongzhiService.deleteList(xinwentongzhi.getList());
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        xinwentongzhiService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody XinwentongzhiVo xinwentongzhi) {
        xinwentongzhiService.update(xinwentongzhi);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Xinwentongzhi> detail(@PathVariable Integer id) {
        Xinwentongzhi xinwentongzhi = xinwentongzhiService.findById(id);
        return Result.success(xinwentongzhi);
    }

    @GetMapping
    public Result<List<Xinwentongzhi>> all() {
        return Result.success(xinwentongzhiService.list());
    }

    @PostMapping("/page")
    public Result<XinwentongzhiVo> page(@RequestBody XinwentongzhiVo xinwentongzhiVo) {
        return Result.success(xinwentongzhiService.findPage(xinwentongzhiVo));
    }
	//youupdt2login
}
