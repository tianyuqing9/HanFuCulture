package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Liuyanban;
import com.example.vo.LiuyanbanVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface LiuyanbanService extends IService<Liuyanban> {
    Liuyanban add(Liuyanban liuyanban);
    void delete(Long id);
    void update(Liuyanban liuyanban);
    Liuyanban findById(Integer id);
    LiuyanbanVo findPage(LiuyanbanVo liuyanbanVo);
	
    void deleteList(List<Liuyanban> list);

    List<Liuyanban> getLiuyan();
}
