package com.xserver.util.jdbc;

import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.select.Select;

public class Format {
    public static final String SQL_BASE_PAGE = "%s LIMIT %s,%s";// just support
                                                                // mysql

    /**
     * 获取优化后的Count Sql
     */
    public static String countSql(String originalSql) {
        CountSqlParser countSqlParser = new CountSqlParser();
        return countSqlParser.getSmartCountSql(originalSql);
    }

    /**
     * 获取支持分页的Page Sql
     */
    public static String pageSql(String originalSql, int pageNo, int pageSize) {
        String tempSql;
        try {
            Select stmt = (Select) CCJSqlParserUtil.parse(originalSql);
            tempSql = String.valueOf(stmt.getSelectBody());
        } catch (Throwable e) {
            tempSql = originalSql;
        }
        return String.format(SQL_BASE_PAGE, tempSql, (pageNo - 1) * pageSize, pageNo * pageSize);
    }

}