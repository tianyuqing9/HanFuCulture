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
import com.example.entity.Tiezi;
import com.example.exception.CustomException;
import com.example.service.TieziService;
import com.example.utils.MapWrapperUtils;
import com.example.utils.jwt.JwtUtil;
import com.example.vo.TieziVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping(value = "/tiezi")
public class TieziController {

    @Resource
    private TieziService tieziService;

    @PostMapping
    public Result<Tiezi> add(@RequestBody TieziVo tiezi) {
        tieziService.add(tiezi);
           return Result.success(tiezi);
    }
	
	
    @GetMapping("/getExcel")
    public void getExcel(HttpServletResponse response) throws IOException {
        // 1. 生成excel
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("suoshubankuai","所属版块");        row.put("biaoti","标题");        row.put("neirong","内容");        row.put("leixing","类型");        row.put("fatieren","发帖人");        row.put("fid","fid");        

        row.put("addtime","添加时间");
        List<Map<String, Object>> list = CollUtil.newArrayList(row);
        List<Map<String, Object>> daochuexcellist = tieziService.daochuexcel();
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
    public Result<Tiezi> deleteList(@RequestBody TieziVo tiezi) {
        tieziService.deleteList(tiezi.getList());
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        tieziService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody TieziVo tiezi) {
        tieziService.update(tiezi);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Tiezi> detail(@PathVariable Integer id) {
        Tiezi tiezi = tieziService.findById(id);
        return Result.success(tiezi);
    }

    @GetMapping
    public Result<List<Tiezi>> all() {
        return Result.success(tieziService.list());
    }

    @PostMapping("/page")
    public Result<TieziVo> page(@RequestBody TieziVo tieziVo) {
        return Result.success(tieziService.findPage(tieziVo));
    }
	//youupdt2login
}
