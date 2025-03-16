package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@TableName("bankuai")
@Data
public class Bankuai {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
	
	@TableField(value = "bankuaimingcheng")	private String bankuaimingcheng;	@TableField(value = "banzhu")	private String banzhu;	@TableField(value = "yiyoutieshu")	private String yiyoutieshu;	@TableField(value = "jianjie")	private String jianjie;	@TableField(value = "fengmian")	private String fengmian;	
    
	
	
    @TableField(value = "addtime")
    private Date addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getBankuaimingcheng() {
        return bankuaimingcheng;
    }
    public void setBankuaimingcheng(String bankuaimingcheng) {
		this.bankuaimingcheng = bankuaimingcheng;
    }	public String getBanzhu() {
        return banzhu;
    }
    public void setBanzhu(String banzhu) {
		this.banzhu = banzhu;
    }	public String getYiyoutieshu() {
        return yiyoutieshu;
    }
    public void setYiyoutieshu(String yiyoutieshu) {
		this.yiyoutieshu = yiyoutieshu;
    }	public String getJianjie() {
        return jianjie;
    }
    public void setJianjie(String jianjie) {
		this.jianjie = jianjie;
    }	public String getFengmian() {
        return fengmian;
    }
    public void setFengmian(String fengmian) {
		this.fengmian = fengmian;
    }	
	

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtme(Date addtime) {
        this.addtime = addtime;
    }
}