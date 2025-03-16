package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@TableName("xinwentongzhi")
@Data
public class Xinwentongzhi {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
	
	@TableField(value = "biaoti")	private String biaoti;	@TableField(value = "leibie")	private String leibie;	@TableField(value = "neirong")	private String neirong;	@TableField(value = "dianjilv")	private String dianjilv;	
    
	
    @TableField(value = "addtime")
    private Date addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getBiaoti() {
        return biaoti;
    }
    public void setBiaoti(String biaoti) {
		this.biaoti = biaoti;
    }	public String getLeibie() {
        return leibie;
    }
    public void setLeibie(String leibie) {
		this.leibie = leibie;
    }	public String getNeirong() {
        return neirong;
    }
    public void setNeirong(String neirong) {
		this.neirong = neirong;
    }	public String getDianjilv() {
        return dianjilv;
    }
    public void setDianjilv(String dianjilv) {
		this.dianjilv = dianjilv;
    }	
	

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtme(Date addtime) {
        this.addtime = addtime;
    }
}