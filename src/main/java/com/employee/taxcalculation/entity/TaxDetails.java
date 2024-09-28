package com.employee.taxcalculation.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class TaxDetails {

    private String employeeId;

    private String firstName;

    private String lastName;

    private double yearlySalary;

    private double taxAmount;

    private double cessAmount;



    public TaxDetails(String employeeId, String firstName, String lastName, double yearlySalary, double taxAmount, double cessAmount) {

        this.employeeId = employeeId;

        this.firstName = firstName;

        this.lastName = lastName;

        this.yearlySalary = yearlySalary;

        this.taxAmount = taxAmount;

        this.cessAmount = cessAmount;

    }



    

}
