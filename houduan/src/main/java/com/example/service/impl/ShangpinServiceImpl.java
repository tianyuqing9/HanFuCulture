package com.example.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.ResultCode;
import com.example.dao.*;
import com.example.entity.Shangpin;
import com.example.exception.CustomException;
import com.example.service.ShangpinService;
import com.example.vo.ShangpinVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@Service("shangpinService")
public class ShangpinServiceImpl extends ServiceImpl<ShangpinDao, Shangpin> implements ShangpinService {

    @Resource
    private ShangpinDao shangpinDao;

    public Shangpin add(Shangpin shangpin) {
		
	   //youquchong1
        shangpin.setAddtime(new Date());
        shangpinDao.insert(shangpin);
        return shangpin;
    }
	
	@Override    public List<Map<String,Object>> shangpin_tj_leixingbianhao() {    List<Map<String,Object>> returnMap = shangpinDao.shangpin_tj_leixingbianhao();    return returnMap;    }    
	
    public void delete(Long id) {
        shangpinDao.deleteById(id);
    }
	

    public void update(Shangpin shangpin) {
        //youquchong2
        shangpinDao.updateById(shangpin);
    }
	
	@Override
    public List<Map<String, Object>> daochuexcel() {
        return shangpinDao.daochuexcel();
    }

    @Override
    public Shangpin findById(Integer id) {
        return shangpinDao.selectById(id);
    }

    public ShangpinVo findPage(ShangpinVo shangpinVo) {
        LambdaQueryWrapper<Shangpin> wrapper = Wrappers.lambdaQuery();
		wrapper.like(ObjectUtils.isNotEmpty(shangpinVo.getHanfubianhao()), Shangpin::getHanfubianhao,shangpinVo.getHanfubianhao());        wrapper.like(ObjectUtils.isNotEmpty(shangpinVo.getHanfumingcheng()), Shangpin::getHanfumingcheng,shangpinVo.getHanfumingcheng());        wrapper.like(ObjectUtils.isNotEmpty(shangpinVo.getLeixingbianhao()), Shangpin::getLeixingbianhao,shangpinVo.getLeixingbianhao());        wrapper.like(ObjectUtils.isNotEmpty(shangpinVo.getLeixingmingcheng()), Shangpin::getLeixingmingcheng,shangpinVo.getLeixingmingcheng());        wrapper.like(ObjectUtils.isNotEmpty(shangpinVo.getJiage()), Shangpin::getJiage,shangpinVo.getJiage());        wrapper.like(ObjectUtils.isNotEmpty(shangpinVo.getKucun()), Shangpin::getKucun,shangpinVo.getKucun());        wrapper.like(ObjectUtils.isNotEmpty(shangpinVo.getTupian()), Shangpin::getTupian,shangpinVo.getTupian());        wrapper.like(ObjectUtils.isNotEmpty(shangpinVo.getZhuangtai()), Shangpin::getZhuangtai,shangpinVo.getZhuangtai());        wrapper.like(ObjectUtils.isNotEmpty(shangpinVo.getBeizhu()), Shangpin::getBeizhu,shangpinVo.getBeizhu());        
		wrapper.orderByDesc(Shangpin::getId);
        Page<Shangpin> page = new Page<>(shangpinVo.getCurrent(),shangpinVo.getCurrentNum());
        shangpinDao.selectPage(page,wrapper);
        List<Shangpin> shangpins = page.getRecords();
        shangpinVo.setList(shangpins);
        shangpinVo.setPages(page.getPages());
        shangpinVo.setTotal(page.getTotal());
        shangpinVo.setCurrent(page.getCurrent());
        shangpinVo.setCurrentNum(page.getSize());
        return shangpinVo;
    }

    @Override
    public void deleteList(List<Shangpin> list) {
        List<Integer> ids =  list.stream().map(Shangpin::getId).collect(Collectors.toList());
        shangpinDao.deleteBatchIds(ids);
    }
	
	
	
	
	
	//youxxianxci
}
