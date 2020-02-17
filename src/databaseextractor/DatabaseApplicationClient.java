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
public class DatabaseApplicationClient {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // TODO code application logic here
        for (String t : tables.getTables()) {
            table tableObject = new table(t);
            System.out.println("CREATE TABLE "+tableObject.getName()+"(");
            for (column c : tableObject.getColumns()) {
                System.out.println(c.getCreateColumnSql());
            }
            System.out.println(");");
        }
    }
}
