package ru.apr.service.Entity;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class Reference {

    public String getReferenceManual(){
        JSONObject response = new JSONObject();

        response.put("info", "reference");

        JSONObject functions = new JSONObject();

        functions.put("/", "Reference manual");
        functions.put("/check/{name}", "Start check by check name");
        functions.put("/check", "Start full check");
        functions.put("/state", "Replication state check");

        response.put("functions", functions);

        return response.toString();
    }
}
