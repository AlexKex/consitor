package ru.apr.service.Monitor.Checker;

import org.springframework.stereotype.Component;
import ru.apr.service.ConsitorApplication;
import ru.apr.service.Monitor.CheckerInterface;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DataChecker extends AbstractChecker implements CheckerInterface{

    /**
     * Check factor here is an SQL-request, which runs on master and on each slave
     * and check the consistency
     */
    @Override
    public void check(){
        
    }

    @Override
    public void init(String checkerName) {
        super.init(checkerName);
    }

    @Override
    public Object getCheckFactor() {
        return checkFactor;
    }

    @Override
    public void setCheckFactor(Object checkFactor) {
        this.checkFactor = checkFactor.toString();
    }


    @Override
    public void run() {
        super.run();

        proceedOnMaster();
        proceedOnSlaves();
    }

    private void proceedOnMaster() {
        master.setSqlRequest(this.checkFactor);
        Thread masterThread = new Thread(master);
        masterThread.run();

        while (masterThread.isAlive()){}

        ResultSet masterResult = master.getResult();
        int count = 0;
        try {
            masterResult.last();
            count = masterResult.getRow();

            ConsitorApplication.logger.info(count + " rows were found");
        } catch (SQLException e) {
            ConsitorApplication.logger.error(e.getMessage());
        }

        master.complete();
    }

    private void proceedOnSlaves() {

    }
}
