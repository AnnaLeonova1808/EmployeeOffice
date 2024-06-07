package com.example.employeeoffice.security.security_util_a;

public class AuthorityList {
    public static final String USER = "USER";
    public static final String ADMIN = "ADMIN";
    public static final String MANAGER = "MANAGER";
    public static final String GUEST = "GUEST";

    public static final String[] USER_LIST = {
            "swagger-ui/**",
            "/address/show_address/**"

    };

    public static final String[] ADMIN_LIST = {
            "swagger-ui/**",
            "/address/show_address/**"

    };

    public static final String[] MANAGER_LIST = {
            "swagger-ui/**",
            "/address/show_address/**"

    };

    public static final String[] GUEST_LIST = {
            "swagger-ui/**",
            "/address/show_address/**"

    };
}
