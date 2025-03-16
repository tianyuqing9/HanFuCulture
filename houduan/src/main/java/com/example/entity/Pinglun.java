package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@TableName("pinglun")
@Data
public class Pinglun {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
	
	@TableField(value = "pinglunneirong")
	private String pinglunneirong;
	@TableField(value = "pingfen")
	private String pingfen;
	@TableField(value = "biao")
	private String biao;
	@TableField(value = "pinglunren")
	private String pinglunren;
    @TableField(value = "shujuid")
    private String shujuid;
    
	
    @TableField(value = "addtime")
    private Date addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getPinglunneirong() {
        return pinglunneirong;
    }
    public void setPinglunneirong(String pinglunneirong) {
		this.pinglunneirong = pinglunneirong;
    }
	public String getPingfen() {
        return pingfen;
    }
    public void setPingfen(String pingfen) {
		this.pingfen = pingfen;
    }
	public String getBiao() {
        return biao;
    }
    public void setBiao(String biao) {
		this.biao = biao;
    }
	public String getPinglunren() {
        return pinglunren;
    }
    public void setPinglunren(String pinglunren) {
		this.pinglunren = pinglunren;
    }
    public String getShujuid() {
        return shujuid;
    }
    public void setShujuid(String shujuid) {
        this.shujuid = shujuid;
    }
	

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtme(Date addtime) {
        this.addtime = addtime;
    }
}