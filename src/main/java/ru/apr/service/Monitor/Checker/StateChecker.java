package ru.apr.service.Monitor.Checker;

import org.springframework.stereotype.Component;
import ru.apr.service.Monitor.CheckerInterface;

import java.util.HashMap;

@Component
public class StateChecker extends AbstractChecker implements CheckerInterface {
    @Override
    public void check() {

    }

    @Override
    public HashMap<String, String> getCheckResult() {
        return null;
    }

    @Override
    public Object getCheckFactor() {
        return null;
    }

    @Override
    public void setCheckFactor(Object checkFactor) {

    }
}
