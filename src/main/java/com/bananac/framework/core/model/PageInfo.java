package com.bananac.framework.core.model;

/**
 * 分页
 * @author xiaojf 294825811@qq.com
 */
public class PageInfo {
    /**总记录数*/
    private int total;
    /**当前页*/
    private int page;
    /**每页显示记录数*/
    private int rows;

    public PageInfo() {
        super();
    }

    public PageInfo(int total, int page, int rows) {
        super();
        this.total = total;
        this.page = page;
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

}
