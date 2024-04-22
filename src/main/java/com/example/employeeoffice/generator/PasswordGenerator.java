package com.example.employeeoffice.generator;

import java.util.UUID;

public class PasswordGenerator {
    public static String generatePasswordBasedOnUUID(){
        return UUID.randomUUID().toString().substring(0,30);
    }
}
