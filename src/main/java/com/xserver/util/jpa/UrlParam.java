package com.xserver.util.jpa;

/**
 * 拼接URL请求的参数
 */
public class UrlParam {
    private StringBuffer params;

    public UrlParam() {
        this.params = new StringBuffer();
    }

    public UrlParam add(String key, Object value) {
        if (params.length() > 0) {
            params.append("&");
        }
        params.append(key).append("=");
        if (value == null) {
            params.append("");
        } else {
            params.append(String.valueOf(value));
        }
        return this;
    }

    @Override
    public String toString() {
        return params.toString();
    }

}