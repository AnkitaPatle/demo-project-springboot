package com.hcl.demoproject.service.impl;

import com.hcl.demoproject.clientEntity.Client;
import com.hcl.demoproject.clientRepository.ClientRepository;
import com.hcl.demoproject.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public String saveClient(Client client) {
        clientRepository.save(client);
        return "Save Client Successfully";
    }

    @Override
    public Client getClientByCompanyName(String companyName) throws IOException {
        Client client = clientRepository.getClientByCompanyName(companyName);
        List<String> db = clientRepository.getAllDatabaseName();
        System.out.println("db ---::::::::::"+db);
        if (db.contains(client.getCompanyName())) {
            System.out.println("db call::::::::::");
//                    System.setProperty("app.name",client.getCompanyName());
//                    CloudcallBackendApplication.restart();
            System.setProperty("spring.cloudcall.datasource.jdbc-url", "jdbc:mysql://localhost:3306/" + companyName + "${app.name:cloudcalldb}?allowPublicKeyRetrieval=true&useSSL=false");
            System.setProperty("spring.cloudcall.datasource.username", "root");
            System.setProperty("spring.cloudcall.datasource.password", "");
            System.setProperty("spring.cloudcall.datasource.driver-class-name", "com.mysql.cj.jdbc.Driver");

        }
        return client;
    }
}
