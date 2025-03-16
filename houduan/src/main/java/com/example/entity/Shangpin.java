package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@TableName("shangpin")
@Data
public class Shangpin {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
	
	@TableField(value = "hanfubianhao")
	private String hanfubianhao;
	@TableField(value = "hanfumingcheng")
	private String hanfumingcheng;
	@TableField(value = "leixingbianhao")
	private String leixingbianhao;
	@TableField(value = "leixingmingcheng")
	private String leixingmingcheng;
	@TableField(value = "jiage")
	private String jiage;
	@TableField(value = "kucun")
	private Integer kucun;
	@TableField(value = "tupian")
	private String tupian;
	@TableField(value = "zhuangtai")
	private String zhuangtai;
	@TableField(value = "beizhu")
	private String beizhu;
	
    
	
	
    @TableField(value = "addtime")
    private Date addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getHanfubianhao() {
        return hanfubianhao;
    }
    public void setHanfubianhao(String hanfubianhao) {
		this.hanfubianhao = hanfubianhao;
    }
	public String getHanfumingcheng() {
        return hanfumingcheng;
    }
    public void setHanfumingcheng(String hanfumingcheng) {
		this.hanfumingcheng = hanfumingcheng;
    }
	public String getLeixingbianhao() {
        return leixingbianhao;
    }
    public void setLeixingbianhao(String leixingbianhao) {
		this.leixingbianhao = leixingbianhao;
    }
	public String getLeixingmingcheng() {
        return leixingmingcheng;
    }
    public void setLeixingmingcheng(String leixingmingcheng) {
		this.leixingmingcheng = leixingmingcheng;
    }
	public String getJiage() {
        return jiage;
    }
    public void setJiage(String jiage) {
		this.jiage = jiage;
    }
	public Integer getKucun() {
        return kucun;
    }
    public void setKucun(Integer kucun) {
		this.kucun = kucun;
    }
	public String getTupian() {
        return tupian;
    }
    public void setTupian(String tupian) {
		this.tupian = tupian;
    }
	public String getZhuangtai() {
        return zhuangtai;
    }
    public void setZhuangtai(String zhuangtai) {
		this.zhuangtai = zhuangtai;
    }
	public String getBeizhu() {
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