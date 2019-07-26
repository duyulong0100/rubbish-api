package com.xserver.common;

import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import com.xserver.exception.UnauthorizedException;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Session {

    private static final String USER_ID = "USER_ID";

    private static final String USER_NAME = "USER_NAME";

    private static final String NICK_NAME = "NICK_NAME";

    private static final String USER_TYPE = "USER_TYPE";

    private static final String ROLE_ID = "ROLE_ID";

    private static final String SHOP_ID = "SHOP_ID";

    private static final String SUPPLIER_ID = "SUPPLIER_ID";

    private static final String SUPPLIER_NO = "SUPPLIER_NO";

    private final HttpSession session;

    public Session(HttpSession session) {
        this.session = session;
    }

    public void invalidate() {
        session.invalidate();
    }

    public long getUserId() {
        Object value = session.getAttribute(USER_ID);
        if (value != null) {
            Long userId = Longs.tryParse(value.toString());
            if (userId != null) {
                return userId;
            }
        }
        throw new UnauthorizedException("未登录用户", 1000005);
    }

    public void setUserId(long userId) {
        set(USER_ID, String.valueOf(userId));
    }

    public String getUserName() {
        Object value = session.getAttribute(USER_NAME);
        if (value != null) {
            String userName = value.toString();
            if (userName != null) {
                return userName;
            }
        }
        return null;
    }

    public void setUserName(String userName) {
        set(USER_NAME, String.valueOf(userName));
    }

    public String getNickName() {
        Object value = session.getAttribute(NICK_NAME);
        if (value != null) {
            String nickName = value.toString();
            if (nickName != null) {
                return nickName;
            }
        }
        return null;
    }

    public void setNickName(String nickName) {
        set(NICK_NAME, String.valueOf(nickName));
    }

    public int getUserType() {
        Object value = session.getAttribute(USER_TYPE);
        if (value != null) {
            int userType = Ints.tryParse(value.toString());
            if (userType != 0) {
                return userType;
            }
        }
        return 0;
    }

    public void setUserType(int userType) {
        set(USER_TYPE, String.valueOf(userType));
    }

    public long getRoleId() {
        Object value = session.getAttribute(ROLE_ID);
        if (value != null) {
            Long roleId = Longs.tryParse(value.toString());
            if (roleId != null) {
                return roleId;
            }
        }
        return 0;
    }

    public void setRoleId(long roleId) {
        set(ROLE_ID, String.valueOf(roleId));
    }

    public long getShopId() {
        Object value = session.getAttribute(SHOP_ID);
        if (value != null) {
            Long shopId = Longs.tryParse(value.toString());
            if (shopId != null) {
                return shopId;
            }
        }
        return 0;
    }

    public void setShopId(long shopId) {
        set(SHOP_ID, String.valueOf(shopId));
    }

    public long getSupplierId() {
        Object value = session.getAttribute(SUPPLIER_ID);
        if (value != null) {
            Long supplierId = Longs.tryParse(value.toString());
            if (supplierId != null) {
                return supplierId;
            }
        }
        return 0;
    }

    public void setSupplierId(long supplierId) {
        set(SUPPLIER_ID, String.valueOf(supplierId));
    }

    public String getSupplierNo() {
        Object value = session.getAttribute(SUPPLIER_NO);
        if (value != null) {
            String supplierNo = value.toString();
            if (supplierNo != null) {
                return supplierNo;
            }
        }
        return null;
    }

    public void setSupplierNo(String supplierNo) {
        set(SUPPLIER_NO, String.valueOf(supplierNo));
    }

    public void set(String key, Object value) {
        if (value == null) {
            session.removeAttribute(key);
        } else {
            session.setAttribute(key, value.toString());
        }
    }

    public Object get(String key) {
        return session.getAttribute(key);
    }

    public static class Resolver implements HandlerMethodArgumentResolver {

        @Override
        public boolean supportsParameter(MethodParameter parameter) {
            return parameter.getParameterType().equals(Session.class);
        }

        @Override
        public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

            HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
            return new Session(request.getSession());
        }
    }

}
