package com.example.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.common.Result;
import com.example.utils.TextUtils;
import com.example.utils.jwt.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse resp, Object handler) throws Exception {
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            String token = request.getHeader("token");
            if (TextUtils.isEmpty(token)) {
                makeResponse(resp, Result.error("-1", "请携带token"));
                return false;
            }
            Result returnData = JwtUtil.valid(token);
            if (returnData.getCode().equals("0")) {
                return true;
            }
            Long id = (Long) returnData.getData();
            if (id == null) {
                makeResponse(resp, Result.error("-1", "token有误"));
                return false;
            }

        return true;
    }
    private void makeResponse(HttpServletResponse resp, Result returnData) {
        try {
            resp.getWriter().print(JSONObject.toJSONString(returnData));
            resp.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
