package com.example.service.impl;

import com.example.dao.HanfuNewsDao;
import com.example.entity.HanfuNews;
import com.example.service.HanfuNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class HanfuNewsServiceImpl implements HanfuNewsService {

    @Autowired
    private HanfuNewsDao hanfuNewsDao;

    @Override
    public List<HanfuNews> getAllHanfuNews() {
        // 通过 DAO 层获取所有新闻记录
        return hanfuNewsDao.selectList(null); // 这里直接调用 MyBatis-Plus 提供的查询方法，获取所有记录
    }

    @Override
    public List<Map<String, Object>> daochuExcel() {
        // 通过 DAO 层自定义查询获取数据
        return hanfuNewsDao.daochuexcel();
    }
}