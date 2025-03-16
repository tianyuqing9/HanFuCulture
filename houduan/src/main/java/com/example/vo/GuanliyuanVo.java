package com.example.vo;
import com.example.entity.Guanliyuan;
import lombok.Data;
import java.util.List;
@Data
public class GuanliyuanVo extends Guanliyuan {
	private List<Guanliyuan> list;//所有数据
    private long pages;//总页数
    private long total;//总记录数
    private long current;//当前页
    private long currentNum = 10;//当前页数据数

}