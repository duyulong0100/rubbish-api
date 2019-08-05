package com.xserver.vo.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Api("Search Req Class")
public class SearchReqVo {

    @ApiModelProperty("关键/字词")
    private String keyword;
    @ApiModelProperty("第几页")
    private int page = 1;
    @ApiModelProperty("每页几条")
    private int size = 10;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
