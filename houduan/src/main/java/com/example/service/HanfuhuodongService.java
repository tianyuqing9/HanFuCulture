package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Hanfuhuodong;
import com.example.vo.HanfuhuodongVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface HanfuhuodongService extends IService<Hanfuhuodong> {
    Hanfuhuodong add(Hanfuhuodong hanfuhuodong);
    void delete(Long id);
    void update(Hanfuhuodong hanfuhuodong);
    Hanfuhuodong findById(Integer id);
    HanfuhuodongVo findPage(HanfuhuodongVo hanfuhuodongVo);
	
    void deleteList(List<Hanfuhuodong> list);
	
	
	List<Map<String, Object>> daochuexcel();
	//youxxianxci
}
