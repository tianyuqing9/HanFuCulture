package com.example.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.ResultCode;
import com.example.dao.*;
import com.example.entity.Yonghuxinxi;
import com.example.exception.CustomException;
import com.example.service.YonghuxinxiService;
import com.example.vo.YonghuxinxiVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@Service("yonghuxinxiService")
public class YonghuxinxiServiceImpl extends ServiceImpl<YonghuxinxiDao, Yonghuxinxi> implements YonghuxinxiService {

    @Resource
    private YonghuxinxiDao yonghuxinxiDao;

    public Yonghuxinxi add(Yonghuxinxi yonghuxinxi) {
		if(yonghuxinxi.getMima()==null) {yonghuxinxi.setMima(SecureUtil.md5("001"));}else {yonghuxinxi.setMima(SecureUtil.md5(yonghuxinxi.getMima())); }
	    LambdaQueryWrapper<Yonghuxinxi> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Yonghuxinxi::getYonghubianhao,yonghuxinxi.getYonghubianhao());
        Yonghuxinxi findYonghuxinxi = yonghuxinxiDao.selectOne(wrapper);
        if (findYonghuxinxi != null) {
            throw new CustomException("1001", "" + yonghuxinxi.getYonghubianhao() + "\"已存在");
        }
        yonghuxinxi.setAddtime(new Date());
        yonghuxinxiDao.insert(yonghuxinxi);
        return yonghuxinxi;
    }
	
	@Override    public List<Map<String,Object>> yonghuxinxi_tj_xingbie() {    List<Map<String,Object>> returnMap = yonghuxinxiDao.yonghuxinxi_tj_xingbie();    return returnMap;    }    
	
    public void delete(Long id) {
        yonghuxinxiDao.deleteById(id);
    }
	

    public void update(Yonghuxinxi yonghuxinxi) {
         LambdaQueryWrapper<Yonghuxinxi> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Yonghuxinxi::getYonghubianhao,yonghuxinxi.getYonghubianhao());
        Yonghuxinxi findYonghuxinxi = yonghuxinxiDao.selectOne(wrapper);
		if (findYonghuxinxi != null && yonghuxinxi.getId() != findYonghuxinxi.getId()) {
            throw new CustomException("1001", "" + yonghuxinxi.getYonghubianhao() + "\"已存在");
        }
        yonghuxinxiDao.updateById(yonghuxinxi);
    }
	
	@Override
    public List<Map<String, Object>> daochuexcel() {
        return yonghuxinxiDao.daochuexcel();
    }

    @Override
    public Yonghuxinxi findById(Integer id) {
        return yonghuxinxiDao.selectById(id);
    }

    public YonghuxinxiVo findPage(YonghuxinxiVo yonghuxinxiVo) {
        LambdaQueryWrapper<Yonghuxinxi> wrapper = Wrappers.lambdaQuery();
		wrapper.like(ObjectUtils.isNotEmpty(yonghuxinxiVo.getYonghubianhao()), Yonghuxinxi::getYonghubianhao,yonghuxinxiVo.getYonghubianhao());        wrapper.like(ObjectUtils.isNotEmpty(yonghuxinxiVo.getMima()), Yonghuxinxi::getMima,yonghuxinxiVo.getMima());        wrapper.like(ObjectUtils.isNotEmpty(yonghuxinxiVo.getXingming()), Yonghuxinxi::getXingming,yonghuxinxiVo.getXingming());        wrapper.eq(ObjectUtils.isNotEmpty(yonghuxinxiVo.getXingbie()), Yonghuxinxi::getXingbie,yonghuxinxiVo.getXingbie());        wrapper.like(ObjectUtils.isNotEmpty(yonghuxinxiVo.getShenfenzheng()), Yonghuxinxi::getShenfenzheng,yonghuxinxiVo.getShenfenzheng());        wrapper.like(ObjectUtils.isNotEmpty(yonghuxinxiVo.getDianhua()), Yonghuxinxi::getDianhua,yonghuxinxiVo.getDianhua());        wrapper.like(ObjectUtils.isNotEmpty(yonghuxinxiVo.getZhaopian()), Yonghuxinxi::getZhaopian,yonghuxinxiVo.getZhaopian());        wrapper.like(ObjectUtils.isNotEmpty(yonghuxinxiVo.getBeizhu()), Yonghuxinxi::getBeizhu,yonghuxinxiVo.getBeizhu());        
		wrapper.orderByDesc(Yonghuxinxi::getId);
        Page<Yonghuxinxi> page = new Page<>(yonghuxinxiVo.getCurrent(),yonghuxinxiVo.getCurrentNum());
        yonghuxinxiDao.selectPage(page,wrapper);
        List<Yonghuxinxi> yonghuxinxis = page.getRecords();
        yonghuxinxiVo.setList(yonghuxinxis);
        yonghuxinxiVo.setPages(page.getPages());
        yonghuxinxiVo.setTotal(page.getTotal());
        yonghuxinxiVo.setCurrent(page.getCurrent());
        yonghuxinxiVo.setCurrentNum(page.getSize());
        return yonghuxinxiVo;
    }

    @Override
    public void deleteList(List<Yonghuxinxi> list) {
        List<Integer> ids =  list.stream().map(Yonghuxinxi::getId).collect(Collectors.toList());
        yonghuxinxiDao.deleteBatchIds(ids);
    }
	
	@Override
    public Yonghuxinxi login(Yonghuxinxi yonghuxinxi) {
        LambdaQueryWrapper<Yonghuxinxi> wrapper = new LambdaQueryWrapper();
        wrapper.eq(ObjectUtils.isNotEmpty(yonghuxinxi.getYonghubianhao()), Yonghuxinxi::getYonghubianhao,yonghuxinxi.getYonghubianhao());
        Yonghuxinxi yonghuxinxiInfo = yonghuxinxiDao.selectOne(wrapper);
        if (yonghuxinxiInfo == null) {
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        if (!SecureUtil.md5(yonghuxinxi.getMima()).equalsIgnoreCase(yonghuxinxiInfo.getMima())) {
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        return yonghuxinxiInfo;
    }
	
	
	
	//youxxianxci
}
