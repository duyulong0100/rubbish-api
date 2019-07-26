package com.xserver.util.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请求第三方数据库,直接用JDBC
 * 支持一个第三方数据源就够了,以静态工具的方式提供
 */
public class DbUtil {
    private static final Logger log = LoggerFactory.getLogger(DbUtil.class);

    public static boolean executeBatchUpdate(String sql, List<Object[]> parmList) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean b = false;
        conn = DbPoolFactory.CDN_DB_POOL.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            if (parmList != null && parmList.size() > 0) {
                for (int i = 0; i < parmList.size(); i++) {
                    Object[] parm = parmList.get(i);
                    if (parm != null && parm.length > 0) {
                        for (int j = 0; j < parm.length; j++) {
                            ps.setObject(j + 1, parm[j]);
                        }
                        ps.addBatch();
                    }
                }
            }
            int[] num = ps.executeBatch();
            if (num != null && num.length > 0) {
                b = true;
            }
            ps.close();
            return b;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     * SQL 查询
     * @param sql
     *            查询sql 例如select id,title from example where status=?
     * @param params
     *            sql中参数,按sql中参数顺序传值
     * @return 返回值 ,每行都转成map对象
     */
    public static List<Map<String, Object>> query(String sql, Object... params) {
        Connection conn = DbPoolFactory.CDN_DB_POOL.getConnection();
        List<Map<String, Object>> response = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            System.out.println(sql);
            ResultSet result = ps.executeQuery();
            // while (result.next()){
            // System.out.println(result.getString(0));
            // }
            response = convertList(result);
            result.close();
            ps.close();
            return response;
        } catch (Exception e) {
            log.warn("insert error." + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    log.warn("close connection error." + e.getMessage());
                }
            }
        }
        return null;
    }

    public static int executeUpdate(String sql, Object[] parm) {
        Connection conn = DbPoolFactory.CDN_DB_POOL.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            if (parm != null && parm.length > 0) {
                for (int i = 0; i < parm.length; i++) {
                    ps.setObject(i + 1, parm[i]);
                }
            }
            int result = ps.executeUpdate();
            ps.close();
            return result;
        } catch (Exception e) {
            log.warn("insert error." + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    log.warn("close connection error." + e.getMessage());
                }
            }
        }
        return 0;
    }

    private static List convertList(ResultSet rs) throws SQLException {
        List list = new ArrayList();
        ResultSetMetaData md = rs.getMetaData();// 获取键名
        int columnCount = md.getColumnCount();// 获取行的数量
        while (rs.next()) {
            Map rowData = new HashMap();// 声明Map
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnLabel(i), rs.getObject(i));// 获取键名及值
            }
            list.add(rowData);
        }
        return list;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("b3ce54bd889a48b7a3cd54eb5d185d27");
        List<Map<String, Object>> result = query("select fac.size_name as size_name,fmpn.prcd_node_name as prcd_node_name,sum(fac.quantity) as quantity from fac_node_complete_qualified fac join fac_mission_prcd_node_report fmpnr on fac.prcd_node_report_id=fmpnr.id join  fac_mission_prcd_node fmpn on fmpnr.fac_mission_prcd_node_id=fmpn.id where fmpn.prcd_node_name IN('车缝发出','车缝回收','后道质检') AND  fmpn.fac_mission_id='b3ce54bd889a48b7a3cd54eb5d185d27' group by fac.size_id");
        // List<Map<String,Object>>
        // result=query("select fac.size_name as size_name,fmpn.prcd_node_name as prcd_node_name,sum(fac.quantity) as quantity from fac_node_complete_qualified fac join fac_mission_prcd_node_report fmpnr on fac.prcd_node_report_id=fmpnr.id join  fac_mission_prcd_node fmpn on fmpnr.fac_mission_prcd_node_id=fmpn.id where  fmpn.fac_mission_id='b3ce54bd889a48b7a3cd54eb5d185d27' group by fac.size_id");
        for (Map<String, Object> m : result) {
            for (String k : m.keySet()) {
                System.out.println("k:" + k + ",v:" + m.get(k));
            }
        }
    }
}
