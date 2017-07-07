package ru.apr.service.Monitor;

import org.springframework.beans.PropertyAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import ru.apr.service.ConsitorApplication;
import ru.apr.service.Entity.BeanFileLoader;
import ru.apr.service.Entity.DataLoader;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class Supervisor {

    @Autowired
    private ApplicationContext context;

    @Value(value="${checker.bean.path}")
    protected String modulesPath;
    @Value(value="${dataloader.bean.path}")
    protected String dbSettingsPath;
    @Value(value="${dataloader.bean.masterfilename}")
    protected String dbMasterFileName;

    private HashMap<String, CheckerInterface> checkers = new HashMap<>();
    private static ArrayList<DataLoader> slaves = new ArrayList<>();
    private static DataLoader master;

    public void init(){
        discoverLandscape();
        collectCheckers();
        runCheckers();
    }

    public static DataLoader getMaster(){
        return master;
    }

    public static ArrayList<DataLoader> getSlaves(){
        return slaves;
    }

    private void discoverLandscape(){
        Collection<File> settingsFiles = context
                .getBean(BeanFileLoader.class)
                .getBeanFiles(dbSettingsPath);

        try{
            if(settingsFiles != null) {
                for (File file : settingsFiles) {
                    ApplicationContext moduleContext = new FileSystemXmlApplicationContext(file.getAbsolutePath());
                    DataLoader loader = moduleContext.getBean(DataLoader.class);

                    if(loader.isMaster()){
                        master = loader;
                        ConsitorApplication.logger.info(file.getName() + " has been added as master");
                    }
                    else{
                        slaves.add(loader);
                        ConsitorApplication.logger.info(file.getName() + " has been added as slave");
                    }
                }
            }
            else {
                ConsitorApplication.logger.error("No modules will run due to lack of XML configuration files");
            }
        }
        catch (PropertyAccessException e){
            ConsitorApplication.logger.error("Incorrect data for one of properties in settings file");
            ConsitorApplication.logger.error(e.getMessage());
            ConsitorApplication.logger.error("DB won't be included");
        }
    }

    private void collectCheckers(){
        Collection<File> settingsFiles = context
                .getBean(BeanFileLoader.class)
                .getBeanFiles(modulesPath);

        try{
            if(settingsFiles != null) {
                for (File file : settingsFiles) {
                    ApplicationContext moduleContext = new FileSystemXmlApplicationContext(file.getAbsolutePath());
                    CheckerInterface checker = moduleContext.getBean(CheckerInterface.class);
                    String checkerName = file.getName().substring(0, file.getName().length()-4);
                    checker.init(checkerName);
                    checkers.put(checkerName, checker);
                    ConsitorApplication.logger.info(checkerName + " has been added to monitoring list");
                }
            }
            else {
                ConsitorApplication.logger.error("No modules will run due to lack of XML configuration files");
            }
        }
        catch (PropertyAccessException e){
            ConsitorApplication.logger.error("Incorrect data for one of properties in settings file");
            ConsitorApplication.logger.error(e.getMessage());
            ConsitorApplication.logger.error("Module won't start");
        }

    }

    private void runCheckers() {
        ExecutorService checkersThreadPool = Executors.newFixedThreadPool(checkers.size());

        for(Map.Entry<String, CheckerInterface> checkerEntry : checkers.entrySet()){
            checkersThreadPool.submit(checkerEntry.getValue());
        }
    }
}
