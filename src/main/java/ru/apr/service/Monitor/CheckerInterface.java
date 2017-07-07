package ru.apr.service.Monitor;

public interface CheckerInterface extends Runnable{
    void check();
    void init(String checkerName);
    void setCheckId(String checkId);
}
