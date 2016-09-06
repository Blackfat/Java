package com.shsasc.a4fuser.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cascc.avidm.util.DebugLog;

/**
 * 数据连接
 * 
 * @author 王飞洋
 * @version 2016-9-5
 */
public class DataConnectionUtil {
    
    private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    public static String                   url;

    public static String                   driver;

    public static String                   username;

    public static String                   password;
    
    public static void init(String url, String driver, String username, String password ){
        DataConnectionUtil.url = url;
        DataConnectionUtil.driver = driver;
        DataConnectionUtil.username = username;
        DataConnectionUtil.password = password;
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection conn = tl.get();
        if (conn == null) {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            tl.set(conn);
        }
        return conn;
    }

    public static void closeConnection() {
        Connection con = tl.get();
        try {
            
            if (con != null) {
                con.close();
                tl.set(null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            DebugLog.error(DataConnectionUtil.class, "closeConnection");
        }
    }

    public static void closeStatement(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            DebugLog.error(DataConnectionUtil.class, "closeStatement");
        }
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            DebugLog.error(DataConnectionUtil.class, "closeResultSet");
        }
    }

}
