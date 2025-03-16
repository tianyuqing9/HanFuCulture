package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Yonghuxinxi;
import com.example.vo.YonghuxinxiVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface YonghuxinxiService extends IService<Yonghuxinxi> {
    Yonghuxinxi add(Yonghuxinxi yonghuxinxi);
    void delete(Long id);
    void update(Yonghuxinxi yonghuxinxi);
    Yonghuxinxi findById(Integer id);
    YonghuxinxiVo findPage(YonghuxinxiVo yonghuxinxiVo);
	Yonghuxinxi login(Yonghuxinxi yonghuxinxi);
    void deleteList(List<Yonghuxinxi> list);
	List<Map<String,Object>> yonghuxinxi_tj_xingbie();    
	
	List<Map<String, Object>> daochuexcel();
	//youxxianxci
}
