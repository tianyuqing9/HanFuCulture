package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Bankuai;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface BankuaiDao extends BaseMapper<Bankuai> {

@Select("select * from bankuai")
List<Map<String, Object>> daochuexcel();

//tonxgji1
}
