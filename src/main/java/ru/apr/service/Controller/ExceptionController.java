package ru.apr.service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ExceptionController implements ErrorController {
    private static final String PATH = "/error";

    @Autowired
    private ApplicationContext context;

    @RequestMapping(value={"/error"})
    public String error(HttpServletRequest request) {
        return "";
    }

    public String getErrorPath() {
        return "/error";
    }
}
