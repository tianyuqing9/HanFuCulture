package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Hanfuleixing;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface HanfuleixingDao extends BaseMapper<Hanfuleixing> {

@Select("select * from hanfuleixing")
List<Map<String, Object>> daochuexcel();

//youxxianxci

//tonxgji1

}
