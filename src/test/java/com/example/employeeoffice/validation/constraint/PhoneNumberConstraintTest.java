package com.example.employeeoffice.validation.constraint;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class PhoneNumberConstraintTest {
    private final PhoneNumberConstraint phoneNumberConstraint = new PhoneNumberConstraint();

    @ParameterizedTest
    @MethodSource("getPhoneNumber")
    void testPhoneNumberValidation(String phoneNumber, boolean expected) {

        Assertions.assertEquals(expected, phoneNumberConstraint.isValid(
                phoneNumber, null));
    }
    private static Stream<Arguments> getPhoneNumber() {
        return Stream.of(
                Arguments.of("+38-050-111-22-33", true),
                Arguments.of("+49-555-111-22-33", true),
                Arguments.of("+37-150-111-22-33", true),
                Arguments.of("+1-22-3333-55-1", false),
                Arguments.of("++22-619872", false),
                Arguments.of("-357-951-85-25", false),
                Arguments.of("", false),
                Arguments.of(null, false)
        );
    }

}