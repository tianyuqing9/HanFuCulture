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
import com.example.exception.CustomException;
import com.example.service.BankuaiService;
import com.example.vo.BankuaiVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@Service("bankuaiService")
public class BankuaiServiceImpl extends ServiceImpl<BankuaiDao, Bankuai> implements BankuaiService {

    @Resource
    private BankuaiDao bankuaiDao;

    public Bankuai add(Bankuai bankuai) {
		
	    LambdaQueryWrapper<Bankuai> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Bankuai::getBankuaimingcheng,bankuai.getBankuaimingcheng());
        Bankuai findBankuai = bankuaiDao.selectOne(wrapper);
        if (findBankuai != null) {
            throw new CustomException("1001", "" + bankuai.getBankuaimingcheng() + "\"已存在");
        }
        bankuai.setAddtime(new Date());
        bankuaiDao.insert(bankuai);
        return bankuai;
    }
	
	
	
    public void delete(Long id) {
        bankuaiDao.deleteById(id);
    }
	

    public void update(Bankuai bankuai) {
         LambdaQueryWrapper<Bankuai> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Bankuai::getBankuaimingcheng,bankuai.getBankuaimingcheng());
        Bankuai findBankuai = bankuaiDao.selectOne(wrapper);
		if (findBankuai != null && bankuai.getId() != findBankuai.getId()) {
            throw new CustomException("1001", "" + bankuai.getBankuaimingcheng() + "\"已存在");
        }
        bankuaiDao.updateById(bankuai);
    }
	
	@Override
    public List<Map<String, Object>> daochuexcel() {
        return bankuaiDao.daochuexcel();
    }

    @Override
    public Bankuai findById(Integer id) {
        return bankuaiDao.selectById(id);
    }

    public BankuaiVo findPage(BankuaiVo bankuaiVo) {
        LambdaQueryWrapper<Bankuai> wrapper = Wrappers.lambdaQuery();
		wrapper.like(ObjectUtils.isNotEmpty(bankuaiVo.getBankuaimingcheng()), Bankuai::getBankuaimingcheng,bankuaiVo.getBankuaimingcheng());        wrapper.like(ObjectUtils.isNotEmpty(bankuaiVo.getBanzhu()), Bankuai::getBanzhu,bankuaiVo.getBanzhu());        wrapper.like(ObjectUtils.isNotEmpty(bankuaiVo.getYiyoutieshu()), Bankuai::getYiyoutieshu,bankuaiVo.getYiyoutieshu());        wrapper.like(ObjectUtils.isNotEmpty(bankuaiVo.getJianjie()), Bankuai::getJianjie,bankuaiVo.getJianjie());        wrapper.like(ObjectUtils.isNotEmpty(bankuaiVo.getFengmian()), Bankuai::getFengmian,bankuaiVo.getFengmian());        
        Page<Bankuai> page = new Page<>(bankuaiVo.getCurrent(),bankuaiVo.getCurrentNum());
        bankuaiDao.selectPage(page,wrapper);
        List<Bankuai> bankuais = page.getRecords();
        bankuaiVo.setList(bankuais);
        bankuaiVo.setPages(page.getPages());
        bankuaiVo.setTotal(page.getTotal());
        bankuaiVo.setCurrent(page.getCurrent());
        bankuaiVo.setCurrentNum(page.getSize());
        return bankuaiVo;
    }

    @Override
    public void deleteList(List<Bankuai> list) {
        List<Integer> ids =  list.stream().map(Bankuai::getId).collect(Collectors.toList());
        bankuaiDao.deleteBatchIds(ids);
    }
	
	
	
	
}
