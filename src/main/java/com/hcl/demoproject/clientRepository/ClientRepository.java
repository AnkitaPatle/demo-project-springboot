package com.hcl.demoproject.clientRepository;

import com.hcl.demoproject.clientEntity.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {

    @Query(value = "SELECT * FROM client WHERE LOWER(company_name) = LOWER(:companyName)", nativeQuery = true)
    Client getClientByCompanyName(@Param("companyName") String companyName);

    @Query(value = "SHOW DATABASES", nativeQuery = true)
    List<String> getAllDatabaseName();
}
