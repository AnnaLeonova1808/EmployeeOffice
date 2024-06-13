package com.example.employeeoffice.validation.constraint;

import com.example.employeeoffice.validation.annotation.EmailChecker;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class EmailConstraint implements ConstraintValidator<EmailChecker, String> {
    private static final String EMAIL = "^[a-zA-Z0-9._%+-]{1,28}@[a-zA-Z0-9.-]{2,24}\\.[a-zA-Z]{2,6}$";

    @Override
    public void initialize(EmailChecker constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        return Optional.ofNullable(value)
                .filter(el -> !el.isBlank())
                .map(el -> el.matches(EMAIL))
                .orElse(false);
    }
}
