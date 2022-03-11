package com.example.notesservice.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StatusValidator implements ConstraintValidator<IsValidStatus, String> {
    private String listOfValidStatus;

    @Override
    public void initialize(IsValidStatus isValidStatus) {
        this.listOfValidStatus=isValidStatus.listOfValidStatus();
    }

    @Override
    public boolean isValid(String status, ConstraintValidatorContext constraintValidatorContext) {
        if(status == null){
            return false;
        }
        if(status.matches(listOfValidStatus)){
            return true;
        }else {
            return false;
        }
    }
}