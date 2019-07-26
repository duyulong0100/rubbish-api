package com.xserver.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;

import javax.annotation.Resource;
import java.util.Locale;

import static com.xserver.common.CommonConstant.RESP_SUCCESS;
import static com.xserver.common.ErrorCodeConstant.EC_PREFIX;

public class BaseService {
    protected final Log LOG = LogFactory.getLog(this.getClass());

    protected ObjectMapper objectMapper = new ObjectMapper();
    @Resource
    private MessageSource reloadMessageSource;

    /**
     * 接口返回的成功结果
     * @param response
     * @param content
     * @return
     */
    protected Response success(Response response, Object content) {
        response.setStatus(RESP_SUCCESS);
        response.setData(content);
        return response;
    }

    /**
     * 接口返回的成功结果
     * @param content
     *            结果数据内容
     * @return
     */
    protected Response success(Object content) {
        Response response = new Response();
        response.setStatus(RESP_SUCCESS);
        response.setData(content);
        return response;
    }

    protected Response error(Response response, int errorCode) {
        response.setStatus(0);
        response.setErrCode(errorCode);
        try {
            response.setErrorMessage(reloadMessageSource.getMessage(EC_PREFIX + errorCode, null, Locale.CHINA));
        } catch (Exception e) {
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
}
