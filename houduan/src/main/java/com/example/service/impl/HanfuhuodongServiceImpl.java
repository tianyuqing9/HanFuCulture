package com.example.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.ResultCode;
import com.example.dao.*;
import com.example.entity.Hanfuhuodong;
import com.example.exception.CustomException;
import com.example.service.HanfuhuodongService;
import com.example.vo.HanfuhuodongVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@Service("hanfuhuodongService")
public class HanfuhuodongServiceImpl extends ServiceImpl<HanfuhuodongDao, Hanfuhuodong> implements HanfuhuodongService {

    @Resource
    private HanfuhuodongDao hanfuhuodongDao;

    public Hanfuhuodong add(Hanfuhuodong hanfuhuodong) {
		
	   //youquchong1
        hanfuhuodong.setAddtime(new Date());
        hanfuhuodongDao.insert(hanfuhuodong);
        return hanfuhuodong;
    }
	
	
	
    public void delete(Long id) {
        hanfuhuodongDao.deleteById(id);
    }
	

    public void update(Hanfuhuodong hanfuhuodong) {
        //youquchong2
        hanfuhuodongDao.updateById(hanfuhuodong);
    }
	
	@Override
    public List<Map<String, Object>> daochuexcel() {
        return hanfuhuodongDao.daochuexcel();
    }

    @Override
    public Hanfuhuodong findById(Integer id) {
        return hanfuhuodongDao.selectById(id);
    }

    public HanfuhuodongVo findPage(HanfuhuodongVo hanfuhuodongVo) {
        LambdaQueryWrapper<Hanfuhuodong> wrapper = Wrappers.lambdaQuery();
		wrapper.like(ObjectUtils.isNotEmpty(hanfuhuodongVo.getHuodongbianhao()), Hanfuhuodong::getHuodongbianhao,hanfuhuodongVo.getHuodongbianhao());        wrapper.like(ObjectUtils.isNotEmpty(hanfuhuodongVo.getHuodongmingcheng()), Hanfuhuodong::getHuodongmingcheng,hanfuhuodongVo.getHuodongmingcheng());        wrapper.like(ObjectUtils.isNotEmpty(hanfuhuodongVo.getHuodongleixing()), Hanfuhuodong::getHuodongleixing,hanfuhuodongVo.getHuodongleixing());        wrapper.like(ObjectUtils.isNotEmpty(hanfuhuodongVo.getTupian()), Hanfuhuodong::getTupian,hanfuhuodongVo.getTupian());        wrapper.like(ObjectUtils.isNotEmpty(hanfuhuodongVo.getShijian()), Hanfuhuodong::getShijian,hanfuhuodongVo.getShijian());        wrapper.like(ObjectUtils.isNotEmpty(hanfuhuodongVo.getBeizhu()), Hanfuhuodong::getBeizhu,hanfuhuodongVo.getBeizhu());        
		wrapper.orderByDesc(Hanfuhuodong::getId);
        Page<Hanfuhuodong> page = new Page<>(hanfuhuodongVo.getCurrent(),hanfuhuodongVo.getCurrentNum());
        hanfuhuodongDao.selectPage(page,wrapper);
        List<Hanfuhuodong> hanfuhuodongs = page.getRecords();
        hanfuhuodongVo.setList(hanfuhuodongs);
        hanfuhuodongVo.setPages(page.getPages());
        hanfuhuodongVo.setTotal(page.getTotal());
        hanfuhuodongVo.setCurrent(page.getCurrent());
        hanfuhuodongVo.setCurrentNum(page.getSize());
        return hanfuhuodongVo;
    }

    @Override
    public void deleteList(List<Hanfuhuodong> list) {
        List<Integer> ids =  list.stream().map(Hanfuhuodong::getId).collect(Collectors.toList());
        hanfuhuodongDao.deleteBatchIds(ids);
    }
	
	
	
	
	
	//youxxianxci
}
