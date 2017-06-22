package ru.apr.service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.apr.service.Entity.Reference;

@RestController
public class ConsitorwebController {

    @Autowired
    ApplicationContext context;

    @RequestMapping(value={"/"})
    public String index(){
        return context.getBean(Reference.class).getReferenceManual();
    }

    /**
     * Runs single check
     * @param checkName - name of the check, defined in configuration
     * @return JSON response
     */
    @RequestMapping(value={"/check/{name}"})
    public String checkConsistency(@PathVariable("name") String checkName){
        return "";
    }

    /**
     * Runs full pack of checks
     * @return JSON response
     */
    @RequestMapping(value={"/check"})
    public String checkFullConsistency(){
        return "";
    }

    /**
     * Checks for current replication state for all defined replications
     * @return JSON response
     */
    @RequestMapping(value={"/state"})
    public String checkReplicationState(){
        return "";
    }
}
