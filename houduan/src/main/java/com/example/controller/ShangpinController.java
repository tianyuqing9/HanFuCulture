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
import com.example.entity.Shangpin;
import com.example.exception.CustomException;
import com.example.service.ShangpinService;
import com.example.utils.MapWrapperUtils;
import com.example.utils.jwt.JwtUtil;
import com.example.vo.ShangpinVo;
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
@RequestMapping(value = "/shangpin")
public class ShangpinController {

    @Resource
    private ShangpinService shangpinService;

    @PostMapping
    public Result<Shangpin> add(@RequestBody ShangpinVo shangpin) {
		//yoxuxiaxnci
        shangpinService.add(shangpin);
        return Result.success(shangpin);
    }
	
	
    @GetMapping("/getExcel")
    public void getExcel(HttpServletResponse response) throws IOException {
        // 1. 生成excel
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("hanfubianhao","汉服编号");        row.put("hanfumingcheng","汉服名称");        row.put("leixingbianhao","类型编号");        row.put("leixingmingcheng","类型名称");        row.put("jiage","价格");        row.put("kucun","库存");        row.put("zhuangtai","状态");        row.put("beizhu","备注");        

        row.put("addtime","添加时间");
        List<Map<String, Object>> list = CollUtil.newArrayList(row);
        List<Map<String, Object>> daochuexcellist = shangpinService.daochuexcel();
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
	
	@GetMapping("/shangpin_tj_leixingbianhao")    public Result shangpin_tj_leixingbianhao() {        return Result.success(shangpinService.shangpin_tj_leixingbianhao());    }    

    @PostMapping("/deleteList")
    public Result<Shangpin> deleteList(@RequestBody ShangpinVo shangpin) {
        shangpinService.deleteList(shangpin.getList());
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        shangpinService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody ShangpinVo shangpin) {
        shangpinService.update(shangpin);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Shangpin> detail(@PathVariable Integer id) {
        Shangpin shangpin = shangpinService.findById(id);
        return Result.success(shangpin);
    }

    @GetMapping
    public Result<List<Shangpin>> all() {
        return Result.success(shangpinService.list());
    }

    @PostMapping("/page")
    public Result<ShangpinVo> page(@RequestBody ShangpinVo shangpinVo) {
        return Result.success(shangpinService.findPage(shangpinVo));
    }
	
	@PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) throws IOException {
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
        List<Shangpin> shangpins = reader.readAll(Shangpin.class);
		
        shangpinService.saveBatch(shangpins);
        return Result.success();
    }
	
	//youupdt2login
}
