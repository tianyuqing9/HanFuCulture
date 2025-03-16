package com.example.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.ResultCode;
import com.example.dao.*;
import com.example.entity.Guanliyuan;
import com.example.exception.CustomException;
import com.example.service.GuanliyuanService;
import com.example.vo.GuanliyuanVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service("guanliyuanService")
public class GuanliyuanServiceImpl extends ServiceImpl<GuanliyuanDao, Guanliyuan> implements GuanliyuanService {

    @Resource
    private GuanliyuanDao guanliyuanDao;

    public Guanliyuan add(Guanliyuan guanliyuan) {
        guanliyuan.setMima(SecureUtil.md5(guanliyuan.getMima()));
	    LambdaQueryWrapper<Guanliyuan> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Guanliyuan::getYonghuming,guanliyuan.getYonghuming());
        Guanliyuan findGuanliyuan = guanliyuanDao.selectOne(wrapper);
        if (findGuanliyuan != null) {
            throw new CustomException("1001", "" + guanliyuan.getYonghuming() + "\"已存在");
        }
        guanliyuan.setAddtime(new Date());
        guanliyuanDao.insert(guanliyuan);
        return guanliyuan;
    }

    public void delete(Long id) {
        guanliyuanDao.deleteById(id);
    }

    public void update(Guanliyuan guanliyuan) {
         LambdaQueryWrapper<Guanliyuan> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Guanliyuan::getYonghuming,guanliyuan.getYonghuming());
        Guanliyuan findGuanliyuan = guanliyuanDao.selectOne(wrapper);
		if (findGuanliyuan != null && guanliyuan.getId() != findGuanliyuan.getId()) {
            throw new CustomException("1001", "" + guanliyuan.getYonghuming() + "\"已存在");
        }
        guanliyuanDao.updateById(guanliyuan);
    }

    @Override
    public Guanliyuan findById(Integer id) {
        return guanliyuanDao.selectById(id);
    }

    public GuanliyuanVo findPage(GuanliyuanVo guanliyuanVo) {
        LambdaQueryWrapper<Guanliyuan> wrapper = Wrappers.lambdaQuery();
		wrapper.like(ObjectUtils.isNotEmpty(guanliyuanVo.getYonghuming()), Guanliyuan::getYonghuming,guanliyuanVo.getYonghuming());
        
        Page<Guanliyuan> page = new Page<>(guanliyuanVo.getCurrent(),guanliyuanVo.getCurrentNum());
        guanliyuanDao.selectPage(page,wrapper);
        List<Guanliyuan> guanliyuans = page.getRecords();
        guanliyuanVo.setList(guanliyuans);
        guanliyuanVo.setPages(page.getPages());
        guanliyuanVo.setTotal(page.getTotal());
        guanliyuanVo.setCurrent(page.getCurrent());
        guanliyuanVo.setCurrentNum(page.getSize());
        return guanliyuanVo;
    }

    @Override
    public void deleteList(List<Guanliyuan> list) {
        List<Integer> ids =  list.stream().map(Guanliyuan::getId).collect(Collectors.toList());
        guanliyuanDao.deleteBatchIds(ids);
    }
	
	@Override
    public Guanliyuan login(Guanliyuan guanliyuan) {
        LambdaQueryWrapper<Guanliyuan> wrapper = new LambdaQueryWrapper();
        wrapper.eq(ObjectUtils.isNotEmpty(guanliyuan.getYonghuming()), Guanliyuan::getYonghuming,guanliyuan.getYonghuming());
        Guanliyuan guanliyuanInfo = guanliyuanDao.selectOne(wrapper);
        if (guanliyuanInfo == null) {
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        if (!SecureUtil.md5(guanliyuan.getMima()).equalsIgnoreCase(guanliyuanInfo.getMima())) {
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        return guanliyuanInfo;
    }
}
