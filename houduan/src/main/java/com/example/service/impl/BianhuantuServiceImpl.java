package com.example.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.ResultCode;
import com.example.dao.*;
import com.example.entity.Bianhuantu;
import com.example.exception.CustomException;
import com.example.service.BianhuantuService;
import com.example.vo.BianhuantuVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@Service("bianhuantuService")
public class BianhuantuServiceImpl extends ServiceImpl<BianhuantuDao, Bianhuantu> implements BianhuantuService {

    @Resource
    private BianhuantuDao bianhuantuDao;

    public Bianhuantu add(Bianhuantu bianhuantu) {
		
	   //youquchong1
        bianhuantu.setAddtime(new Date());
        bianhuantuDao.insert(bianhuantu);
        return bianhuantu;
    }
	
	
	
    public void delete(Long id) {
        bianhuantuDao.deleteById(id);
    }
	

    public void update(Bianhuantu bianhuantu) {
        //youquchong2
        bianhuantuDao.updateById(bianhuantu);
    }
	
	@Override
    public List<Map<String, Object>> daochuexcel() {
        return bianhuantuDao.daochuexcel();
    }

    @Override
    public Bianhuantu findById(Integer id) {
        return bianhuantuDao.selectById(id);
    }

    public BianhuantuVo findPage(BianhuantuVo bianhuantuVo) {
        LambdaQueryWrapper<Bianhuantu> wrapper = Wrappers.lambdaQuery();
		wrapper.like(ObjectUtils.isNotEmpty(bianhuantuVo.getMingcheng()), Bianhuantu::getMingcheng,bianhuantuVo.getMingcheng());        wrapper.like(ObjectUtils.isNotEmpty(bianhuantuVo.getTupian()), Bianhuantu::getTupian,bianhuantuVo.getTupian());        
        Page<Bianhuantu> page = new Page<>(bianhuantuVo.getCurrent(),bianhuantuVo.getCurrentNum());
        bianhuantuDao.selectPage(page,wrapper);
        List<Bianhuantu> bianhuantus = page.getRecords();
        bianhuantuVo.setList(bianhuantus);
        bianhuantuVo.setPages(page.getPages());
        bianhuantuVo.setTotal(page.getTotal());
        bianhuantuVo.setCurrent(page.getCurrent());
        bianhuantuVo.setCurrentNum(page.getSize());
        return bianhuantuVo;
    }

    @Override
    public void deleteList(List<Bianhuantu> list) {
        List<Integer> ids =  list.stream().map(Bianhuantu::getId).collect(Collectors.toList());
        bianhuantuDao.deleteBatchIds(ids);
    }
	
	
	
	
}
