package com.xserver.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "example", uniqueConstraints = { @UniqueConstraint(columnNames = { "title", "status" }) })
public class Example extends IdEntity {
    @Column(length = 50, nullable = true)
    private String title;// 标题
    @Column(length = 500, nullable = true, name = "description")
    private String desc;// 描述
    @Column(columnDefinition = "int default 1")
    private Integer status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
