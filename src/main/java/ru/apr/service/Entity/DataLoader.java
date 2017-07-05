package ru.apr.service.Entity;

import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    private String dbDriver;
    private String dbType;
    private String dbUser;
    private String dbPassword;
    private String dbHost;
    private String dbName;
    private Boolean isMaster = false;

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




}
