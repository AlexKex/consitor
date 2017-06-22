package ru.apr.service.Monitor;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class Supervisor {

    private HashMap<String, CheckerInterface> checkers = new HashMap<String, CheckerInterface>();
}
