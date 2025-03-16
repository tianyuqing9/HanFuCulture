package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Hanfuleixing;
import com.example.vo.HanfuleixingVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface HanfuleixingService extends IService<Hanfuleixing> {
    Hanfuleixing add(Hanfuleixing hanfuleixing);
    void delete(Long id);
    void update(Hanfuleixing hanfuleixing);
    Hanfuleixing findById(Integer id);
    HanfuleixingVo findPage(HanfuleixingVo hanfuleixingVo);
	
    void deleteList(List<Hanfuleixing> list);
	
	
	List<Map<String, Object>> daochuexcel();
	//youxxianxci
}
