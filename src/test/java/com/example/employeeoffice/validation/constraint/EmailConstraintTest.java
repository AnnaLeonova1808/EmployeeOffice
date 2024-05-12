package com.example.employeeoffice.validation.constraint;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class EmailConstraintTest {
    private final EmailConstraint emailConstraint = new EmailConstraint();

    @ParameterizedTest
    @MethodSource("getEmail")
    void testEmailValidation(String email, boolean expected) {

        Assertions.assertEquals(expected, emailConstraint.isValid(email, null));
    }
    private static Stream<Arguments> getEmail() {
        return Stream.of(
                Arguments.of("olivia@example.com", true),
                Arguments.of("olivia123@example.com", true),
                Arguments.of("olivia.martinez123@example.com", true),
                Arguments.of("olidsjwdfhjiwefhujowefhuowefhiowefn.olivia@example", false),
                Arguments.of("olivia_Martinez1234@example.", false),
                Arguments.of("olivia_martinez1234example.com", false),
                Arguments.of("", false)
        );
    }

}