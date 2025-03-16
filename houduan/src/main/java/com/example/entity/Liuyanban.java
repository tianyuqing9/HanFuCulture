package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


@TableName("liuyanban")
@Data
public class Liuyanban {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

	@TableField(value = "yonghuming")
	private String yonghuming;
	@TableField(value = "cheng")
	private String cheng;
	@TableField(value = "touxiang")
	private String touxiang;
	@TableField(value = "biaoti")
	private String biaoti;
	@TableField(value = "neirong")
	private String neirong;
	@TableField(value = "huifu")
	private String huifu;



    @TableField(value = "addtime")
    private Date addtime;
    @TableField(exist = false)
    private List<Liuyanban> children;
    private Integer parent;

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
	public String getCheng() {
        return cheng;
    }
    public void setCheng(String cheng) {
		this.cheng = cheng;
    }
	public String getTouxiang() {
        return touxiang;
    }
    public void setTouxiang(String touxiang) {
		this.touxiang = touxiang;
    }
	public String getBiaoti() {
        return biaoti;
    }
    public void setBiaoti(String biaoti) {
		this.biaoti = biaoti;
    }
	public String getNeirong() {
        return neirong;
    }
    public void setNeirong(String neirong) {
		this.neirong = neirong;
    }
	public String getHuifu() {
        return huifu;
    }
    public void setHuifu(String huifu) {
		this.huifu = huifu;
    }



    public Date getAddtime() {
        return addtime;
    }

    public void setAddtme(Date addtime) {
        this.addtime = addtime;
    }
}
