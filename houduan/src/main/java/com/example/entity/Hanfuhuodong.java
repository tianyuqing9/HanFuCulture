package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@TableName("hanfuhuodong")
@Data
public class Hanfuhuodong {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
	
	@TableField(value = "huodongbianhao")	private String huodongbianhao;	@TableField(value = "huodongmingcheng")	private String huodongmingcheng;	@TableField(value = "huodongleixing")	private String huodongleixing;	@TableField(value = "tupian")	private String tupian;	@TableField(value = "shijian")	private String shijian;	@TableField(value = "beizhu")	private String beizhu;	
    
	
	
    @TableField(value = "addtime")
    private Date addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getHuodongbianhao() {
        return huodongbianhao;
    }
    public void setHuodongbianhao(String huodongbianhao) {
		this.huodongbianhao = huodongbianhao;
    }	public String getHuodongmingcheng() {
        return huodongmingcheng;
    }
    public void setHuodongmingcheng(String huodongmingcheng) {
		this.huodongmingcheng = huodongmingcheng;
    }	public String getHuodongleixing() {
        return huodongleixing;
    }
    public void setHuodongleixing(String huodongleixing) {
		this.huodongleixing = huodongleixing;
    }	public String getTupian() {
        return tupian;
    }
    public void setTupian(String tupian) {
		this.tupian = tupian;
    }	public String getShijian() {
        return shijian;
    }
    public void setShijian(String shijian) {
		this.shijian = shijian;
    }	public String getBeizhu() {
        return beizhu;
    }
    public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
    }	
	

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtme(Date addtime) {
        this.addtime = addtime;
    }
}