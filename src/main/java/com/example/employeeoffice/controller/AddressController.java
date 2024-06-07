package com.example.employeeoffice.controller;

import com.example.employeeoffice.annotation.GetAddress;
import com.example.employeeoffice.entity.Address;
import com.example.employeeoffice.service.interfaces.AddressService;
import com.example.employeeoffice.validation.annotation.UuidFormatChecker;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
/**
 * REST controller for managing addresses.
 */
@Validated
@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    /**
     * Retrieves an address by its unique identifier.
     *
     * @param addressId The unique identifier of the address.
     * @return The address with the specified ID.
     */
    @GetAddress(path = "/show_address/{addressId}")
    public Address showAddressById(@PathVariable(name = "addressId") @UuidFormatChecker String addressId, HttpServletRequest request) {
        System.out.println("Entering showAddressById with addressId: " + addressId);
        handleRequest(request);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            String password = ((UserDetails) principal).getPassword();
            System.out.println("username: " + username + " password: " + password);
        }

        return addressService.showAddressById(UUID.fromString(addressId));

    }
    private void handleRequest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies!= null){
            for (Cookie cookie: cookies){
                System.out.println("JSESSIONID: " + cookie.getValue());
            }
        }
    }
}
