package org.promisedland.minecraft.utils;

import org.promisedland.minecraft.EraAuth;

import java.sql.*;
import java.util.Arrays;
import java.util.function.Supplier;

public class DataBase {
    public static Connection connection;
    public static Statement statement;
    public static final String SEARCH_VALUE_SQL = "select %s from %s where %s = '%s'";
    public static final String INSERT_VALUE_SQL = "INSERT INTO ";
    public static final String EXIST_ID_SQL = "SELECT EXISTS(SELECT 1 FROM %s WHERE %s = '%s')";
    public static void initMysql() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(
                String.format("jdbc:mysql://%s:%s/%s",
                    Config.getConfig("database.ip"),
                    Config.getConfig("database.port"),
                    Config.getConfig("database.database")),
                Config.getConfig("database.user"),
                Config.getConfig("database.password")
        );
        statement = connection.createStatement();
    }
    /**
     * @Author BatryCC
     * @Description 搜索某个特定的值str1为要查询的键，str2为表名称，str3为用来查询的键，str4为键值。额外代码用来防SQL注入 -BatryCC
     * @Date 20:54 2024/8/9
     * @Param [table,query,key,value]
     * @return java.lang.String
    */
    public static String searchValue(String table, String query,  String key, String value){
        try {
            String sql = String.format(SEARCH_VALUE_SQL, query, table, key);
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, value);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getString(1);
                    }
                }
            }
            return null;
        }catch (SQLException se){
            EraAuth.LOGGER.warning(se.getMessage());
        }
        return null;
    }
    /**
     * @Author BatryCC
     * @Description 插入一个新数据行 -BatryCC
     * @Date 21:21 2024/8/9
     * @Param [table, columns, values]
     * @return boolean
    */
    public static boolean insertValue(String table, String[] columns, String[] values){
        try {
            if (columns.length != values.length) {
                return false;
            }

            StringBuilder sqlBuilder = new StringBuilder(INSERT_VALUE_SQL);
            sqlBuilder.append(table).append(" (");

            // 添加列名
            sqlBuilder.append(String.join(", ", columns));

            sqlBuilder.append(") VALUES (");

            // 添加占位符
            String[] placeholders = new String[values.length];
            Arrays.fill(placeholders, "?");
            sqlBuilder.append(String.join(", ", placeholders));

            sqlBuilder.append(")");
            String sql = sqlBuilder.toString();

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                // 设置值
                for (int i = 0; i < values.length; i++) {
                    pstmt.setString(i + 1, values[i]);
                }

                int affectedRows = pstmt.executeUpdate();
                return affectedRows > 0;
            }
        }catch (SQLException se){
            EraAuth.LOGGER.warning(se.getMessage());
        }
        return false;
    }
    /**
     * @Author BatryCC
     * @Description 检测数据库内是否存在某个键为某个值的列 -BatryCC
     * @Date 22:13 2024/8/9
     * @Param [table, key, value]
     * @return boolean
    */
    public static boolean checkExists(String table, String key,String value) {
        try {
            String sql = String.format(EXIST_ID_SQL, table, key, value);
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, value);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getBoolean(1);
                    }
                }
            }
            return false;
        }catch (SQLException se){
            EraAuth.LOGGER.warning(se.getMessage());
        }
        return false;
    }
}
