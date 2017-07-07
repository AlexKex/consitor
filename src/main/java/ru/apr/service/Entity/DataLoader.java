package ru.apr.service.Entity;

import org.springframework.stereotype.Component;
import ru.apr.service.ConsitorApplication;

import java.sql.*;

@Component
public class DataLoader implements Runnable {

    private String dbDriver;
    private String dbType;
    private String dbUser;
    private String dbPassword;
    private String dbHost;
    private String dbName;
    private String dbPort = "3306";
    private Boolean isMaster = false;
    private String sqlRequest;
    private ResultSet result;

    private Connection connection;
    private Statement statement;

    public void init(){
        try {
            Class.forName(this.dbDriver);
            String dsn = "jdbc:" + this.dbType + "://" + this.dbHost + ":" + this.dbPort + "/" + this.dbName;
            this.connection = DriverManager.getConnection(dsn, this.dbUser, this.dbPassword);
            this.statement = this.connection.createStatement();
        }
        catch (ClassNotFoundException e) {
            ConsitorApplication.logger.error("Class " + this.dbDriver + " not found");
        }
        catch (SQLException e) {
            ConsitorApplication.logger.error(e.getMessage());
        }
    }

    public void complete(){
        try {
            if (this.connection != null) {
                this.connection.close();
                this.connection = null;
            }
        }
        catch (SQLException e) {
            ConsitorApplication.logger.error(e.getMessage());
        }
    }

    public void setDbDriver(String dbDriver) {
        this.dbDriver = dbDriver;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public void setIsMaster(String master) {
        isMaster = Boolean.parseBoolean(master);
    }

    public String getDbDriver() {
        return dbDriver;
    }

    public String getDbType() {
        return dbType;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getDbHost() {
        return dbHost;
    }

    public String getDbName() {
        return dbName;
    }

    public Boolean isMaster() {
        return isMaster;
    }

    public void setSqlRequest(String sqlRequest) {
        this.sqlRequest = sqlRequest;
    }

    @Override
    public void run() {
        ConsitorApplication.logger.info(dbType + " connection has been started for " + dbHost);
        init();

        try {
            result = statement.executeQuery(sqlRequest);
        } catch (SQLException e) {
            ConsitorApplication.logger.error("SQLException with query " + sqlRequest);
        }

        ConsitorApplication.logger.info(dbType + " connection has been completed");
    }

    synchronized public ResultSet getResult(){
        return result;
    }
}
