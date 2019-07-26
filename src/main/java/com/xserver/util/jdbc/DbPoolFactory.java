package com.xserver.util.jdbc;

import java.util.HashMap;
import java.util.Map;

public class DbPoolFactory {
    public final static String JDBC_DRIVER_CLASSNAME = "jdbc.driverClassName";
    public final static String JDBC_URL = "jdbc.url";
    public final static String JDBC_USERNAME = "jdbc.username";
    public final static String JDBC_PASSWORD = "jdbc.password";
    public final static String C3P0_VALIDATE = "c3p0validate";
    public final static String C3P0_MIN_POOL_SIZE = "c3p0.minPoolSize";
    public final static String C3P0_ACQUIRE_INCREMENT = "c3p0.acquireIncrement";
    public final static String C3P0_MAX_POOL_SIZE = "c3p0.maxPoolSize";
    public final static String C3P0_INITIAL_POOL_SIZE = "c3p0.initialPoolSize";
    public final static String C3P0_IDLE_CONNECTION_TEST_PERIOD = "c3p0.idleConnectionTestPeriod";
    public final static DbPool CDN_DB_POOL;
    public final static Map<String, String> MES_DB_CONFIG_MAP = new HashMap<String, String>();
    static {
        config();
        CDN_DB_POOL = new DbPool();
        CDN_DB_POOL.init(MES_DB_CONFIG_MAP);
    }

    private static void config() {
        MES_DB_CONFIG_MAP.put(JDBC_DRIVER_CLASSNAME, "com.mysql.jdbc.Driver");
        MES_DB_CONFIG_MAP.put(JDBC_URL,
                "jdbc:mysql://47.105.140.181:8336/warehouse_pro_ssgs?useUnicode=true&characterEncoding=UTF-8");
        MES_DB_CONFIG_MAP.put(JDBC_USERNAME, "ssgs_query");
        MES_DB_CONFIG_MAP.put(JDBC_PASSWORD, "ssgs.2019@");
        MES_DB_CONFIG_MAP.put(C3P0_VALIDATE, "false");
        MES_DB_CONFIG_MAP.put(C3P0_MIN_POOL_SIZE, "5");
        MES_DB_CONFIG_MAP.put(C3P0_MAX_POOL_SIZE, "40");
        MES_DB_CONFIG_MAP.put(C3P0_ACQUIRE_INCREMENT, "5");
        MES_DB_CONFIG_MAP.put(C3P0_INITIAL_POOL_SIZE, "10");
        MES_DB_CONFIG_MAP.put(C3P0_IDLE_CONNECTION_TEST_PERIOD, "3000");
    }
}
