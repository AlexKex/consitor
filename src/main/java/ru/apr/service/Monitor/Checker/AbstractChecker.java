package ru.apr.service.Monitor.Checker;

import ru.apr.service.ConsitorApplication;
import ru.apr.service.Entity.DataLoader;
import ru.apr.service.Monitor.CheckerInterface;
import ru.apr.service.Monitor.Supervisor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

abstract public class AbstractChecker implements CheckerInterface {
    protected Boolean checkIsRunning = false;
    protected String checkId;
    protected String checkerName;

    protected String checkFactor;
    protected Long checkInterval;
    protected HashMap<String, String> checkResult = new HashMap<>();

    protected DataLoader master;
    protected ArrayList<DataLoader> slaves;

    abstract public Object getCheckFactor();
    abstract public void setCheckFactor(Object checkFactor);

    public void init(String checkerName){
        this.checkerName = checkerName;

        master = Supervisor.getMaster();
        slaves = Supervisor.getSlaves();
    }

    public void setCheckInterval(String interval) {
        this.checkInterval = Long.parseLong(interval);
    }

    public Long getCheckInterval() {
        return checkInterval;
    }

    public HashMap<String, String> getCheckResult(){
        return checkResult;
    }

    public void startCheck(){
        checkIsRunning = true;
    }

    public void stopCheck(){
        checkIsRunning = false;
    }

    public Boolean isCheckerRunning(){
        return checkIsRunning;
    }

    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }

    private void applyCheckId(){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()).getBytes());
            byte[] digest = messageDigest.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(Integer.toHexString(b & 0xff));
            }

            this.checkId = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            ConsitorApplication.logger.error("Can't create MD5 check id");
        }
    }

    public void run(){

    }
}