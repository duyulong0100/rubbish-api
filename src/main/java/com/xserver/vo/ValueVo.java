package com.xserver.vo;

/**
 * Created by lkk on 2018/12/28.
 */
public class ValueVo<T> extends BaseVo {
    private T value;

    public ValueVo(T v) {
        value = v;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
