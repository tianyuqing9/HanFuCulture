package com.example.service;

import com.example.entity.HanfuNews;
import java.util.List;
import java.util.Map;
public interface HanfuNewsService {
    // 获取所有新闻记录
    List<HanfuNews> getAllHanfuNews();

    // 导出数据（例如：导出 Excel）
    List<Map<String, Object>> daochuExcel();
}
