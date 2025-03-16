package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Yonghu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface YonghuDao extends BaseMapper<Yonghu> {

@Select("select * from yonghu")
List<Map<String, Object>> daochuexcel();

//youxxianxci

@Select("SELECT distinct(xingbie) as aa,count(id) as bb FROM yonghu group by xingbie order by id")List<Map<String, Object>> yonghu_tj_xingbie();//tonxgji1

}
