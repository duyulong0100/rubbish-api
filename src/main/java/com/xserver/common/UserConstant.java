package com.xserver.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserConstant {
    public static final int USER_TYPE_SUPPLIER = 2; // 工厂
    public static final int USER_TYPE_SHOP = 3; // 店铺
    public static final int USER_TYPE_PLATFORM = 4; // 平台运营
    public static final int USER_TYPE_SYSTEM = 99; // 系统

    public static final int USER_STATUS_DISABLE = 0;
    public static final int USER_STATUS_ENABLE = 1;
    public static final int USER_STATUS_DELETE = 4;

    public static final int USER_ACCEPT_RIGHT_INIT = 0;// 接单权限初始化
    public static final int USER_ACCEPT_RIGHT_ENABLE = 1;// 有接单权限
    public static final int USER_ACCEPT_RIGHT_APPLY = 2;// 接单权限申请

    public static final int ROLE_TYPE_DESIGNER = 1;// 设计师
    public static final int ROLE_TYPE_SUPPLIER = 2;// 工厂
    public static final int ROLE_TYPE_SHOP = 3;// 店铺
    public static final int ROLE_TYPE_PLATFORM = 4;// 平台运营
    public static final int ROLE_TYPE_ADMIN = 99; // 系统管理员

    public static final int PERMISSION_FIRSTORDER_ADD = 321;// 首单下单
    public static final int PERMISSION_ORDER_ADD = 322;// 补单下单

    public static final Map<Integer, String> roleCodeMap = new HashMap<>();
    static {
        roleCodeMap.put(ROLE_TYPE_DESIGNER, "designer");
        roleCodeMap.put(ROLE_TYPE_SUPPLIER, "supplier");
        roleCodeMap.put(ROLE_TYPE_SHOP, "shop");
        roleCodeMap.put(ROLE_TYPE_PLATFORM, "platform");
    }

    public static final List<Integer> adminPers = new ArrayList<>();

    static {
        adminPers.add(1);
        adminPers.add(11);
        adminPers.add(12);
        adminPers.add(13);
    }

}