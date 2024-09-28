package com.employee.taxcalculation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.taxcalculation.entity.Employee;
import com.employee.taxcalculation.entity.TaxDetails;
import com.employee.taxcalculation.exceptionHandler.ResourceNotFoundException;
import com.employee.taxcalculation.repository.EmployeeRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public TaxDetails getTaxDetails(String employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        LocalDate currentDate = LocalDate.now();
        LocalDate startOfYear = LocalDate.of(currentDate.getYear(), 4, 1);
        long monthsWorked = ChronoUnit.MONTHS.between(employee.getDoj(), currentDate) + 1;
        double yearlySalary = employee.getSalary() * monthsWorked;
        double taxAmount = calculateTax(yearlySalary);
        double cessAmount = yearlySalary > 2500000 ? (yearlySalary - 2500000) * 0.02 : 0;
        return new TaxDetails(employeeId, employee.getFirstName(), employee.getLastName(), yearlySalary, taxAmount,
                cessAmount);
    }

    private double calculateTax(double yearlySalary) {
        double tax = 0;
        if (yearlySalary > 1000000) {
            tax += (yearlySalary - 1000000) * 0.20;
            yearlySalary = 1000000;
        }
        if (yearlySalary > 500000) {
            tax += (yearlySalary - 500000) * 0.10;
            yearlySalary = 500000;
        }
        if (yearlySalary > 250000) {
            tax += (yearlySalary - 250000) * 0.05;
        }
        return tax;
    }
}