package com.boaglio.dbaas.rest;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boaglio.dbaas.dto.SQLRequest;
import com.boaglio.dbaas.service.ExecuteQueryService;
import com.google.gson.Gson;


@RestController
public class QueryService {

    private static final String PARAMETER_EXCEPTION = "exception";
    private static final Logger log = LoggerFactory.getLogger(CatalogService.class);

    @Autowired
    private ExecuteQueryService queryService;

    @PostMapping("/api/q")
    public String query(@RequestBody SQLRequest sqlrequest) {

        log.info("SQL request=" + sqlrequest);
        var jsonService = "";

        var serviceReturn = new HashMap<String, Object> ();
        try {
            try {
                jsonService = queryService.execute(sqlrequest.service(), sqlrequest.method(),
                        sqlrequest.version(), sqlrequest.params());
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
