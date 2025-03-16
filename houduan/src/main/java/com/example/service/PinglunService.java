package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Pinglun;
import com.example.vo.PinglunVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface PinglunService extends IService<Pinglun> {
    Pinglun add(Pinglun pinglun);
    void delete(Long id);
    void update(Pinglun pinglun);
    Pinglun findById(Integer id);
    PinglunVo findPage(PinglunVo pinglunVo);
	
    void deleteList(List<Pinglun> list);
	
	
}
