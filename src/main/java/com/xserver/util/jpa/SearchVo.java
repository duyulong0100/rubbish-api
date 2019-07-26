package com.xserver.util.jpa;

import com.xserver.vo.BaseVo;

import java.util.Map;
import java.util.Map.Entry;

public class SearchVo extends BaseVo {
    public String fieldName;
    public Object value;
    public Op op;// 操作的表达式

    public SearchVo() {

    }

    public SearchVo(String fieldName, Op op, Object value) {
        this.fieldName = fieldName;
        this.value = value;
        this.op = op;
    }

    public static SearchVo isNull(String fieldName) {
        return new SearchVo(fieldName, Op.IS_NULL, null);
    }

    public static SearchVo notNull(String fieldName) {
        return new SearchVo(fieldName, Op.NOT_NULL, null);
    }

    public static String parse(Map<String, SearchVo> searchParams) {
        if (searchParams == null || searchParams.isEmpty()) {
            return "";
        }
        StringBuffer params = new StringBuffer();
        for (Entry<String, SearchVo> entry : searchParams.entrySet()) {
            SearchVo filter = entry.getValue();
            params.append("&").append(entry.getKey()).append("=").append(filter.getValue());
        }
        return params.toString();
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Op getOp() {
        return op;
    }

    public void setOp(Op op) {
        this.op = op;
    }
}