package com.leslie.travel.domain;

import java.util.List;

/**
 * @Author LeslieCheni
 * @Date Created in 8:47 2020/5/23 0023
 * @Version JDK1.8
 */
public class PageBean<T> {

    private int totalPage; //总页数
    private int totalCount; //总记录数
    private int currentPage; //当前页码
    private int pageSize;   //每页显示的条数
    private List<T> list;   //每页显示的数据集合

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", list=" + list +
                '}';
    }
}
