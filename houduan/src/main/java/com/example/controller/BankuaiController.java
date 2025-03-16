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
import com.example.entity.Bankuai;
import com.example.exception.CustomException;
import com.example.service.BankuaiService;
import com.example.utils.MapWrapperUtils;
import com.example.utils.jwt.JwtUtil;
import com.example.vo.BankuaiVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping(value = "/bankuai")
public class BankuaiController {

    @Resource
    private BankuaiService bankuaiService;

    @PostMapping
    public Result<Bankuai> add(@RequestBody BankuaiVo bankuai) {
        bankuaiService.add(bankuai);
           return Result.success(bankuai);
    }
	
	
    @GetMapping("/getExcel")
    public void getExcel(HttpServletResponse response) throws IOException {
        // 1. 生成excel
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("bankuaimingcheng","版块名称");        row.put("banzhu","版主");        row.put("yiyoutieshu","已有帖数");        row.put("jianjie","简介");        

        row.put("addtime","添加时间");
        List<Map<String, Object>> list = CollUtil.newArrayList(row);
        List<Map<String, Object>> daochuexcellist = bankuaiService.daochuexcel();
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
    public Result<Bankuai> deleteList(@RequestBody BankuaiVo bankuai) {
        bankuaiService.deleteList(bankuai.getList());
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        bankuaiService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody BankuaiVo bankuai) {
        bankuaiService.update(bankuai);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Bankuai> detail(@PathVariable Integer id) {
        Bankuai bankuai = bankuaiService.findById(id);
        return Result.success(bankuai);
    }

    @GetMapping
    public Result<List<Bankuai>> all() {
        return Result.success(bankuaiService.list());
    }

    @PostMapping("/page")
    public Result<BankuaiVo> page(@RequestBody BankuaiVo bankuaiVo) {
        return Result.success(bankuaiService.findPage(bankuaiVo));
    }
	//youupdt2login
}
