package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Yonghu;
import com.example.vo.YonghuVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface YonghuService extends IService<Yonghu> {
    Yonghu add(Yonghu yonghu);
    void delete(Long id);
    void update(Yonghu yonghu);
    Yonghu findById(Integer id);
    YonghuVo findPage(YonghuVo yonghuVo);
	Yonghu login(Yonghu yonghu);
    void deleteList(List<Yonghu> list);
	List<Map<String,Object>> yonghu_tj_xingbie();    
	
	List<Map<String, Object>> daochuexcel();
	//youxxianxci
}
