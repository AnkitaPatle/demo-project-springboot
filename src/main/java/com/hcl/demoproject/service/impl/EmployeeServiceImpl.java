package com.hcl.demoproject.service.impl;

import com.hcl.demoproject.entiry.Employee;
import com.hcl.demoproject.repository.EmployeeRepository;
import com.hcl.demoproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public String saveEmployee(Employee employee) {
        employeeRepository.save(employee);
        return "Employee Save Successfully";
    }
}
