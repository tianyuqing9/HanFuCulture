package com.example.controller;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.entity.Yonghuxinxi;
import com.example.exception.CustomException;
import com.example.service.YonghuxinxiService;
import com.example.utils.MapWrapperUtils;
import com.example.utils.jwt.JwtUtil;
import com.example.vo.YonghuxinxiVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import cn.hutool.poi.excel.ExcelReader;
import org.springframework.web.multipart.MultipartFile;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/yonghuxinxi")
public class YonghuxinxiController {

    @Resource
    private YonghuxinxiService yonghuxinxiService;

    @PostMapping
    public Result<Yonghuxinxi> add(@RequestBody YonghuxinxiVo yonghuxinxi) {
		//yoxuxiaxnci
        yonghuxinxiService.add(yonghuxinxi);
        return Result.success(yonghuxinxi);
    }
	
	
    @GetMapping("/getExcel")
    public void getExcel(HttpServletResponse response) throws IOException {
        // 1. 生成excel
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("yonghubianhao","用户编号");        row.put("xingming","姓名");        row.put("xingbie","性别");        row.put("shenfenzheng","身份证");        row.put("dianhua","电话");        row.put("beizhu","备注");        

        row.put("addtime","添加时间");
        List<Map<String, Object>> list = CollUtil.newArrayList(row);
        List<Map<String, Object>> daochuexcellist = yonghuxinxiService.daochuexcel();
        for (Map<String, Object> map : daochuexcellist) {
            list.add(map);
        }
        // 2. 写excel
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename=chaoba.xlsx");
        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(System.out);
    }
	
	@GetMapping("/yonghuxinxi_tj_xingbie")    public Result yonghuxinxi_tj_xingbie() {        return Result.success(yonghuxinxiService.yonghuxinxi_tj_xingbie());    }    

    @PostMapping("/deleteList")
    public Result<Yonghuxinxi> deleteList(@RequestBody YonghuxinxiVo yonghuxinxi) {
        yonghuxinxiService.deleteList(yonghuxinxi.getList());
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        yonghuxinxiService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody YonghuxinxiVo yonghuxinxi) {
        yonghuxinxiService.update(yonghuxinxi);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Yonghuxinxi> detail(@PathVariable Integer id) {
        Yonghuxinxi yonghuxinxi = yonghuxinxiService.findById(id);
        return Result.success(yonghuxinxi);
    }

    @GetMapping
    public Result<List<Yonghuxinxi>> all() {
        return Result.success(yonghuxinxiService.list());
    }

    @PostMapping("/page")
    public Result<YonghuxinxiVo> page(@RequestBody YonghuxinxiVo yonghuxinxiVo) {
        return Result.success(yonghuxinxiService.findPage(yonghuxinxiVo));
    }
	
	@PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) throws IOException {
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
        List<Yonghuxinxi> yonghuxinxis = reader.readAll(Yonghuxinxi.class);
		for (Yonghuxinxi yonghuxinxi : yonghuxinxis) { yonghuxinxi.setMima("dc5c7986daef50c1e02ab09b442ee34f");}
        yonghuxinxiService.saveBatch(yonghuxinxis);
        return Result.success();
    }
	
	    @PostMapping("/login")
    public Result login(@RequestBody Yonghuxinxi yonghuxinxi, HttpServletRequest request) {
        if (StrUtil.isBlank(yonghuxinxi.getYonghubianhao()) || StrUtil.isBlank(yonghuxinxi.getMima())) {
            throw new CustomException(ResultCode.PARAM_LOST_ERROR);
        }
        Yonghuxinxi login = yonghuxinxiService.login(yonghuxinxi);
//        if(!login.getStatus()){
//            return Result.error("1001","状态限制，无法登录系统");
//        }
        if(login != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("user", login);
            Map<String, Object> map = MapWrapperUtils.builder(MapWrapperUtils.KEY_USER_ID,yonghuxinxi.getId());
            String token = JwtUtil.creatToken(map);
            hashMap.put("token", token);
            return Result.success(hashMap);
        }else {
            return Result.error();
        }
    }
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody Yonghuxinxi info, HttpServletRequest request) {
        Yonghuxinxi yonghuxinxi = yonghuxinxiService.findById(info.getId());
        String oldPassword = SecureUtil.md5(info.getMima());
        if (!oldPassword.equals(yonghuxinxi.getMima())) {
            return Result.error(ResultCode.PARAM_PASSWORD_ERROR.code, ResultCode.PARAM_PASSWORD_ERROR.msg);
        }
        info.setMima(SecureUtil.md5(info.getNewPassword()));
        Yonghuxinxi yonghuxinxi1 = new Yonghuxinxi();
        BeanUtils.copyProperties(info, yonghuxinxi1);
        yonghuxinxiService.update(yonghuxinxi1);
        return Result.success();
    }
	@PostMapping("/register")
    public Result<Yonghuxinxi> register(@RequestBody YonghuxinxiVo yonghuxinxi) {
        yonghuxinxiService.add(yonghuxinxi);
        return Result.success(yonghuxinxi);
    }
}
