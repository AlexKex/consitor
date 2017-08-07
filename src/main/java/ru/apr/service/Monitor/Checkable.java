package ru.apr.service.Monitor;

public interface Checkable extends Runnable{
    void check();
    void init(String checkerName);
    void setCheckId(String checkId);
    Boolean isRunning();
}
