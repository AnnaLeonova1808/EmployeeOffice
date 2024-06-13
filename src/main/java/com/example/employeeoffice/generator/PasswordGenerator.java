package com.example.employeeoffice.generator;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

/**
 * Utility class for generating passwords.
 */
public class PasswordGenerator {

    /**
     * Generates a password based on a randomly generated UUID.
     *
     * @return a 30-character password derived from a UUID
     */
    @Schema(description = "Generates a password based on a randomly generated UUID")

    public static String generatePasswordBasedOnUUID(){

        return UUID.randomUUID().toString().substring(0,30);
    }
}
