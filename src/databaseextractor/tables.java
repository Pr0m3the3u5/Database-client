/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseextractor;

import java.sql.SQLException;
import java.util.*;
import static databaseextractor.SQL.runSQLReturnValues;

/**
 *
 * @author andrew
 */
public class tables {

    private static final List<table> tableList = new ArrayList<>();

    public static void addToList(table t) {
        tableList.add(t);
    }

    public static List<String> getTables() throws SQLException, ClassNotFoundException {
        String sql = "select * from INFORMATION_SCHEMA.TABLES";

        List<String> listOfTables = new ArrayList<>();
        for (String x : runSQLReturnValues(sql, "TABLE_NAME")) {
            listOfTables.add(x);
        }
        return listOfTables;

    }

    public static List<table> getTableList() {
        return tableList;
    }

}
