/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseextractor;

import java.sql.SQLException;

/**
 *
 * @author andrew
 */
public class column {

    private final String name;
    private final String dataType;
    private final boolean isNullable;
    private final String dataTypeLength;
    private boolean isUnqiue;
    private final String tableBelong;
    private boolean isPrimaryKey;

    public column(String name, String type, table t, String nullable, String length, String table) throws SQLException, ClassNotFoundException {
        this.name = name;
        this.dataType = type;
        this.isNullable = ("NO".equals(nullable));
        this.dataTypeLength = length;
        this.tableBelong = table;
        t.addColumn(this);
        this.isUnique();
        this.isPrimaryKey();
    }

    public String getColumnName() {
        return this.name;
    }

    public String getTableBelong() {
        return this.tableBelong;
    }

    public String getLength() {
        return this.dataTypeLength;
    }

    public String getColumnType() {
        return this.dataType;
    }

    private void isUnique() throws SQLException, ClassNotFoundException {
        String sql = "select t.CONSTRAINT_TYPE, c.COLUMN_NAME, c.TABLE_NAME from INFORMATION_SCHEMA.TABLE_CONSTRAINTS t "
                + "inner join INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE c on t.CONSTRAINT_NAME=c.CONSTRAINT_NAME "
                + "where c.COLUMN_NAME = '" + this.name + "' and t.CONSTRAINT_TYPE = 'UNIQUE' and c.TABLE_NAME = '" + this.getTableBelong() + "'";
        String unuqueness = SQL.runSQLReturnValue(sql, "CONSTRAINT_TYPE");
        try {
            this.isUnqiue = unuqueness.equals("UNIQUE");
        } catch (NullPointerException E) {
        }

    }

    private void isPrimaryKey() throws SQLException, ClassNotFoundException {
        String sql = "select t.CONSTRAINT_TYPE, c.COLUMN_NAME, c.TABLE_NAME from INFORMATION_SCHEMA.TABLE_CONSTRAINTS t "
                + "inner join INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE c on t.CONSTRAINT_NAME=c.CONSTRAINT_NAME "
                + "where c.COLUMN_NAME = '" + this.name + "' and t.CONSTRAINT_TYPE = 'PRIMARY KEY' and c.TABLE_NAME = '" + this.getTableBelong() + "'";
        String unuqueness = SQL.runSQLReturnValue(sql, "CONSTRAINT_TYPE");
        try {
            this.isPrimaryKey = unuqueness.equals("PRIMARY KEY");
        } catch (Exception e) {
        }
    }

    public String getCreateColumnSql() {
        String columnSql = this.name + " ";
        columnSql += (this.dataTypeLength != null) ? this.dataType + "(" + this.dataTypeLength + ")" : this.dataType;
        columnSql += (this.isNullable) ? " NOT NULL" : "";
        columnSql += (this.isPrimaryKey) ? " PRIMARY KEY" : "";
        columnSql += (this.isUnqiue) ? " UNIQUE" : "";
        return columnSql + ",";
    }
}
