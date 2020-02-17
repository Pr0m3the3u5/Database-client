/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseextractor;

import java.sql.SQLException;
import java.util.*;


/**
 *
 * @author andrew
 */
public class table {

    private final String tableName;
    private final List<column> columnList = new ArrayList<>();

    public table(String name) throws SQLException, ClassNotFoundException {
        this.tableName = name;
        tables.addToList(this);
        columns.getColumns(this);
    }

    public String getName() {
        return this.tableName;
    }
    
    public List<column> getColumns(){
        return this.columnList;
    }
    
    public void addColumn(column c){
         columnList.add(c);
     }

}
