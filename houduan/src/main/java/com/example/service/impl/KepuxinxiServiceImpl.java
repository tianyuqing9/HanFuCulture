package com.example.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.ResultCode;
import com.example.dao.*;
import com.example.entity.Kepuxinxi;
import com.example.exception.CustomException;
import com.example.service.KepuxinxiService;
import com.example.vo.KepuxinxiVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@Service("kepuxinxiService")
public class KepuxinxiServiceImpl extends ServiceImpl<KepuxinxiDao, Kepuxinxi> implements KepuxinxiService {

    @Resource
    private KepuxinxiDao kepuxinxiDao;

    public Kepuxinxi add(Kepuxinxi kepuxinxi) {
		
	   //youquchong1
        kepuxinxi.setAddtime(new Date());
        kepuxinxiDao.insert(kepuxinxi);
        return kepuxinxi;
    }
	
	@Override    public List<Map<String,Object>> kepuxinxi_tj_kepuleixing() {    List<Map<String,Object>> returnMap = kepuxinxiDao.kepuxinxi_tj_kepuleixing();    return returnMap;    }    
	
    public void delete(Long id) {
        kepuxinxiDao.deleteById(id);
    }
	

    public void update(Kepuxinxi kepuxinxi) {
        //youquchong2
        kepuxinxiDao.updateById(kepuxinxi);
    }
	
	@Override
    public List<Map<String, Object>> daochuexcel() {
        return kepuxinxiDao.daochuexcel();
    }

    @Override
    public Kepuxinxi findById(Integer id) {
        return kepuxinxiDao.selectById(id);
    }

    public KepuxinxiVo findPage(KepuxinxiVo kepuxinxiVo) {
        LambdaQueryWrapper<Kepuxinxi> wrapper = Wrappers.lambdaQuery();
		wrapper.like(ObjectUtils.isNotEmpty(kepuxinxiVo.getKepubianhao()), Kepuxinxi::getKepubianhao,kepuxinxiVo.getKepubianhao());        wrapper.like(ObjectUtils.isNotEmpty(kepuxinxiVo.getKepumingcheng()), Kepuxinxi::getKepumingcheng,kepuxinxiVo.getKepumingcheng());        wrapper.like(ObjectUtils.isNotEmpty(kepuxinxiVo.getKepuleixing()), Kepuxinxi::getKepuleixing,kepuxinxiVo.getKepuleixing());        wrapper.like(ObjectUtils.isNotEmpty(kepuxinxiVo.getNeirong()), Kepuxinxi::getNeirong,kepuxinxiVo.getNeirong());        wrapper.like(ObjectUtils.isNotEmpty(kepuxinxiVo.getTupian()), Kepuxinxi::getTupian,kepuxinxiVo.getTupian());        wrapper.like(ObjectUtils.isNotEmpty(kepuxinxiVo.getShipin()), Kepuxinxi::getShipin,kepuxinxiVo.getShipin());        wrapper.like(ObjectUtils.isNotEmpty(kepuxinxiVo.getShangchuanshijian()), Kepuxinxi::getShangchuanshijian,kepuxinxiVo.getShangchuanshijian());        wrapper.like(ObjectUtils.isNotEmpty(kepuxinxiVo.getBeizhu()), Kepuxinxi::getBeizhu,kepuxinxiVo.getBeizhu());        
		wrapper.orderByDesc(Kepuxinxi::getId);
        Page<Kepuxinxi> page = new Page<>(kepuxinxiVo.getCurrent(),kepuxinxiVo.getCurrentNum());
        kepuxinxiDao.selectPage(page,wrapper);
        List<Kepuxinxi> kepuxinxis = page.getRecords();
        kepuxinxiVo.setList(kepuxinxis);
        kepuxinxiVo.setPages(page.getPages());
        kepuxinxiVo.setTotal(page.getTotal());
        kepuxinxiVo.setCurrent(page.getCurrent());
        kepuxinxiVo.setCurrentNum(page.getSize());
        return kepuxinxiVo;
    }

    @Override
    public void deleteList(List<Kepuxinxi> list) {
        List<Integer> ids =  list.stream().map(Kepuxinxi::getId).collect(Collectors.toList());
        kepuxinxiDao.deleteBatchIds(ids);
    }
	
	
	
	
	
	//youxxianxci
}
