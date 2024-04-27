package com.example.employeeoffice.mapper.util;

import com.github.javafaker.Faker;

public class UserDataGeneratorUtil {
    private static final Faker FAKER = new Faker();
    public static String genAddress() {
        return FAKER.address().fullAddress();
    }

    public static String genPhone() {
        return FAKER.phoneNumber().phoneNumber();
    }
}
