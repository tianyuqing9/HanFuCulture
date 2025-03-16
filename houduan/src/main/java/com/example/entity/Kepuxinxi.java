package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@TableName("kepuxinxi")
@Data
public class Kepuxinxi {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
	
	@TableField(value = "kepubianhao")	private String kepubianhao;	@TableField(value = "kepumingcheng")	private String kepumingcheng;	@TableField(value = "kepuleixing")	private String kepuleixing;	@TableField(value = "neirong")	private String neirong;	@TableField(value = "tupian")	private String tupian;	@TableField(value = "shipin")	private String shipin;	@TableField(value = "shangchuanshijian")	private String shangchuanshijian;	@TableField(value = "beizhu")	private String beizhu;	
    
	
	
    @TableField(value = "addtime")
    private Date addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getKepubianhao() {
        return kepubianhao;
    }
    public void setKepubianhao(String kepubianhao) {
		this.kepubianhao = kepubianhao;
    }	public String getKepumingcheng() {
        return kepumingcheng;
    }
    public void setKepumingcheng(String kepumingcheng) {
		this.kepumingcheng = kepumingcheng;
    }	public String getKepuleixing() {
        return kepuleixing;
    }
    public void setKepuleixing(String kepuleixing) {
		this.kepuleixing = kepuleixing;
    }	public String getNeirong() {
        return neirong;
    }
    public void setNeirong(String neirong) {
		this.neirong = neirong;
    }	public String getTupian() {
        return tupian;
    }
    public void setTupian(String tupian) {
		this.tupian = tupian;
    }	public String getShipin() {
        return shipin;
    }
    public void setShipin(String shipin) {
		this.shipin = shipin;
    }	public String getShangchuanshijian() {
        return shangchuanshijian;
    }
    public void setShangchuanshijian(String shangchuanshijian) {
		this.shangchuanshijian = shangchuanshijian;
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