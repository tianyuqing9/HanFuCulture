package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Bankuai;
import com.example.vo.BankuaiVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface BankuaiService extends IService<Bankuai> {
    Bankuai add(Bankuai bankuai);
    void delete(Long id);
    void update(Bankuai bankuai);
    Bankuai findById(Integer id);
    BankuaiVo findPage(BankuaiVo bankuaiVo);
	
    void deleteList(List<Bankuai> list);
	
	
	List<Map<String, Object>> daochuexcel();
}
