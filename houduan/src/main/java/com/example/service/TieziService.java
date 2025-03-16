package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Tiezi;
import com.example.vo.TieziVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface TieziService extends IService<Tiezi> {
    Tiezi add(Tiezi tiezi);
    void delete(Long id);
    void update(Tiezi tiezi);
    Tiezi findById(Integer id);
    TieziVo findPage(TieziVo tieziVo);
	
    void deleteList(List<Tiezi> list);
	
	
	List<Map<String, Object>> daochuexcel();
}
