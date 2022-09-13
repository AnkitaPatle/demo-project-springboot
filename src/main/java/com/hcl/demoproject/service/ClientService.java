package com.hcl.demoproject.service;

import com.hcl.demoproject.clientEntity.Client;

import java.io.IOException;

public interface ClientService {

    String saveClient(Client client);

    Client getClientByCompanyName(String companyName) throws IOException;
}
