/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseextractor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 *
 * @author andrew
 */
public class SQL {

    private static final database targetDatabase = new database("com.microsoft.sqlserver.jdbc.SQLServerDriver", "adw", "1433", "Amiibo", "sa", "928326Blue", "jdbc", "sqlserver");

    public static void runSQLNoReturn(String sql) {
        try {
            Class.forName(targetDatabase.getDriver());
            String connectionUrl = targetDatabase.getConnectionUrl();
            Connection con = DriverManager.getConnection(connectionUrl);
            Statement stmt = con.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.toString());
        } catch (ClassNotFoundException cE) {
            System.out.println("Class Not Found Exception: " + cE.toString());
        }
    }

    public static ArrayList<String> runSQLReturnValues(String sql, String columnName) throws SQLException, ClassNotFoundException {
        Class.forName(targetDatabase.getDriver());
        String connectionUrl = targetDatabase.getConnectionUrl();
        Connection con = DriverManager.getConnection(connectionUrl);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ArrayList<String> tables = new ArrayList<>();
        while (rs.next()) {
            String result = rs.getString(columnName);
            tables.add(result);
        }
        return tables;
    }
    
    public static String runSQLReturnValue(String sql, String columnName) throws SQLException, ClassNotFoundException {
        Class.forName(targetDatabase.getDriver());
        String connectionUrl = targetDatabase.getConnectionUrl();
        Connection con = DriverManager.getConnection(connectionUrl);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        String value = null;
        if (rs.next()) {
            value =  rs.getString(columnName);
        }
        return value;
    }
}
