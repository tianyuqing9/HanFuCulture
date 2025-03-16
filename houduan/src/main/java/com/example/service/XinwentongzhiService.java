package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Xinwentongzhi;
import com.example.vo.XinwentongzhiVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface XinwentongzhiService extends IService<Xinwentongzhi> {
    Xinwentongzhi add(Xinwentongzhi xinwentongzhi);
    void delete(Long id);
    void update(Xinwentongzhi xinwentongzhi);
    Xinwentongzhi findById(Integer id);
    XinwentongzhiVo findPage(XinwentongzhiVo xinwentongzhiVo);
	
    void deleteList(List<Xinwentongzhi> list);
	
}
