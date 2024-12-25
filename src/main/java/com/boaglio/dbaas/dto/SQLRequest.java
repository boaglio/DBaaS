package com.boaglio.dbaas.dto;

import java.util.List;

public record SQLRequest(
    String       service,
    String       method,
    String       version,
    List<Object> params
){}
