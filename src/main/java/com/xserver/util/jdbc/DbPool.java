package com.xserver.util.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * C3PO 获得连接
 * @author Administrator
 */
public class DbPool {
    private ComboPooledDataSource cpds = null;

    /**
     * 初始化
     */
    public void init(Map<String, String> map) {
        // 建立数据库连接池
        String DRIVER_NAME = map.get("jdbc.driverClassName"); // 驱动器
        String DATABASE_URL = map.get("jdbc.url"); // 数据库连接url
        String DATABASE_USER = map.get("jdbc.username"); // 数据库用户名
        String DATABASE_PASSWORD = map.get("jdbc.password"); // 数据库密码
        int Min_PoolSize = 5;
        int Max_PoolSize = 50;
        int Acquire_Increment = 5;
        int Initial_PoolSize = 10;
        // 每隔3000s测试连接是否可以正常使用
        int Idle_Test_Period = 3000;
        // 每次连接验证连接是否可用
        String Validate = map.get("c3p0.validate");
        if ("".equals(Validate)) {
            Validate = "false";
        }
        // 最小连接数
        try {
            Min_PoolSize = Integer.parseInt(map.get("c3p0.minPoolSize"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // 增量条数
        try {
            Acquire_Increment = Integer.parseInt(map.get("c3p0.acquireIncrement"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // 最大连接数
        try {
            Max_PoolSize = Integer.parseInt(map.get("c3p0.maxPoolSize"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // 初始化连接数
        try {
            Initial_PoolSize = Integer.parseInt(map.get("c3p0.initialPoolSize"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // 每隔Idle_Test_Period s测试连接是否可以正常使用
        try {
            Idle_Test_Period = Integer.parseInt(map.get("c3p0.idleConnectionTestPeriod"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            this.cpds = new ComboPooledDataSource();
            this.cpds.setDriverClass(DRIVER_NAME); // 驱动器
            this.cpds.setJdbcUrl(DATABASE_URL); // 数据库url
            this.cpds.setUser(DATABASE_USER); // 用户名
            this.cpds.setPassword(DATABASE_PASSWORD); // 密码
            this.cpds.setInitialPoolSize(Initial_PoolSize); // 初始化连接池大小
            this.cpds.setMinPoolSize(Min_PoolSize); // 最少连接数
            this.cpds.setMaxPoolSize(Max_PoolSize); // 最大连接数
            this.cpds.setAcquireIncrement(Acquire_Increment); // 连接数的增量
            this.cpds.setIdleConnectionTestPeriod(Idle_Test_Period); // 测连接有效的时间间隔
            this.cpds.setTestConnectionOnCheckout(Boolean.getBoolean(Validate)); // 每次连接验证连接是否可用
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 取得链接
     * @return
     */
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = this.cpds.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
    }

    /**
     * 释放连接
     */
    public void release() {
        try {
            if (this.cpds != null) {
                this.cpds.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}