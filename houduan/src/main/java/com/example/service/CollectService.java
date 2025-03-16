package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;

import com.example.entity.Collect;

import java.util.List;

public interface CollectService extends IService<Collect> {
    void deleteByUserId(Integer id, Integer userId);
}
