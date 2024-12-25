package com.boaglio.dbaas.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("dbaas_catalog")
public record ServiceSQL (
    @Id
    Long          id,
    String        service,
    String        method,
    String        version,
    @Column("full_sql")
    String        sql,
    LocalDateTime created,
    LocalDateTime updated
){
}