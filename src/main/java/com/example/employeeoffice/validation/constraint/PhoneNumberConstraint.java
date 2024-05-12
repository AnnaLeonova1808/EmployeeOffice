package com.example.employeeoffice.validation.constraint;

import com.example.employeeoffice.validation.annotation.EmailChecker;
import com.example.employeeoffice.validation.annotation.PhoneNumberChecker;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class PhoneNumberConstraint implements ConstraintValidator<PhoneNumberChecker, String> {

    private static final String PHONE_NUMBER = "^\\+\\d{2}-\\d{3}-\\d{3}-\\d{2}-\\d{2}$";

    @Override
    public void initialize(PhoneNumberChecker constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return Optional.ofNullable(s)
                .filter(el -> !el.isBlank())
                .map(el -> el.matches(PHONE_NUMBER))
                .orElse(false);
    }

}
