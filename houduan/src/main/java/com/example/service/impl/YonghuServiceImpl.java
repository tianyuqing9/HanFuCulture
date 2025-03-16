package com.example.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.ResultCode;
import com.example.dao.*;
import com.example.entity.Yonghu;
import com.example.exception.CustomException;
import com.example.service.YonghuService;
import com.example.vo.YonghuVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@Service("yonghuService")
public class YonghuServiceImpl extends ServiceImpl<YonghuDao, Yonghu> implements YonghuService {

    @Resource
    private YonghuDao yonghuDao;

    public Yonghu add(Yonghu yonghu) {
		if(yonghu.getMima()==null) {yonghu.setMima(SecureUtil.md5("001"));}else {yonghu.setMima(SecureUtil.md5(yonghu.getMima())); }
	    LambdaQueryWrapper<Yonghu> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Yonghu::getYonghubianhao,yonghu.getYonghubianhao());
        Yonghu findYonghu = yonghuDao.selectOne(wrapper);
        if (findYonghu != null) {
            throw new CustomException("1001", "" + yonghu.getYonghubianhao() + "\"已存在");
        }
        yonghu.setAddtime(new Date());
        yonghuDao.insert(yonghu);
        return yonghu;
    }
	
	@Override    public List<Map<String,Object>> yonghu_tj_xingbie() {    List<Map<String,Object>> returnMap = yonghuDao.yonghu_tj_xingbie();    return returnMap;    }    
	
    public void delete(Long id) {
        yonghuDao.deleteById(id);
    }
	

    public void update(Yonghu yonghu) {
         LambdaQueryWrapper<Yonghu> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Yonghu::getYonghubianhao,yonghu.getYonghubianhao());
        Yonghu findYonghu = yonghuDao.selectOne(wrapper);
		if (findYonghu != null && yonghu.getId() != findYonghu.getId()) {
            throw new CustomException("1001", "" + yonghu.getYonghubianhao() + "\"已存在");
        }
        yonghuDao.updateById(yonghu);
    }
	
	@Override
    public List<Map<String, Object>> daochuexcel() {
        return yonghuDao.daochuexcel();
    }

    @Override
    public Yonghu findById(Integer id) {
        return yonghuDao.selectById(id);
    }

    public YonghuVo findPage(YonghuVo yonghuVo) {
        LambdaQueryWrapper<Yonghu> wrapper = Wrappers.lambdaQuery();
		wrapper.like(ObjectUtils.isNotEmpty(yonghuVo.getYonghubianhao()), Yonghu::getYonghubianhao,yonghuVo.getYonghubianhao());        wrapper.like(ObjectUtils.isNotEmpty(yonghuVo.getMima()), Yonghu::getMima,yonghuVo.getMima());        wrapper.like(ObjectUtils.isNotEmpty(yonghuVo.getXingming()), Yonghu::getXingming,yonghuVo.getXingming());        wrapper.eq(ObjectUtils.isNotEmpty(yonghuVo.getXingbie()), Yonghu::getXingbie,yonghuVo.getXingbie());        wrapper.like(ObjectUtils.isNotEmpty(yonghuVo.getShenfenzheng()), Yonghu::getShenfenzheng,yonghuVo.getShenfenzheng());        wrapper.like(ObjectUtils.isNotEmpty(yonghuVo.getDianhua()), Yonghu::getDianhua,yonghuVo.getDianhua());        wrapper.like(ObjectUtils.isNotEmpty(yonghuVo.getZhaopian()), Yonghu::getZhaopian,yonghuVo.getZhaopian());        wrapper.like(ObjectUtils.isNotEmpty(yonghuVo.getBeizhu()), Yonghu::getBeizhu,yonghuVo.getBeizhu());        
		wrapper.orderByDesc(Yonghu::getId);
        Page<Yonghu> page = new Page<>(yonghuVo.getCurrent(),yonghuVo.getCurrentNum());
        yonghuDao.selectPage(page,wrapper);
        List<Yonghu> yonghus = page.getRecords();
        yonghuVo.setList(yonghus);
        yonghuVo.setPages(page.getPages());
        yonghuVo.setTotal(page.getTotal());
        yonghuVo.setCurrent(page.getCurrent());
        yonghuVo.setCurrentNum(page.getSize());
        return yonghuVo;
    }

    @Override
    public void deleteList(List<Yonghu> list) {
        List<Integer> ids =  list.stream().map(Yonghu::getId).collect(Collectors.toList());
        yonghuDao.deleteBatchIds(ids);
    }
	
	@Override
    public Yonghu login(Yonghu yonghu) {
        LambdaQueryWrapper<Yonghu> wrapper = new LambdaQueryWrapper();
        wrapper.eq(ObjectUtils.isNotEmpty(yonghu.getYonghubianhao()), Yonghu::getYonghubianhao,yonghu.getYonghubianhao());
        Yonghu yonghuInfo = yonghuDao.selectOne(wrapper);
        if (yonghuInfo == null) {
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        if (!SecureUtil.md5(yonghu.getMima()).equalsIgnoreCase(yonghuInfo.getMima())) {
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        return yonghuInfo;
    }
	
	
	
	//youxxianxci
}
