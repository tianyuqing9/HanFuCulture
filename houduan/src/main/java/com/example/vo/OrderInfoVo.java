package com.example.vo;

import com.example.entity.OrderInfo;

import java.util.List;

public class OrderInfoVo extends OrderInfo {


    private List<OrderInfo> list;//所有数据
    private long pages;//总页数
    private long total;//总记录数
    private long current;//当前页
    private long currentNum;//当前页数据数

    public List<OrderInfo> getList() {
        return list;
    }

    public void setList(List<OrderInfo> list) {
        this.list = list;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public long getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(long currentNum) {
        this.currentNum = currentNum;
    }
}
