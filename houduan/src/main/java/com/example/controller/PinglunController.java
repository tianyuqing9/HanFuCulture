package com.example.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.entity.Pinglun;
import com.example.exception.CustomException;
import com.example.service.PinglunService;
import com.example.utils.MapWrapperUtils;
import com.example.utils.jwt.JwtUtil;
import com.example.vo.PinglunVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/pinglun")
public class PinglunController {

    @Resource
    private PinglunService pinglunService;

    @PostMapping
    public Result<Pinglun> add(@RequestBody PinglunVo pinglun) {
        pinglunService.add(pinglun);
           return Result.success(pinglun);
    }
	
	

    @PostMapping("/deleteList")
    public Result<Pinglun> deleteList(@RequestBody PinglunVo pinglun) {
        pinglunService.deleteList(pinglun.getList());
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        pinglunService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody PinglunVo pinglun) {
        pinglunService.update(pinglun);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Pinglun> detail(@PathVariable Integer id) {
        Pinglun pinglun = pinglunService.findById(id);
        return Result.success(pinglun);
    }

    @GetMapping
    public Result<List<Pinglun>> all() {
        return Result.success(pinglunService.list());
    }

    @PostMapping("/page")
    public Result<PinglunVo> page(@RequestBody PinglunVo pinglunVo) {
        return Result.success(pinglunService.findPage(pinglunVo));
    }
	//youupdt2login
}
