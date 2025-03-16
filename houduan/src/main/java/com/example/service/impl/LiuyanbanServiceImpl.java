package com.example.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.ResultCode;
import com.example.dao.*;
import com.example.entity.Liuyanban;
import com.example.exception.CustomException;
import com.example.service.LiuyanbanService;
import com.example.vo.LiuyanbanVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service("liuyanbanService")
public class LiuyanbanServiceImpl extends ServiceImpl<LiuyanbanDao, Liuyanban> implements LiuyanbanService {

    @Resource
    private LiuyanbanDao liuyanbanDao;

    public Liuyanban add(Liuyanban liuyanban) {

	   //youquchong1
        liuyanban.setAddtime(new Date());
        liuyanbanDao.insert(liuyanban);
        return liuyanban;
    }

    public void delete(Long id) {
        liuyanbanDao.deleteById(id);
    }

    public void update(Liuyanban liuyanban) {
        //youquchong2
        liuyanbanDao.updateById(liuyanban);
    }

    @Override
    public Liuyanban findById(Integer id) {
        return liuyanbanDao.selectById(id);
    }

    public LiuyanbanVo findPage(LiuyanbanVo liuyanbanVo) {
        LambdaQueryWrapper<Liuyanban> wrapper = Wrappers.lambdaQuery();
		wrapper.like(ObjectUtils.isNotEmpty(liuyanbanVo.getYonghuming()), Liuyanban::getYonghuming,liuyanbanVo.getYonghuming());
        wrapper.like(ObjectUtils.isNotEmpty(liuyanbanVo.getCheng()), Liuyanban::getCheng,liuyanbanVo.getCheng());
        wrapper.like(ObjectUtils.isNotEmpty(liuyanbanVo.getTouxiang()), Liuyanban::getTouxiang,liuyanbanVo.getTouxiang());
        wrapper.like(ObjectUtils.isNotEmpty(liuyanbanVo.getBiaoti()), Liuyanban::getBiaoti,liuyanbanVo.getBiaoti());
        wrapper.like(ObjectUtils.isNotEmpty(liuyanbanVo.getNeirong()), Liuyanban::getNeirong,liuyanbanVo.getNeirong());
        wrapper.like(ObjectUtils.isNotEmpty(liuyanbanVo.getHuifu()), Liuyanban::getHuifu,liuyanbanVo.getHuifu());

        Page<Liuyanban> page = new Page<>(liuyanbanVo.getCurrent(),liuyanbanVo.getCurrentNum());
        liuyanbanDao.selectPage(page,wrapper);
        List<Liuyanban> liuyanbans = page.getRecords();
        liuyanbanVo.setList(liuyanbans);
        liuyanbanVo.setPages(page.getPages());
        liuyanbanVo.setTotal(page.getTotal());
        liuyanbanVo.setCurrent(page.getCurrent());
        liuyanbanVo.setCurrentNum(page.getSize());
        return liuyanbanVo;
    }

    @Override
    public void deleteList(List<Liuyanban> list) {
        List<Integer> ids =  list.stream().map(Liuyanban::getId).collect(Collectors.toList());
        liuyanbanDao.deleteBatchIds(ids);
    }

    @Override
    public List<Liuyanban> getLiuyan() {
        LambdaQueryWrapper<Liuyanban> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Liuyanban::getParent, 0);
        List<Liuyanban> liuyanbanList = liuyanbanDao.selectList(wrapper);
        for (Liuyanban liuyanban : liuyanbanList) {
            LambdaQueryWrapper<Liuyanban> wrapper2 = Wrappers.lambdaQuery();
            wrapper2.eq(Liuyanban::getParent, liuyanban.getId());
            List<Liuyanban> child = liuyanbanDao.selectList(wrapper2);
            liuyanban.setChildren(child);
        }
        return liuyanbanList;
    }


}
