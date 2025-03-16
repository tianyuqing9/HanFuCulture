package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@TableName("hanfuleixing")
@Data
public class Hanfuleixing {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
	
	@TableField(value = "leixingbianhao")	private String leixingbianhao;	@TableField(value = "leixingmingcheng")	private String leixingmingcheng;	
    
	
	
    @TableField(value = "addtime")
    private Date addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getLeixingbianhao() {
        return leixingbianhao;
    }
    public void setLeixingbianhao(String leixingbianhao) {
		this.leixingbianhao = leixingbianhao;
    }	public String getLeixingmingcheng() {
        return leixingmingcheng;
    }
    public void setLeixingmingcheng(String leixingmingcheng) {
		this.leixingmingcheng = leixingmingcheng;
    }	
	

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtme(Date addtime) {
        this.addtime = addtime;
    }
}