package ru.apr.service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.apr.service.Entity.Reference;
import ru.apr.service.Monitor.Supervisor;

import javax.annotation.PostConstruct;

@RestController
public class ConsitorwebController {

    Supervisor supervisor;

    @Autowired
    ApplicationContext context;

    @PostConstruct
    private void prepareSuprevisor(){
        supervisor = context.getBean(Supervisor.class);
        supervisor.init();
    }

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
        supervisor.runSingleChecker(checkName);
        // TODO add AMQP
        return "";
    }

    /**
     * Runs full pack of checks
     * @return JSON response
     */
    @RequestMapping(value={"/check"})
    public String checkFullConsistency(){
        supervisor.runAllCheckers();
        // TODO add AMQP
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
