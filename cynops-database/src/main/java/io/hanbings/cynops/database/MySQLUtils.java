package io.hanbings.cynops.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 谁在乎 JDBC 版本啊 <br>
 * 一个 try catch 抹平 JDBC 对应 Mysql 版本问题
 */
@SuppressWarnings("unused")
public class MySQLUtils {
    public static Connection getMysqlConnection(String url, String username, String password) {
        // 先处理 8.x 版本的 Mysql JDBC
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                return DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                // 链接异常 没得救 抛出异常
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            // 版本不对 处理版本
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            try {
                return DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                // 链接异常 没得救 抛出
                ex.printStackTrace();
            }
        }
        return null;
    }
}
