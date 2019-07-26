package com.xserver.vo;

import com.xserver.dao.entity.RubbishCategory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Api("RubbishCategory Class")
public class RubbishCategoryVo extends BaseVo {

    @ApiModelProperty("id")
    private long id;
    @ApiModelProperty("名称")
    private String categoryName;
    @ApiModelProperty("描述")
    private String description;
    @ApiModelProperty("状态 0-禁用 1-正常 4-删除")
    private int status;

    public static RubbishCategoryVo entityToVo(RubbishCategory rubbishCategory) {
        RubbishCategoryVo vo = new RubbishCategoryVo();
        vo.setId(rubbishCategory.getId());
        vo.setCategoryName(rubbishCategory.getCategoryName());
        vo.setDescription(rubbishCategory.getDescription());
        vo.setStatus(rubbishCategory.getStatus());
        return vo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
