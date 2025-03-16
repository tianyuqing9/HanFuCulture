package com.example.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.entity.Liuyanban;
import com.example.exception.CustomException;
import com.example.service.LiuyanbanService;
import com.example.utils.MapWrapperUtils;
import com.example.utils.jwt.JwtUtil;
import com.example.vo.LiuyanbanVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/liuyanban")
public class LiuyanbanController {

    @Resource
    private LiuyanbanService liuyanbanService;

    @PostMapping
    public Result<Liuyanban> add(@RequestBody LiuyanbanVo liuyanban) {
        liuyanbanService.add(liuyanban);
           return Result.success(liuyanban);
    }

    @PostMapping("/deleteList")
    public Result<Liuyanban> deleteList(@RequestBody LiuyanbanVo liuyanban) {
        liuyanbanService.deleteList(liuyanban.getList());
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        liuyanbanService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody LiuyanbanVo liuyanban) {
        liuyanbanService.update(liuyanban);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Liuyanban> detail(@PathVariable Integer id) {
        Liuyanban liuyanban = liuyanbanService.findById(id);
        return Result.success(liuyanban);
    }

    @GetMapping
    public Result<List<Liuyanban>> all() {
        return Result.success(liuyanbanService.list());
    }

    @PostMapping("/page")
    public Result<LiuyanbanVo> page(@RequestBody LiuyanbanVo liuyanbanVo) {
        return Result.success(liuyanbanService.findPage(liuyanbanVo));
    }
	//youupdt2login
}
