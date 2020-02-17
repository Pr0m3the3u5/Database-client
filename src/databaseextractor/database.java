/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseextractor;

/**
 *
 * @author andrew
 */
public class database {

    private final String driver;
    private final String server;
    private final String port;
    private final String databaseName;
    private final String user;
    private final String password;
    private final String connectionType;
    private final String type;

    public database(String driver, String server, String port, String databaseName, String user, String password, String connection, String type) {
        this.driver = driver;
        this.server = server;
        this.port = port;
        this.databaseName = databaseName;
        this.user = user;
        this.password = password;
        this.connectionType = connection;
        this.type = type;
    }

    public String getConnectionUrl() {
        String connectionUrl = this.connectionType + ":" + this.type + "://" + this.server + ":" + this.port + ";databaseName=" + this.databaseName + ";user=" + this.user + ";password=" + this.password + ";";
        return connectionUrl;
    }

    public String getDriver() {
        return this.driver;
    }

    public String getUser() {
        return this.user;
    }

}
