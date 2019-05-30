package com.ufi.pdioms.resource.common.model;

import java.io.Serializable;
import java.util.List;

public class PageResult implements Serializable {
    //当前页
    private long pageNo;

    //页面大小，数据的存放数量
    private long pageSize;

    //总记录数
    private long total;

    //总页数
    private long pages;

    //列表；占位符，如果赋值以后是不可以修改其里面的值的
    private List<?> data;

    public long getPageNo() {
        return pageNo;
    }

    public void setPageNo(long pageNo) {
        this.pageNo = pageNo;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public PageResult(long pageNo, long pageSize, long total, long pages, List<?> data) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.total = total;
        this.pages = pages;
        this.data = data;
    }
}
