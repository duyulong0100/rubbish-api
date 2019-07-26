package com.xserver.util.jpa;

import com.xserver.vo.BaseVo;

public class RequestVo extends BaseVo {
    private Integer pageSize;// 每页条数
    private Integer pageNo;// 当前页
    private String searchParams;// 查询条件
    private String orderParams;// 排序条件

    public Integer getPageSize() {
        if (pageSize == null) {
            return SpecUtils.DEFAULT_PAGE_SIZE;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        if (pageNo == null) {
            return SpecUtils.DEFAULT_PAGE_NO;
        }
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public String getSearchParams() {
        return searchParams;
    }

    public void setSearchParams(String searchParams) {
        this.searchParams = searchParams;
    }

    public String getOrderParams() {
        return orderParams;
    }

    public void setOrderParams(String orderParams) {
        this.orderParams = orderParams;
    }

}