package ru.apr.service.Monitor.Checker;

import java.util.ArrayList;
import java.util.HashMap;

abstract public class AbstractChecker {
    protected String checkFactor;
    protected Long checkInterval;
    protected HashMap<String, String> checkResult = new HashMap<>();

    abstract public Object getCheckFactor();
    abstract public void setCheckFactor(Object checkFactor);

    public void setCheckInterval(String interval) {
        this.checkInterval = Long.parseLong(interval);
    }

    public Long getCheckInterval() {
        return checkInterval;
    }

    public HashMap<String, String> getCheckResult(){
        return checkResult;
    }
}