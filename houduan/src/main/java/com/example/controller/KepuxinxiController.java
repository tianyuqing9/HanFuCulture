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
import com.example.entity.Kepuxinxi;
import com.example.exception.CustomException;
import com.example.service.KepuxinxiService;
import com.example.utils.MapWrapperUtils;
import com.example.utils.jwt.JwtUtil;
import com.example.vo.KepuxinxiVo;
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
@RequestMapping(value = "/kepuxinxi")
public class KepuxinxiController {

    @Resource
    private KepuxinxiService kepuxinxiService;

    @PostMapping
    public Result<Kepuxinxi> add(@RequestBody KepuxinxiVo kepuxinxi) {
		//yoxuxiaxnci
        kepuxinxiService.add(kepuxinxi);
        return Result.success(kepuxinxi);
    }
	
	
    @GetMapping("/getExcel")
    public void getExcel(HttpServletResponse response) throws IOException {
        // 1. 生成excel
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("kepubianhao","科普编号");        row.put("kepumingcheng","科普名称");        row.put("kepuleixing","科普类型");        row.put("neirong","内容");        row.put("shangchuanshijian","上传时间");        row.put("beizhu","备注");        

        row.put("addtime","添加时间");
        List<Map<String, Object>> list = CollUtil.newArrayList(row);
        List<Map<String, Object>> daochuexcellist = kepuxinxiService.daochuexcel();
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
	
	@GetMapping("/kepuxinxi_tj_kepuleixing")    public Result kepuxinxi_tj_kepuleixing() {        return Result.success(kepuxinxiService.kepuxinxi_tj_kepuleixing());    }    

    @PostMapping("/deleteList")
    public Result<Kepuxinxi> deleteList(@RequestBody KepuxinxiVo kepuxinxi) {
        kepuxinxiService.deleteList(kepuxinxi.getList());
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        kepuxinxiService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody KepuxinxiVo kepuxinxi) {
        kepuxinxiService.update(kepuxinxi);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Kepuxinxi> detail(@PathVariable Integer id) {
        Kepuxinxi kepuxinxi = kepuxinxiService.findById(id);
        return Result.success(kepuxinxi);
    }

    @GetMapping
    public Result<List<Kepuxinxi>> all() {
        return Result.success(kepuxinxiService.list());
    }

    @PostMapping("/page")
    public Result<KepuxinxiVo> page(@RequestBody KepuxinxiVo kepuxinxiVo) {
        return Result.success(kepuxinxiService.findPage(kepuxinxiVo));
    }
	
	@PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) throws IOException {
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
        List<Kepuxinxi> kepuxinxis = reader.readAll(Kepuxinxi.class);
		
        kepuxinxiService.saveBatch(kepuxinxis);
        return Result.success();
    }
	
	//youupdt2login
}
