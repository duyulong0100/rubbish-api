package com.xserver.dao.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rubbish_item", indexes = {@Index(name = "Fk_rubbishItemid", columnList = "id")})
public class RubbishItem extends IdEntity {
    @Column(length = 50, nullable = false)
    private String itemName;//名称
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private RubbishCategory belongCategory;// 所属分类
    @Column(nullable = false, columnDefinition = "int default 1")
    private int status;// 状态 ;0-禁用 1-正常 4-删除
    @Column
    private Date createTime;// 注册时间

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public RubbishCategory getBelongCategory() {
        return belongCategory;
    }

    public void setBelongCategory(RubbishCategory belongCategory) {
        this.belongCategory = belongCategory;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
