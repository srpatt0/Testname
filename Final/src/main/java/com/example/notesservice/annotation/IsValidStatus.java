package com.example.notesservice.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StatusValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsValidStatus {
    String listOfValidStatus();
    String message() default "Please provide a valid status; accepted status are - competed and pending (choose anyone)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}