package com.example.SalaryManagementSystem.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BaseDao {

    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://192.168.111.129:3306/test?useUnicode=true&characterEncoding=GBK";
    private static String user = "root";
    private static String password = "000000";

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void closeAll(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }


    public ArrayList<Map<String,Object>> querySQL(String preparedSql, Object... param) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        ArrayList<Map<String,Object>> result = new ArrayList<>();
        /* 处理SQL,执行SQL */
        try {
            conn = getConnection(); // 得到数据库连接
            pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    pstmt.setObject(i + 1, param[i]); // 为预编译sql设置参数
                }
            }
            resultSet = pstmt.executeQuery(); // 执行SQL语句
            ResultSetMetaData metaData = resultSet.getMetaData();
            int count = metaData.getColumnCount();
            while (resultSet.next()){

                Map<String, Object> map = new HashMap<>();
                for (int i = 1; i <=count; i++){
                    String columnLabel = metaData.getColumnLabel(i);
                    Object v = resultSet.getObject(i);
                    map.put(columnLabel,v);
                }
                result.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 处理SQLException异常
        } finally {
            try {
                BaseDao.closeAll(conn, pstmt, null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public int updateSQL(String preparedSql, Object... param) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int row = 0;
        /* 处理SQL,执行SQL */
        try {
            conn = getConnection(); // 得到数据库连接
            pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    pstmt.setObject(i + 1, param[i]); // 为预编译sql设置参数
                }
            }
            row = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // 处理SQLException异常
        } finally {
            try {
                BaseDao.closeAll(conn, pstmt, null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return row;
    }

}
