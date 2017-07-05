package ru.apr.service.Monitor.Checker;

import org.springframework.stereotype.Component;
import ru.apr.service.Entity.DataLoader;
import ru.apr.service.Monitor.CheckerInterface;
import ru.apr.service.Monitor.Supervisor;

import javax.xml.crypto.Data;
import java.util.ArrayList;

@Component
public class DataChecker extends AbstractChecker implements CheckerInterface{

    @Override
    public void check(){

    }

    @Override
    public void init() {
        DataLoader master = Supervisor.getMaster();
        ArrayList<DataLoader> slaves = Supervisor.getSlaves();
    }

    @Override
    public Object getCheckFactor() {
        return checkFactor;
    }

    @Override
    public void setCheckFactor(Object checkFactor) {
        this.checkFactor = checkFactor.toString();
    }
}
