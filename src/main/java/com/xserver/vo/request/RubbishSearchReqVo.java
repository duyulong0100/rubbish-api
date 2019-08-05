package com.xserver.vo.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Api("RubbishSearch Req Class")
public class RubbishSearchReqVo extends SearchReqVo {

    @ApiModelProperty("所属分类id")
    private Long categoryId;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
