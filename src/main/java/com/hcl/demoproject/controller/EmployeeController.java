package com.hcl.demoproject.controller;

import com.hcl.demoproject.entiry.Employee;
import com.hcl.demoproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/saveEmployee")
    public void add(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
    }
}
