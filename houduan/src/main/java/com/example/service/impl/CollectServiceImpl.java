package com.example.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.CollectDao;
import com.example.dao.FileInfoDao;
import com.example.entity.Collect;
import com.example.entity.FileInfo;
import com.example.entity.Yonghu;import com.example.entity.Shangpin;
import com.example.service.CollectService;
import com.example.service.YonghuService;import com.example.service.ShangpinService;


import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("collectService")
public class CollectServiceImpl  extends ServiceImpl<CollectDao, Collect> implements CollectService {

    @Resource
    private CollectDao collectDao;
	
	@Resourceprivate YonghuService yonghuService;@Resourceprivate ShangpinService shangpinService;
   

    public Collect add(Collect collect) {
        Collect collect1 = collectDao.findByUser(collect);
        if(collect1 == null) {
            collectDao.insert(collect);
            return collect;
        }else {
            return null;
        }
    }

    public void delete(Integer id) {
        collectDao.deleteById(id);
    }

    public void update(Collect collect) {
        collectDao.updateById(collect);
    }

    public Collect findById(Long id) {
        return collectDao.selectById(id);
    }

    public List<Collect> findFrontPages(Integer userId) {
        List<Collect> collects;
        if (userId != null){
            collects = collectDao.findByEndUserId(userId);
        } else {
            collects = new ArrayList<>();
        }
        for (Collect collect : collects) {
            packOrder(collect);
        }
        return collects;
    }
    /**
     * 包装收藏的用户和商品信息
     */

	public List<Yonghu> findPaperByYonghu(Integer id) {
        List<Collect> collects = collectDao.findByEndUserId(id);
        List<Yonghu> yonghuList = new ArrayList<>();
        for (Collect collect : collects) {
            Integer foreignId = collect.getShangpinxinxiID();
            Yonghu yonghu = null;
            if(foreignId != null){
                yonghu = yonghuService.findById(foreignId);
            };
            if(yonghu != null){
                yonghuList.add(yonghu);
            }
        }
        return yonghuList;
    }
	
	private void packOrder(Collect collect) {
        collect.setYonghu(yonghuService.findById(collect.getUserId()));
        List<Shangpin> goodsList = CollUtil.newArrayList();
        collect.setProductList(goodsList);
        Shangpin goodsDetail = shangpinService.findById(collect.getShangpinxinxiID());
        if (goodsDetail != null) {
            goodsList.add(goodsDetail);
        }
    }

	@Override
    public void deleteByUserId(Integer id, Integer userId) {
        collectDao.deleteByUserId(id,userId);
    }
    
}
