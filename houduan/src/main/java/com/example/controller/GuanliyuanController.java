package com.example.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.entity.Guanliyuan;
import com.example.exception.CustomException;
import com.example.service.GuanliyuanService;
import com.example.utils.MapWrapperUtils;
import com.example.utils.jwt.JwtUtil;
import com.example.vo.GuanliyuanVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/guanliyuan")
public class GuanliyuanController {

    @Resource
    private GuanliyuanService guanliyuanService;

    @PostMapping
    public Result<Guanliyuan> add(@RequestBody GuanliyuanVo guanliyuan) {
        guanliyuanService.add(guanliyuan);
           return Result.success(guanliyuan);
    }

    @PostMapping("/deleteList")
    public Result<Guanliyuan> deleteList(@RequestBody GuanliyuanVo guanliyuan) {
        guanliyuanService.deleteList(guanliyuan.getList());
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        guanliyuanService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody GuanliyuanVo guanliyuan) {
        guanliyuanService.update(guanliyuan);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Guanliyuan> detail(@PathVariable Integer id) {
        Guanliyuan guanliyuan = guanliyuanService.findById(id);
        return Result.success(guanliyuan);
    }

    @GetMapping
    public Result<List<Guanliyuan>> all() {
        return Result.success(guanliyuanService.list());
    }

    @PostMapping("/page")
    public Result<GuanliyuanVo> page(@RequestBody GuanliyuanVo guanliyuanVo) {
        return Result.success(guanliyuanService.findPage(guanliyuanVo));
    }
	    @PostMapping("/login")
    public Result login(@RequestBody Guanliyuan guanliyuan, HttpServletRequest request) {
        if (StrUtil.isBlank(guanliyuan.getYonghuming()) || StrUtil.isBlank(guanliyuan.getMima())) {
            throw new CustomException(ResultCode.PARAM_LOST_ERROR);
        }
        Guanliyuan login = guanliyuanService.login(guanliyuan);
//        if(!login.getStatus()){
//            return Result.error("1001","状态限制，无法登录系统");
//        }
        if(login != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("user", login);
            Map<String, Object> map = MapWrapperUtils.builder(MapWrapperUtils.KEY_USER_ID,guanliyuan.getId());
            String token = JwtUtil.creatToken(map);
            hashMap.put("token", token);
            return Result.success(hashMap);
        }else {
            return Result.error();
        }
    }
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody Guanliyuan info, HttpServletRequest request) {
        Guanliyuan guanliyuan = guanliyuanService.findById(info.getId());
        String oldPassword = SecureUtil.md5(info.getMima());
        if (!oldPassword.equals(guanliyuan.getMima())) {
            return Result.error(ResultCode.PARAM_PASSWORD_ERROR.code, ResultCode.PARAM_PASSWORD_ERROR.msg);
        }
        info.setMima(SecureUtil.md5(info.getNewPassword()));
        Guanliyuan guanliyuan1 = new Guanliyuan();
        BeanUtils.copyProperties(info, guanliyuan1);
        guanliyuanService.update(guanliyuan1);
        return Result.success();
    }
}
