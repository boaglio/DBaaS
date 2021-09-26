package com.boaglio.dbaas.dto;

import java.util.List;

import lombok.Data;

@Data
public class SQLRequest {

    private String       service;
    private String       method;
    private String       version;
    private List<Object> params;

}
