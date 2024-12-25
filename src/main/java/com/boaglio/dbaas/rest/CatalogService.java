package com.boaglio.dbaas.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.boaglio.dbaas.domain.ServiceSQL;
import com.boaglio.dbaas.repo.ServiceSQLRepository;


@RestController
@RequestMapping("/api/catalog")
public class CatalogService {

    private static final Logger log = LoggerFactory.getLogger(CatalogService.class);

    @Autowired
    ServiceSQLRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/all")
    @ResponseBody
    public List<ServiceSQL> getAllSQL() {
        return (ArrayList<ServiceSQL>) repository.findAll();
    }

    @GetMapping("/q/{query}")
    @ResponseBody
    public List<ServiceSQL> getByQuery(@PathVariable("query") String query) {
        return (ArrayList<ServiceSQL>) repository.findByServiceContainsOrMethodContainsOrVersionContains(query, query,
                query);
    }

    @PostMapping("/sql")
    public String newSQL(@RequestBody ServiceSQL servicesql) {

        ServiceSQL stored = repository.save(servicesql);

        if (stored.id() != null)
            return "Ok";
        else
            return "Error";
    }

    @PutMapping("/sql")
    public String updateSQL(@RequestBody ServiceSQL servicesql) {

        if (servicesql.id() != null) {
            Optional<ServiceSQL> storedOpt = repository.findById(servicesql.id());
            if (storedOpt.isPresent()) {
                repository.save(servicesql);
                return "Ok";
            }
        }
        return "Invalid ID";
    }

    @DeleteMapping("/sql/{id}")
    public String deleteSQL(@PathVariable("id") Long id) {

        if (id != null) {
            Optional<ServiceSQL> storedOpt = repository.findById(id);
            if (storedOpt.isPresent()) {
                repository.deleteById(id);
                return "Ok";
            }
        }
        return "Invalid ID";
    }

    @GetMapping("/setup/mysql")
    @ResponseBody
    public String setup() {
        log.info("Creating MySQL tables");

        String sqlCreateTable = "create table dbaas_catalog (id integer not null auto_increment primary key,service varchar(100),method varchar(100),version varchar(30),full_sql varchar(16000),created timestamp default now(), updated timestamp default now() on update now())";
        String sqlInsert1 = "INSERT INTO dbaas_catalog(service,method,version,full_sql,created,updated) VALUES ('sample','catalog','v1','select VERSION()',now(),now())";
        String sqlInsert2 = "INSERT INTO dbaas_catalog(service,method,version,full_sql,created,updated) VALUES ('sample','schemas','v1','select SCHEMA_NAME FROM information_schema.schemata order by 1',now(),now())";
        String sqlInsert3 = "INSERT INTO dbaas_catalog(service,method,version,full_sql,created,updated) VALUES ('sample','tables','v1','select TABLE_SCHEMA ,TABLE_NAME, engine  FROM information_schema.TABLES order by 1,2',now(),now())";
        String sqlInsert4 = "INSERT INTO dbaas_catalog(service,method,version,full_sql,created,updated) VALUES ('sample','tables-by-schema','v1','select TABLE_SCHEMA ,TABLE_NAME, engine FROM information_schema.TABLES WHERE TABLE_SCHEMA=? order by 1,2',now(),now())";

        jdbcTemplate.execute(sqlCreateTable);
        jdbcTemplate.execute(sqlInsert1);
        jdbcTemplate.execute(sqlInsert2);
        jdbcTemplate.execute(sqlInsert3);
        jdbcTemplate.execute(sqlInsert4);

        return "Ok";
    }

}
