package com.xserver.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "rubbish_category", indexes = {@Index(name = "Fk_rubbishCategoryid", columnList = "id")})
public class RubbishCategory extends IdEntity {
    @Column(length = 50, nullable = false)
    private String categoryName;// 垃圾分类名称
    @Column
    private int status;// 0-禁用 1-正常 4-删除

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
