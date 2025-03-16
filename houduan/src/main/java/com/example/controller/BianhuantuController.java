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
import com.example.entity.Bianhuantu;
import com.example.exception.CustomException;
import com.example.service.BianhuantuService;
import com.example.utils.MapWrapperUtils;
import com.example.utils.jwt.JwtUtil;
import com.example.vo.BianhuantuVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping(value = "/bianhuantu")
public class BianhuantuController {

    @Resource
    private BianhuantuService bianhuantuService;

    @PostMapping
    public Result<Bianhuantu> add(@RequestBody BianhuantuVo bianhuantu) {
        bianhuantuService.add(bianhuantu);
           return Result.success(bianhuantu);
    }
	
	
    @GetMapping("/getExcel")
    public void getExcel(HttpServletResponse response) throws IOException {
        // 1. 生成excel
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("mingcheng","名称");        

        row.put("addtime","添加时间");
        List<Map<String, Object>> list = CollUtil.newArrayList(row);
        List<Map<String, Object>> daochuexcellist = bianhuantuService.daochuexcel();
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
	
	

    @PostMapping("/deleteList")
    public Result<Bianhuantu> deleteList(@RequestBody BianhuantuVo bianhuantu) {
        bianhuantuService.deleteList(bianhuantu.getList());
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        bianhuantuService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody BianhuantuVo bianhuantu) {
        bianhuantuService.update(bianhuantu);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Bianhuantu> detail(@PathVariable Integer id) {
        Bianhuantu bianhuantu = bianhuantuService.findById(id);
        return Result.success(bianhuantu);
    }

    @GetMapping
    public Result<List<Bianhuantu>> all() {
        return Result.success(bianhuantuService.list());
    }

    @PostMapping("/page")
    public Result<BianhuantuVo> page(@RequestBody BianhuantuVo bianhuantuVo) {
        return Result.success(bianhuantuService.findPage(bianhuantuVo));
    }
	//youupdt2login
}
