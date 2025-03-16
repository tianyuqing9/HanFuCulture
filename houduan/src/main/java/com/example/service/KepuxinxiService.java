package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Kepuxinxi;
import com.example.vo.KepuxinxiVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface KepuxinxiService extends IService<Kepuxinxi> {
    Kepuxinxi add(Kepuxinxi kepuxinxi);
    void delete(Long id);
    void update(Kepuxinxi kepuxinxi);
    Kepuxinxi findById(Integer id);
    KepuxinxiVo findPage(KepuxinxiVo kepuxinxiVo);
	
    void deleteList(List<Kepuxinxi> list);
	List<Map<String,Object>> kepuxinxi_tj_kepuleixing();    
	
	List<Map<String, Object>> daochuexcel();
	//youxxianxci
}
