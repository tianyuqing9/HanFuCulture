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
import com.example.entity.Hanfuleixing;
import com.example.exception.CustomException;
import com.example.service.HanfuleixingService;
import com.example.utils.MapWrapperUtils;
import com.example.utils.jwt.JwtUtil;
import com.example.vo.HanfuleixingVo;
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
@RequestMapping(value = "/hanfuleixing")
public class HanfuleixingController {

    @Resource
    private HanfuleixingService hanfuleixingService;

    @PostMapping
    public Result<Hanfuleixing> add(@RequestBody HanfuleixingVo hanfuleixing) {
		//yoxuxiaxnci
        hanfuleixingService.add(hanfuleixing);
        return Result.success(hanfuleixing);
    }
	
	
    @GetMapping("/getExcel")
    public void getExcel(HttpServletResponse response) throws IOException {
        // 1. 生成excel
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("leixingbianhao","类型编号");        row.put("leixingmingcheng","类型名称");        

        row.put("addtime","添加时间");
        List<Map<String, Object>> list = CollUtil.newArrayList(row);
        List<Map<String, Object>> daochuexcellist = hanfuleixingService.daochuexcel();
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
    public Result<Hanfuleixing> deleteList(@RequestBody HanfuleixingVo hanfuleixing) {
        hanfuleixingService.deleteList(hanfuleixing.getList());
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        hanfuleixingService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody HanfuleixingVo hanfuleixing) {
        hanfuleixingService.update(hanfuleixing);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Hanfuleixing> detail(@PathVariable Integer id) {
        Hanfuleixing hanfuleixing = hanfuleixingService.findById(id);
        return Result.success(hanfuleixing);
    }

    @GetMapping
    public Result<List<Hanfuleixing>> all() {
        return Result.success(hanfuleixingService.list());
    }

    @PostMapping("/page")
    public Result<HanfuleixingVo> page(@RequestBody HanfuleixingVo hanfuleixingVo) {
        return Result.success(hanfuleixingService.findPage(hanfuleixingVo));
    }
	
	@PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) throws IOException {
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
        List<Hanfuleixing> hanfuleixings = reader.readAll(Hanfuleixing.class);
		
        hanfuleixingService.saveBatch(hanfuleixings);
        return Result.success();
    }
	
	//youupdt2login
}
