package com.boaglio.dbaas.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table("dbaas_catalog")
@NoArgsConstructor
public class ServiceSQL {

    @Id
    private Long          id;
    private String        service;
    private String        method;
    private String        version;
    @Column("full_sql")
    private String        sql;
    private LocalDateTime created;
    private LocalDateTime updated;

    public ServiceSQL(String service, String method, String version, String sql) {
        this.service = service;
        this.method = method;
        this.version = version;
        this.sql = sql;
        this.created = this.updated = LocalDateTime.now();
    }

}
