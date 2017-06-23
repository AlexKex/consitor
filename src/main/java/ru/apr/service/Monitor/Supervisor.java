package ru.apr.service.Monitor;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.PropertyAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.apr.service.ConsitorApplication;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;

@Component
public class Supervisor {

    @Autowired
    private ApplicationContext context;

    @Value(value="${modules.path}")
    protected String modulesPath;

    private HashMap<String, CheckerInterface> checkers = new HashMap<String, CheckerInterface>();

    public void init(){
        collectCheckers();
        runCheckers();
    }

    private void collectCheckers(){
        Collection<File> settingsFiles = null;

        try {
            settingsFiles = FileUtils.listFiles(
                    new File(modulesPath),
                    new String[]{"xml"},
                    true
            );
        }
        catch (IllegalArgumentException e){
            ConsitorApplication.logger.error("Incorrect modules configuration directory " + modulesPath);
            ConsitorApplication.logger.error(e.getMessage());
            ConsitorApplication.logger.error("No module will start");
        }

        try{
            if(settingsFiles != null) {
                for (File file : settingsFiles) {
                    ApplicationContext moduleContext = new FileSystemXmlApplicationContext(file.getAbsolutePath());
                    CheckerInterface checker = moduleContext.getBean(CheckerInterface.class);
                    String checkerName = file.getName().substring(0, file.getName().length()-4);
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

    private void runCheckers(){

    }
}
