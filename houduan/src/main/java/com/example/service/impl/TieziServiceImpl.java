package com.example.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.ResultCode;
import com.example.dao.*;
import com.example.entity.Bankuai;
import com.example.entity.Tiezi;
import com.example.exception.CustomException;
import com.example.service.TieziService;
import com.example.vo.TieziVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@Service("tieziService")
public class TieziServiceImpl extends ServiceImpl<TieziDao, Tiezi> implements TieziService {

    @Resource
    private TieziDao tieziDao;

    @Resource
    private BankuaiDao bankuaiDao;

    public Tiezi add(Tiezi tiezi) {

	   //youquchong1
        tiezi.setAddtime(new Date());
        tieziDao.insert(tiezi);
        Bankuai bankuai=bankuaiDao.selectById(Integer.parseInt(tiezi.getSuoshubankuai()));
        bankuai.setYiyoutieshu((Integer.parseInt(bankuai.getYiyoutieshu())+1)+"");
        bankuaiDao.updateById(bankuai);
        return tiezi;
    }



    public void delete(Long id) {
        tieziDao.deleteById(id);
    }


    public void update(Tiezi tiezi) {
        //youquchong2
        tieziDao.updateById(tiezi);
    }

	@Override
    public List<Map<String, Object>> daochuexcel() {
        return tieziDao.daochuexcel();
    }

    @Override
    public Tiezi findById(Integer id) {
        return tieziDao.selectById(id);
    }

    public TieziVo findPage(TieziVo tieziVo) {
        LambdaQueryWrapper<Tiezi> wrapper = Wrappers.lambdaQuery();
		wrapper.like(ObjectUtils.isNotEmpty(tieziVo.getSuoshubankuai()), Tiezi::getSuoshubankuai,tieziVo.getSuoshubankuai());
        wrapper.like(ObjectUtils.isNotEmpty(tieziVo.getBiaoti()), Tiezi::getBiaoti,tieziVo.getBiaoti());
        wrapper.like(ObjectUtils.isNotEmpty(tieziVo.getNeirong()), Tiezi::getNeirong,tieziVo.getNeirong());
        wrapper.like(ObjectUtils.isNotEmpty(tieziVo.getLeixing()), Tiezi::getLeixing,tieziVo.getLeixing());
        wrapper.like(ObjectUtils.isNotEmpty(tieziVo.getFujian()), Tiezi::getFujian,tieziVo.getFujian());
        wrapper.like(ObjectUtils.isNotEmpty(tieziVo.getFatieren()), Tiezi::getFatieren,tieziVo.getFatieren());
        wrapper.like(ObjectUtils.isNotEmpty(tieziVo.getFid()), Tiezi::getFid,tieziVo.getFid());

        Page<Tiezi> page = new Page<>(tieziVo.getCurrent(),tieziVo.getCurrentNum());
        tieziDao.selectPage(page,wrapper);
        List<Tiezi> tiezis = page.getRecords();
        tieziVo.setList(tiezis);
        tieziVo.setPages(page.getPages());
        tieziVo.setTotal(page.getTotal());
        tieziVo.setCurrent(page.getCurrent());
        tieziVo.setCurrentNum(page.getSize());
        return tieziVo;
    }

    @Override
    public void deleteList(List<Tiezi> list) {
        List<Integer> ids =  list.stream().map(Tiezi::getId).collect(Collectors.toList());
        tieziDao.deleteBatchIds(ids);
    }




}
