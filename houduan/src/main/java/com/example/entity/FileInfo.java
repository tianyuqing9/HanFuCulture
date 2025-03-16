package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@TableName("file")
@Data
public class FileInfo {
	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;
	@TableField(value = "originName")
	private String originName;
	@TableField(value = "fileName")
	private String fileName;
}
