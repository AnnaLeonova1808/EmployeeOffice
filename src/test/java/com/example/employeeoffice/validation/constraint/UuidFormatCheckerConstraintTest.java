package com.example.employeeoffice.validation.constraint;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UuidFormatCheckerConstraintTest {

    private final UuidFormatCheckerConstraint uuidFormatCheckerConstraint = new UuidFormatCheckerConstraint();

    @ParameterizedTest
    @MethodSource("getUUID")
    void testUUIDValidation(String UUID, boolean expected) {

        assertEquals(expected, uuidFormatCheckerConstraint.isValid(UUID, null));
    }
    private static Stream<Arguments> getUUID(){

        return Stream.of(
                Arguments.of("cd8edecd-0d27-4228-8fe6-911c1cf7fd7c",true),
                Arguments.of("55035fe9-37e3-466f-ba4a-197f23fc5700",true),
                Arguments.of("",false),
                Arguments.of("555a555-9a9a-444444ssss44-s888-999d", false),
                Arguments.of("qweasdf-z99x-6rty-fgh3-lkjhgfdsa",false)
        );
    }
}