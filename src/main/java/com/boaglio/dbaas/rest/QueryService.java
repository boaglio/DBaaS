package com.boaglio.dbaas.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boaglio.dbaas.dto.SQLRequest;
import com.boaglio.dbaas.service.ExecuteQueryService;
import com.google.gson.Gson;

import lombok.extern.java.Log;

@Log
@RestController
public class QueryService {

    private static final String PARAMETER_EXCEPTION = "exception";
    @Autowired
    private ExecuteQueryService queryService;

    @PostMapping("/api/q")
    public String query(@RequestBody SQLRequest sqlrequest) {

        log.info("SQL request=" + sqlrequest);
        String jsonService = "";

        Map<String, Object> serviceReturn = new HashMap<String, Object>();
        try {
            try {
                jsonService = queryService.execute(sqlrequest.getService(), sqlrequest.getMethod(),
                        sqlrequest.getVersion(), sqlrequest.getParams());
            } catch (Exception e) {
                serviceReturn.put(PARAMETER_EXCEPTION, e.getMessage());
                jsonService = new Gson().toJson(serviceReturn);
            }

        } catch (Exception err) {
            serviceReturn.put(PARAMETER_EXCEPTION, "invalid request");
            jsonService = new Gson().toJson(serviceReturn);
        }

        return jsonService;
    }
}
