package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Guanliyuan;
import com.example.vo.GuanliyuanVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface GuanliyuanService extends IService<Guanliyuan> {
    Guanliyuan add(Guanliyuan guanliyuan);
    void delete(Long id);
    void update(Guanliyuan guanliyuan);
    Guanliyuan findById(Integer id);
    GuanliyuanVo findPage(GuanliyuanVo guanliyuanVo);
	Guanliyuan login(Guanliyuan guanliyuan);
    void deleteList(List<Guanliyuan> list);
}
