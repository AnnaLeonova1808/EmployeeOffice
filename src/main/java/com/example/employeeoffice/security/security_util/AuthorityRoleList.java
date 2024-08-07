package com.example.employeeoffice.security.security_util;

public class AuthorityRoleList {

    public static final String USER = "USER";

    public static final String ADMIN = "ADMIN";
    public static final String MANAGER = "MANAGER";
    public static final String GUEST = "GUEST";

    public static final String[] USER_LIST = {

            "/v2/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui/",
            "/webjars/**",
            "/favicon.ico",
            "/swagger-ui/**",
            "/address/**"
    };

    public static final String[] ADMIN_LIST = {

            "/v2/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui/",
            "/webjars/**",
            "/configuration/**",
            "/public",
            "/favicon.ico",
            "/swagger-ui/**",
            "/department/**",
            "/employee/**",
            "/event/**",
            "/personal_info/**",
            "/role/**",
            "/authority/**",
            "/vacancy/**",
            "/work_schedules/**"
    };

    public static final String[] MANAGER_LIST = {

            "/v2/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui/",
            "/webjars/**",
            "/configuration/**",
            "/favicon.ico",
            "/swagger-ui/**",
            "/address/**"
    };

    public static final String[] GUEST_LIST = {

            "/v2/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui/",
            "/webjars/**",
            "/configuration/**",
            "/public",
            "/favicon.ico",
            "/swagger-ui/**"
    };
}
