package ru.apr.service.Monitor.Checker;

import ru.apr.service.Monitor.CheckerInterface;

import java.util.HashMap;

/**
 * Created by Alex Pryakhin on 23.06.2017.
 */
public class SchemaChecker extends AbstractChecker implements CheckerInterface{
    @Override
    public void check() {

    }

    @Override
    public void init(String checkerName) {
        super.init(checkerName);
    }

    @Override
    public Object getCheckFactor() {
        return null;
    }

    @Override
    public void setCheckFactor(Object checkFactor) {

    }

    @Override
    public void run() {

    }
}
