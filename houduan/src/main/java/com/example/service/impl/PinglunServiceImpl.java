package com.example.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.common.ResultCode;
import com.example.dao.*;
//import com.example.entity.Jiaolian;
import com.example.entity.Pinglun;

import com.example.exception.CustomException;
import com.example.service.PinglunService;
import com.example.vo.PinglunVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@Service("pinglunService")
public class PinglunServiceImpl extends ServiceImpl<PinglunDao, Pinglun> implements PinglunService {

    @Resource
    private PinglunDao pinglunDao;

    public Pinglun add(Pinglun pinglun) {
		
	   //youquchong1
        pinglun.setAddtime(new Date());
        pinglunDao.insert(pinglun);
        return pinglun;
    }
	
	
	
    public void delete(Long id) {
        pinglunDao.deleteById(id);
    }

    public void update(Pinglun pinglun) {
        //youquchong2
        pinglunDao.updateById(pinglun);
    }

    @Override
    public Pinglun findById(Integer id) {
        return pinglunDao.selectById(id);
    }

    public PinglunVo findPage(PinglunVo pinglunVo) {
        LambdaQueryWrapper<Pinglun> wrapper = Wrappers.lambdaQuery();
		wrapper.like(ObjectUtils.isNotEmpty(pinglunVo.getPinglunneirong()), Pinglun::getPinglunneirong,pinglunVo.getPinglunneirong());
        wrapper.like(ObjectUtils.isNotEmpty(pinglunVo.getPingfen()), Pinglun::getPingfen,pinglunVo.getPingfen());
        wrapper.like(ObjectUtils.isNotEmpty(pinglunVo.getBiao()), Pinglun::getBiao,pinglunVo.getBiao());
        wrapper.like(ObjectUtils.isNotEmpty(pinglunVo.getPinglunren()), Pinglun::getPinglunren,pinglunVo.getPinglunren());
        wrapper.eq(ObjectUtils.isNotEmpty(pinglunVo.getShujuid()), Pinglun::getShujuid,pinglunVo.getShujuid());

        Page<Pinglun> page = new Page<>(pinglunVo.getCurrent(),pinglunVo.getCurrentNum());
        pinglunDao.selectPage(page,wrapper);
        List<Pinglun> pingluns = page.getRecords();
        pinglunVo.setList(pingluns);
        pinglunVo.setPages(page.getPages());
        pinglunVo.setTotal(page.getTotal());
        pinglunVo.setCurrent(page.getCurrent());
        pinglunVo.setCurrentNum(page.getSize());
        return pinglunVo;
    }

    @Override
    public void deleteList(List<Pinglun> list) {
        List<Integer> ids =  list.stream().map(Pinglun::getId).collect(Collectors.toList());
        pinglunDao.deleteBatchIds(ids);
    }
	
	
	
	
}
