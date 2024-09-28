package com.employee.taxcalculation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.taxcalculation.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}