package com.xserver.vo;

import com.xserver.dao.entity.RubbishItem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@Api("RubbishItem Class")
public class RubbishItemVo extends BaseVo {
    @ApiModelProperty("id")
    private long id;
    @ApiModelProperty("名称")
    private String itemName;
    @ApiModelProperty("分类")
    private RubbishCategoryVo belongCategory;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("状态 0-禁用 1-正常 4-删除")
    private int status;

    public static RubbishItemVo entityToVo(RubbishItem rubbishItem) {
        RubbishItemVo vo = new RubbishItemVo();
        vo.setId(rubbishItem.getId());
        vo.setItemName(rubbishItem.getItemName());
        vo.setBelongCategory(RubbishCategoryVo.entityToVo(rubbishItem.getBelongCategory()));
        vo.setCreateTime(rubbishItem.getCreateTime());
        vo.setStatus(rubbishItem.getStatus());
        return vo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public RubbishCategoryVo getBelongCategory() {
        return belongCategory;
    }

    public void setBelongCategory(RubbishCategoryVo belongCategory) {
        this.belongCategory = belongCategory;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
