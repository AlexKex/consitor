package ru.apr.service.Monitor;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.PropertyAccessException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
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
        try{
            Collection<File> settingsFiles = FileUtils.listFiles(
                    new File(modulesPath),
                    new String[] {"xml"},
                    true
            );

            for (File file : settingsFiles) {
                ApplicationContext moduleContext = new FileSystemXmlApplicationContext(file.getAbsolutePath());
                CheckerInterface checker = moduleContext.getBean(CheckerInterface.class);
            }
        }
        catch (PropertyAccessException e){
            System.err.println("Incorrect data for one of properties in settings file");
            System.err.println(e.getMessage());
            System.err.println("This module won't start");
        }
        catch (IllegalArgumentException e){
            System.err.println("Incorrect modules configuration directory " + modulesPath);
            System.err.println(e.getMessage());
            System.err.println("No module will start");
        }
    }

    private void runCheckers(){

    }
}
