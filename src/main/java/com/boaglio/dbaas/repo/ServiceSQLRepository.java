package com.boaglio.dbaas.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.boaglio.dbaas.domain.ServiceSQL;

public interface ServiceSQLRepository extends CrudRepository<ServiceSQL, Long> {

    Optional<ServiceSQL> findByServiceAndMethodAndVersion(String service, String method, String version);

    List<ServiceSQL> findByServiceContainsOrMethodContainsOrVersionContains(String s, String m, String v);

}
