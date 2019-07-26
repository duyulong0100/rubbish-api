package com.xserver.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.xserver.common.CommonConstant.RESP_SUCCESS;
import static com.xserver.common.ErrorCodeConstant.EC_PREFIX;

public class BaseController<T> {
    protected final Log LOG = LogFactory.getLog(this.getClass());
    protected static final String PAGE_NO = "1";// 默认当前页
    protected static final String PAGE_SIZE = "10";// 默认每页条数
    @Resource
    protected MessageSource reloadMessageSource;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 接口返回的成功结果
     * @param content
     *            结果数据内容
     * @return
     */
    protected Response success(T content) {
        Response result = new Response();
        result.setStatus(RESP_SUCCESS);
        result.setData(content);
        return result;
    }

    /**
     * 接口返回的错误结果
     * @param message
     *            错误信息
     * @return
     */
    protected Response error(String message) {
        Response result = new Response();
        result.setErrorMessage(message);
        result.setStatus(0);
        return result;
    }

    protected Response error(Response response, int errorCode) {
        response.setStatus(0);
        try {
            response.setErrorMessage(reloadMessageSource.getMessage(EC_PREFIX + errorCode, null, null));
            response.setErrCode(errorCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    protected Response error(int errorCode) {
        Response response = new Response();
        response.setStatus(0);
        response.setErrCode(errorCode);
        try {
            response.setErrorMessage(reloadMessageSource.getMessage(EC_PREFIX + errorCode, null, Locale.CHINA));
        } catch (Exception e) {
        }
        return response;
    }

    protected Response success() {
        return success(null);
    }

}