
package com.employee.taxcalculation.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import com.employee.taxcalculation.entity.Employee;
import com.employee.taxcalculation.entity.TaxDetails;
import com.employee.taxcalculation.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/{employeeId}/tax-deductions")
    public ResponseEntity<TaxDetails> getTaxDeductions(@PathVariable String employeeId) {
        TaxDetails taxDetails = employeeService.getTaxDetails(employeeId);
        return new ResponseEntity<>(taxDetails, HttpStatus.OK);
    }
}