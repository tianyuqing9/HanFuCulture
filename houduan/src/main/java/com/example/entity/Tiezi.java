package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@TableName("tiezi")
@Data
public class Tiezi {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

	@TableField(value = "suoshubankuai")
	private String suoshubankuai;
	@TableField(value = "biaoti")
	private String biaoti;
	@TableField(value = "neirong")
	private String neirong;
	@TableField(value = "leixing")
	private String leixing;
	@TableField(value = "fujian")
	private String fujian;
	@TableField(value = "fatieren")
	private String fatieren;
	@TableField(value = "fid")
	private String fid;

    @TableField(value = "dianzan")
    private Integer dianzan;




    @TableField(value = "addtime")
    private Date addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getSuoshubankuai() {
        return suoshubankuai;
    }
    public void setSuoshubankuai(String suoshubankuai) {
		this.suoshubankuai = suoshubankuai;
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
	public String getLeixing() {
        return leixing;
    }
    public void setLeixing(String leixing) {
		this.leixing = leixing;
    }
	public String getFujian() {
        return fujian;
    }
    public void setFujian(String fujian) {
		this.fujian = fujian;
    }
	public String getFatieren() {
        return fatieren;
    }
    public void setFatieren(String fatieren) {
		this.fatieren = fatieren;
    }
	public String getFid() {
        return fid;
    }
    public void setFid(String fid) {
		this.fid = fid;
    }

    public Integer getDianzan() {
        return dianzan;
    }
    public void setDianzan(Integer dianzan) {
        this.dianzan = dianzan;
    }
//
//    public String getDianzan_c() {
//        return dianzan_c;
//    }
//    public void setDianzan_c(String dianzan_c) {
//        this.dianzan_c = dianzan_c;
//    }



    public Date getAddtime() {
        return addtime;
    }

    public void setAddtme(Date addtime) {
        this.addtime = addtime;
    }
}
