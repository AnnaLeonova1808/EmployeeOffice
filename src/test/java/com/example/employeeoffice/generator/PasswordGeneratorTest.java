package com.example.employeeoffice.generator;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;


class PasswordGeneratorTest {

    @Test
    void testGeneratePasswordBasedOnUUID() throws Exception{

        String generatedPassword = PasswordGenerator.generatePasswordBasedOnUUID();

        assertNotNull(generatedPassword);

        assertEquals(30, generatedPassword.length());
    }

}