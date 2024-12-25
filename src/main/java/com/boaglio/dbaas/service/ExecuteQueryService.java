package com.boaglio.dbaas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.boaglio.dbaas.domain.ServiceSQL;
import com.boaglio.dbaas.exception.QueryException;
import com.boaglio.dbaas.repo.ServiceSQLRepository;
import com.google.gson.Gson;

@Service
public class ExecuteQueryService {

    static final String          MSG_QUERY_ERROR     = "Query error";
    static final String          MSG_QUERY_NOT_FOUND = "Query not found";
    @Autowired
    private ServiceSQLRepository serviceSQLRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String execute(final String service, final String method, final String version, final List<Object> params)
            throws Exception {

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        String sql = "";

        try {

            Optional<ServiceSQL> serviceSQL = serviceSQLRepository.findByServiceAndMethodAndVersion(service, method,
                    version);

            if (serviceSQL.isPresent()) {
                sql = serviceSQL.get().sql();
                if (params != null) {
                    result = jdbcTemplate.queryForList(sql, params.toArray());
                } else {
                    result = jdbcTemplate.queryForList(sql);
                }
            }

        } catch (EmptyResultDataAccessException err) {
            QueryException queryException = new QueryException(MSG_QUERY_NOT_FOUND, sql);
            throw queryException;
        } catch (BadSqlGrammarException err) {
            QueryException queryException = new QueryException(MSG_QUERY_ERROR, sql);
            throw queryException;
        } catch (DataAccessException err) {
            QueryException queryException = new QueryException(err.getMessage(), sql);
            throw queryException;
        } catch (Exception err) {
            QueryException queryException = new QueryException(err.getMessage(), sql);
            throw queryException;
        }

        return new Gson().toJson(result);
    }

}
