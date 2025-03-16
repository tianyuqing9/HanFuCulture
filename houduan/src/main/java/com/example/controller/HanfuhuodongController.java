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
import com.example.entity.Hanfuhuodong;
import com.example.exception.CustomException;
import com.example.service.HanfuhuodongService;
import com.example.utils.MapWrapperUtils;
import com.example.utils.jwt.JwtUtil;
import com.example.vo.HanfuhuodongVo;
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
@RequestMapping(value = "/hanfuhuodong")
public class HanfuhuodongController {

    @Resource
    private HanfuhuodongService hanfuhuodongService;

    @PostMapping
    public Result<Hanfuhuodong> add(@RequestBody HanfuhuodongVo hanfuhuodong) {
        //yoxuxiaxnci
        hanfuhuodongService.add(hanfuhuodong);
        return Result.success(hanfuhuodong);
    }


    @GetMapping("/getExcel")
    public void getExcel(HttpServletResponse response) throws IOException {
        // 1. 生成excel
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("huodongbianhao","活动编号");
        row.put("huodongmingcheng","活动名称");
        row.put("huodongleixing","活动类型");
        row.put("shijian","时间");
        row.put("beizhu","备注");


        row.put("addtime","添加时间");
        List<Map<String, Object>> list = CollUtil.newArrayList(row);
        List<Map<String, Object>> daochuexcellist = hanfuhuodongService.daochuexcel();
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
    public Result<Hanfuhuodong> deleteList(@RequestBody HanfuhuodongVo hanfuhuodong) {
        hanfuhuodongService.deleteList(hanfuhuodong.getList());
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        hanfuhuodongService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody HanfuhuodongVo hanfuhuodong) {
        hanfuhuodongService.update(hanfuhuodong);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Hanfuhuodong> detail(@PathVariable Integer id) {
        Hanfuhuodong hanfuhuodong = hanfuhuodongService.findById(id);
        return Result.success(hanfuhuodong);
    }

    @GetMapping
    public Result<List<Hanfuhuodong>> all() {
        return Result.success(hanfuhuodongService.list());
    }

    @PostMapping("/page")
    public Result<HanfuhuodongVo> page(@RequestBody HanfuhuodongVo hanfuhuodongVo) {
        return Result.success(hanfuhuodongService.findPage(hanfuhuodongVo));
    }

    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) throws IOException {
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
        List<Hanfuhuodong> hanfuhuodongs = reader.readAll(Hanfuhuodong.class);

        hanfuhuodongService.saveBatch(hanfuhuodongs);
        return Result.success();
    }

    //youupdt2login
}
