package com.example.employeeoffice.controller;

import com.example.employeeoffice.annotation.ShowAllAuthority;
import com.example.employeeoffice.annotation.ShowAllEvent;
import com.example.employeeoffice.service.interfaces.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/authorities")
@RequiredArgsConstructor
public class AuthorityController {

    private final AuthorityService authorityService;

    /**
     * Retrieves all authorities.
     *
     * @return a list of all authorities
     */
    @ShowAllAuthority(path = "/showAllAuthority")
    public Set<String> showAllAuthority() {

        return authorityService.showAllAuthority();
    }

}
