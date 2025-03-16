package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Bianhuantu;
import com.example.vo.BianhuantuVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface BianhuantuService extends IService<Bianhuantu> {
    Bianhuantu add(Bianhuantu bianhuantu);
    void delete(Long id);
    void update(Bianhuantu bianhuantu);
    Bianhuantu findById(Integer id);
    BianhuantuVo findPage(BianhuantuVo bianhuantuVo);
	
    void deleteList(List<Bianhuantu> list);
	
	
	List<Map<String, Object>> daochuexcel();
}
