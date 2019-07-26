package com.xserver.common;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.xserver.common.CommonConstant.RESP_SUCCESS;

@ApiModel
public class Response<T> {

    @ApiModelProperty("是否成功")
    private int status = RESP_SUCCESS;// 1成功,其他失败
    @ApiModelProperty("业务异常码")
    private int errCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty("描述信息")
    private String errorMessage;
    @ApiModelProperty("描述信息")
    private String message;
    @ApiModelProperty("商品同步,第三方要求的code")
    private Integer code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty("结果数据")
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private Map<String, Object> extra = new HashMap<>();

    public Response() {
    }

    public Response(T data) {
        this.data = data;
        this.status = RESP_SUCCESS;
    }

    public static Response<Void> success() {
        return new Response(null);
    }

    public static <T> Response<T> success(T data) {
        return new Response(data);
    }

    /**
     * 把F list转成T List
     * @param data
     * @param func
     * @param <F>
     * @param <T>
     * @return
     */
    public static <F, T> Response<Collection<T>> collection(Collection<F> data, Function<F, T> func) {
        return new Response(Collections2.transform(data, func));
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Response extraField(String key, Object value) {
        this.extra.put(key, value);
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getExtra() {
        return extra;
    }
}
