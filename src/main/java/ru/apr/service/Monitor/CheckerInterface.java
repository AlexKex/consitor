package ru.apr.service.Monitor;

import java.util.HashMap;

/**
 * Created by Alex Pryakhin on 22.06.2017.
 */
public interface CheckerInterface {
    void check();
    HashMap<String, String> getCheckResult();
}
