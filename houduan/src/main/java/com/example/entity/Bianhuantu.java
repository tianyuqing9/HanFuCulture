package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@TableName("bianhuantu")
@Data
public class Bianhuantu {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
	
	@TableField(value = "mingcheng")	private String mingcheng;	@TableField(value = "tupian")	private String tupian;	
    
	
	
    @TableField(value = "addtime")
    private Date addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getMingcheng() {
        return mingcheng;
    }
    public void setMingcheng(String mingcheng) {
		this.mingcheng = mingcheng;
    }	public String getTupian() {
        return tupian;
    }
    public void setTupian(String tupian) {
		this.tupian = tupian;
    }	
	

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtme(Date addtime) {
        this.addtime = addtime;
    }
}