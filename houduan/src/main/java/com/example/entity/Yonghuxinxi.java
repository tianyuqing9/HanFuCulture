package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@TableName("yonghuxinxi")
@Data
public class Yonghuxinxi {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
	
	@TableField(value = "yonghubianhao")	private String yonghubianhao;	@TableField(value = "mima")	private String mima;	@TableField(value = "xingming")	private String xingming;	@TableField(value = "xingbie")	private Boolean xingbie;	@TableField(value = "shenfenzheng")	private String shenfenzheng;	@TableField(value = "dianhua")	private String dianhua;	@TableField(value = "zhaopian")	private String zhaopian;	@TableField(value = "beizhu")	private String beizhu;	@TableField(exist = false)	private String newPassword;	
    
	
	
    @TableField(value = "addtime")
    private Date addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getYonghubianhao() {
        return yonghubianhao;
    }
    public void setYonghubianhao(String yonghubianhao) {
		this.yonghubianhao = yonghubianhao;
    }	public String getMima() {
        return mima;
    }
    public void setMima(String mima) {
		this.mima = mima;
    }	public String getXingming() {
        return xingming;
    }
    public void setXingming(String xingming) {
		this.xingming = xingming;
    }	public Boolean getXingbie() {
        return xingbie;
    }
    public void setXingbie(Boolean xingbie) {
		this.xingbie = xingbie;
    }	public String getShenfenzheng() {
        return shenfenzheng;
    }
    public void setShenfenzheng(String shenfenzheng) {
		this.shenfenzheng = shenfenzheng;
    }	public String getDianhua() {
        return dianhua;
    }
    public void setDianhua(String dianhua) {
		this.dianhua = dianhua;
    }	public String getZhaopian() {
        return zhaopian;
    }
    public void setZhaopian(String zhaopian) {
		this.zhaopian = zhaopian;
    }	public String getBeizhu() {
        return beizhu;
    }
    public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
    }	public String getNewPassword() {
        return newPassword;
    }
    public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
    }	
	

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtme(Date addtime) {
        this.addtime = addtime;
    }
}