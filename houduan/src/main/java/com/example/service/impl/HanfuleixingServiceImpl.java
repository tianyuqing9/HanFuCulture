package com.example.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.ResultCode;
import com.example.dao.*;
import com.example.entity.Hanfuleixing;
import com.example.exception.CustomException;
import com.example.service.HanfuleixingService;
import com.example.vo.HanfuleixingVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@Service("hanfuleixingService")
public class HanfuleixingServiceImpl extends ServiceImpl<HanfuleixingDao, Hanfuleixing> implements HanfuleixingService {

    @Resource
    private HanfuleixingDao hanfuleixingDao;

    public Hanfuleixing add(Hanfuleixing hanfuleixing) {
		
	   //youquchong1
        hanfuleixing.setAddtime(new Date());
        hanfuleixingDao.insert(hanfuleixing);
        return hanfuleixing;
    }
	
	
	
    public void delete(Long id) {
        hanfuleixingDao.deleteById(id);
    }
	

    public void update(Hanfuleixing hanfuleixing) {
        //youquchong2
        hanfuleixingDao.updateById(hanfuleixing);
    }
	
	@Override
    public List<Map<String, Object>> daochuexcel() {
        return hanfuleixingDao.daochuexcel();
    }

    @Override
    public Hanfuleixing findById(Integer id) {
        return hanfuleixingDao.selectById(id);
    }

    public HanfuleixingVo findPage(HanfuleixingVo hanfuleixingVo) {
        LambdaQueryWrapper<Hanfuleixing> wrapper = Wrappers.lambdaQuery();
		wrapper.like(ObjectUtils.isNotEmpty(hanfuleixingVo.getLeixingbianhao()), Hanfuleixing::getLeixingbianhao,hanfuleixingVo.getLeixingbianhao());        wrapper.like(ObjectUtils.isNotEmpty(hanfuleixingVo.getLeixingmingcheng()), Hanfuleixing::getLeixingmingcheng,hanfuleixingVo.getLeixingmingcheng());        
		wrapper.orderByDesc(Hanfuleixing::getId);
        Page<Hanfuleixing> page = new Page<>(hanfuleixingVo.getCurrent(),hanfuleixingVo.getCurrentNum());
        hanfuleixingDao.selectPage(page,wrapper);
        List<Hanfuleixing> hanfuleixings = page.getRecords();
        hanfuleixingVo.setList(hanfuleixings);
        hanfuleixingVo.setPages(page.getPages());
        hanfuleixingVo.setTotal(page.getTotal());
        hanfuleixingVo.setCurrent(page.getCurrent());
        hanfuleixingVo.setCurrentNum(page.getSize());
        return hanfuleixingVo;
    }

    @Override
    public void deleteList(List<Hanfuleixing> list) {
        List<Integer> ids =  list.stream().map(Hanfuleixing::getId).collect(Collectors.toList());
        hanfuleixingDao.deleteBatchIds(ids);
    }
	
	
	
	
	
	//youxxianxci
}
