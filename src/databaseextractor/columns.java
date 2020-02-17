/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseextractor;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author andrew
 */
public class columns {

    public static void getColumns(table t) throws SQLException, ClassNotFoundException {
        String sql = ("select * from INFORMATION_SCHEMA.COLUMNS where TABLE_NAME = '" + t.getName() + "'");
        ArrayList<String> listOfColumns = new ArrayList<>();
        ArrayList<String> listOfLengths = new ArrayList<>();
        ArrayList<String> listOfNullables = new ArrayList<>();
        ArrayList<String> listOfTypes = new ArrayList<>();
        listOfColumns = SQL.runSQLReturnValues(sql, "COLUMN_NAME");
        listOfTypes = SQL.runSQLReturnValues(sql, "DATA_TYPE");
        listOfLengths = SQL.runSQLReturnValues(sql, "CHARACTER_MAXIMUM_LENGTH");
        listOfNullables = SQL.runSQLReturnValues(sql, "IS_NULLABLE");        
        for (int i = 0; i < listOfColumns.size(); i++) {
            column c = new column(listOfColumns.get(i), listOfTypes.get(i), t, listOfNullables.get(i), listOfLengths.get(i), t.getName());
        }
    }
    
    
}
