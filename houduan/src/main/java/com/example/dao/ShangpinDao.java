package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Shangpin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ShangpinDao extends BaseMapper<Shangpin> {

@Select("select * from shangpin")
List<Map<String, Object>> daochuexcel();

//youxxianxci

@Select("SELECT distinct(leixingbianhao) as aa,count(id) as bb FROM shangpin group by leixingbianhao order by id")List<Map<String, Object>> shangpin_tj_leixingbianhao();//tonxgji1

}
