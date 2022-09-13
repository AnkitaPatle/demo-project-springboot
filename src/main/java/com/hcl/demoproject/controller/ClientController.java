package com.hcl.demoproject.controller;

import com.hcl.demoproject.clientEntity.Client;
import com.hcl.demoproject.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/saveClient")
    public void add(@RequestBody Client client) {
        clientService.saveClient(client);
    }


    @GetMapping("/getClientByCompanyName/{companyName}")
    public void getClientByName(@PathVariable(value = "companyName") String companyName) throws IOException {
        clientService.getClientByCompanyName(companyName);
      }
}
