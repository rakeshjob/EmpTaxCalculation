package com.employee.taxcalculation.exceptionHandler;

public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(String message) {

    super(message);

  }

}