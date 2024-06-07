package com.example.employeeoffice;

import com.example.employeeoffice.configuration.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class EmployeeOfficeApplication {

    public static void main(String[] args) {

        SpringApplication.run(EmployeeOfficeApplication.class, args);

    }

}
