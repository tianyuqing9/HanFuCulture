package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Collect;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface CollectDao extends BaseMapper<Collect> {

    Collect findByUser(Collect collectInfo);

    List<Collect> findByEndUserId(Integer userId);

    @Delete("delete from collect where shangpinxinxiID = #{id} and user_id = #{userId}")
    void deleteByUserId(@Param("id") Integer id,@Param("userId") Integer userId);
}
