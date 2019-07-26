package com.xserver.util.jpa;

import com.xserver.vo.BaseVo;
import org.springframework.data.domain.Sort;

public class OrderVo extends BaseVo {
    private String field;
    private String direction;

    public OrderVo() {

    }

    public OrderVo(String field, String direction) {
        this.field = field;
        this.direction = direction;
    }

    public Sort.Direction getDirection() {
        if ("desc".equalsIgnoreCase(direction)) {
            return Sort.Direction.DESC;
        } else {
            return Sort.Direction.ASC;
        }
    }

    public static OrderVo ASC(String field) {
        return new OrderVo(field, Sort.Direction.ASC.name());
    }

    public static OrderVo DESC(String field) {
        return new OrderVo(field, Sort.Direction.DESC.name());
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

}