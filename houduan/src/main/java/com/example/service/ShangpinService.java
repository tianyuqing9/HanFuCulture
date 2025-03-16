package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Shangpin;
import com.example.vo.ShangpinVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ShangpinService extends IService<Shangpin> {
    Shangpin add(Shangpin shangpin);
    void delete(Long id);
    void update(Shangpin shangpin);
    Shangpin findById(Integer id);
    ShangpinVo findPage(ShangpinVo shangpinVo);
	
    void deleteList(List<Shangpin> list);
	List<Map<String,Object>> shangpin_tj_leixingbianhao();    
	
	List<Map<String, Object>> daochuexcel();
	//youxxianxci
}
