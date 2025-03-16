package com.example.controller;

import com.example.entity.HanfuNews;
import com.example.service.HanfuNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hanfu-news")  // 公共接口路径
public class HanfuNewsController {

    @Autowired
    private HanfuNewsService hanfuNewsService;

    /**
     * 获取所有的汉服新闻
     * @param authHeader 请求头中的 token（如果存在）
     * @return 新闻列表 或 错误信息
     */
    @GetMapping("/list")
    public ResponseEntity<List<HanfuNews>> getAllHanfuNews(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        // 如果有 token，进行验证
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // 获取 token

            if (!validateToken(token)) {
                // 如果 token 无效，返回 401 Unauthorized 错误
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        }
        // 无论是否验证 token，直接返回新闻列表
        List<HanfuNews> newsList = hanfuNewsService.getAllHanfuNews();
        return ResponseEntity.ok(newsList);
    }
    /**
     * 验证 token 的有效性
     * @param token token 字符串
     * @return true - token 有效，false - token 无效
     */
    private boolean validateToken(String token) {
        // 这里可以添加具体的 token 验证逻辑，例如使用 JWT 解析库等
        // 此处只是一个简单的示例，假设所有 token 都是有效的
        return token != null && !token.trim().isEmpty();
    }
}
