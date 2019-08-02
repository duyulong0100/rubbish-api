package com.xserver.common;

public class ErrorCodeConstant {
    public static final String EC_PREFIX = "ec.";
    // 全局异常模块错误码
    public static final int EC_COMMON_PARAM_ERROR = 1000000;// 请求参数错误
    public static final int EC_COMMON_UNKNOWN_ERROR = 500;// 服务器内部出错
    public static final int EC_COMMON_UNAUTH = 401;// 未登录
    public static final int EC_COMMON_REQ_METHOD_ERROR = 405;// 请求方法不对

    public static final int EC_RUBBISH_CATEGORY_NAME_REPEAT_ERROR = 1000001;// 分类名称重复
    public static final int EC_RUBBISH_CATEGORY_BIND_ITEM_ERROR = 1000002;// 分类下有垃圾信息，不可删除

    public static final int EC_RUBBISH_ITEM_NAME_REPEAT_ERROR = 2000001;// 垃圾名称重复

    public static final int EC_COMMON_FILE_UPLOAD_ERR = 9000001;// 文件上传失败
}