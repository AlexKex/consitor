package ru.apr.service.Monitor.Checker;

import org.springframework.stereotype.Component;
import ru.apr.service.Monitor.CheckerInterface;

@Component
public class DataChecker extends AbstractChecker implements CheckerInterface{

    @Override
    public void check() {

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
