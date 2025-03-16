package com.example.controller;

import com.example.common.Result;
import com.example.entity.Collect;
import com.example.service.impl.CollectServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/collect")
public class CollectController {
    @Resource
    private CollectServiceImpl collectService;

    @PostMapping
    public Result<Collect> add(@RequestBody Collect collect) {
        Collect collect1 =collectService.add(collect);
        if(collect1  == null){
            return Result.success("请勿重复收藏");
        }
        return Result.success(collect);
    }

    @PutMapping
    public Result update(@RequestBody Collect collect) {
        collectService.update(collect);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Collect> detail(@PathVariable Long id) {
        Collect collect = collectService.findById(id);
        return Result.success(collect);
    }
}
