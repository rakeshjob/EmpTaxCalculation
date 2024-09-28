package com.employee.taxcalculation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.employee.taxcalculation.entity.Employee;
import com.employee.taxcalculation.entity.TaxDetails;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateEmployee() {
        List<String> phonenumbeStrings = List.of("8412842831");
        Employee employee = new Employee("E123", "abc", "def", "abc@gmail.com", phonenumbeStrings,
                LocalDate.now(),
                10000.5d);
        ResponseEntity<Employee> response = restTemplate.postForEntity("/api/employees", employee, Employee.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void testGetTaxDeductions() {
        ResponseEntity<TaxDetails> response = restTemplate.getForEntity("/api/employees/E123/tax-deductions",
                TaxDetails.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}