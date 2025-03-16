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
import com.example.entity.Yonghu;
import com.example.exception.CustomException;
import com.example.service.YonghuService;
import com.example.utils.MapWrapperUtils;
import com.example.utils.jwt.JwtUtil;
import com.example.vo.YonghuVo;
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
@RequestMapping(value = "/yonghu")
public class YonghuController {

    @Resource
    private YonghuService yonghuService;

    @PostMapping
    public Result<Yonghu> add(@RequestBody YonghuVo yonghu) {
		//yoxuxiaxnci
        yonghuService.add(yonghu);
        return Result.success(yonghu);
    }
	
	
    @GetMapping("/getExcel")
    public void getExcel(HttpServletResponse response) throws IOException {
        // 1. 生成excel
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("yonghubianhao","用户编号");        row.put("xingming","姓名");        row.put("xingbie","性别");        row.put("shenfenzheng","身份证");        row.put("dianhua","电话");        row.put("beizhu","备注");        

        row.put("addtime","添加时间");
        List<Map<String, Object>> list = CollUtil.newArrayList(row);
        List<Map<String, Object>> daochuexcellist = yonghuService.daochuexcel();
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
	
	@GetMapping("/yonghu_tj_xingbie")    public Result yonghu_tj_xingbie() {        return Result.success(yonghuService.yonghu_tj_xingbie());    }    

    @PostMapping("/deleteList")
    public Result<Yonghu> deleteList(@RequestBody YonghuVo yonghu) {
        yonghuService.deleteList(yonghu.getList());
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        yonghuService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody YonghuVo yonghu) {
        yonghuService.update(yonghu);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Yonghu> detail(@PathVariable Integer id) {
        Yonghu yonghu = yonghuService.findById(id);
        return Result.success(yonghu);
    }

    @GetMapping
    public Result<List<Yonghu>> all() {
        return Result.success(yonghuService.list());
    }

    @PostMapping("/page")
    public Result<YonghuVo> page(@RequestBody YonghuVo yonghuVo) {
        return Result.success(yonghuService.findPage(yonghuVo));
    }
	
	@PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) throws IOException {
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
        List<Yonghu> yonghus = reader.readAll(Yonghu.class);
		for (Yonghu yonghu : yonghus) { yonghu.setMima("dc5c7986daef50c1e02ab09b442ee34f");}
        yonghuService.saveBatch(yonghus);
        return Result.success();
    }
	
	    @PostMapping("/login")
    public Result login(@RequestBody Yonghu yonghu, HttpServletRequest request) {
        if (StrUtil.isBlank(yonghu.getYonghubianhao()) || StrUtil.isBlank(yonghu.getMima())) {
            throw new CustomException(ResultCode.PARAM_LOST_ERROR);
        }
        Yonghu login = yonghuService.login(yonghu);
//        if(!login.getStatus()){
//            return Result.error("1001","状态限制，无法登录系统");
//        }
        if(login != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("user", login);
            Map<String, Object> map = MapWrapperUtils.builder(MapWrapperUtils.KEY_USER_ID,yonghu.getId());
            String token = JwtUtil.creatToken(map);
            hashMap.put("token", token);
            return Result.success(hashMap);
        }else {
            return Result.error();
        }
    }
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody Yonghu info, HttpServletRequest request) {
        Yonghu yonghu = yonghuService.findById(info.getId());
        String oldPassword = SecureUtil.md5(info.getMima());
        if (!oldPassword.equals(yonghu.getMima())) {
            return Result.error(ResultCode.PARAM_PASSWORD_ERROR.code, ResultCode.PARAM_PASSWORD_ERROR.msg);
        }
        info.setMima(SecureUtil.md5(info.getNewPassword()));
        Yonghu yonghu1 = new Yonghu();
        BeanUtils.copyProperties(info, yonghu1);
        yonghuService.update(yonghu1);
        return Result.success();
    }
	@PostMapping("/register")
    public Result<Yonghu> register(@RequestBody YonghuVo yonghu) {
        yonghuService.add(yonghu);
        return Result.success(yonghu);
    }
}
