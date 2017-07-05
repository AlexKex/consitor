package ru.apr.service.Entity;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.apr.service.ConsitorApplication;

import java.io.File;
import java.util.Collection;

@Component
public class BeanFileLoader {

    @Autowired
    ApplicationContext context;

    public Collection<File> getBeanFiles(String path){
        Collection<File> settingsFiles = null;

        try {
            settingsFiles = FileUtils.listFiles(
                    new File(path),
                    new String[]{"xml"},
                    true
            );
        }
        catch (IllegalArgumentException e){
            ConsitorApplication.logger.error("Incorrect modules configuration directory " + path);
            ConsitorApplication.logger.error(e.getMessage());
            ConsitorApplication.logger.error("No module will start");
        }

        return settingsFiles;
    }
}
