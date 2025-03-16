package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@TableName("guanliyuan")
@Data
public class Guanliyuan {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
	
	@TableField(value = "yonghuming")
	private String yonghuming;
	@TableField(value = "mima")
	private String mima;
	@TableField(exist = false)
	private String newPassword;
	
    
	
    @TableField(value = "addtime")
    private Date addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getYonghuming() {
        return yonghuming;
    }
    public void setYonghuming(String yonghuming) {
		this.yonghuming = yonghuming;
    }
	public String getMima() {
        return mima;
    }
    public void setMima(String mima) {
		this.mima = mima;
    }
	public String getNewPassword() {
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