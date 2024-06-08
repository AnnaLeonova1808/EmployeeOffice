package com.example.employeeoffice.controller;

//import com.example.employeeoffice.annotation.GetAuthority;
//import com.example.employeeoffice.entity.Authority;
//import com.example.employeeoffice.exception.IdNotFoundException;
//import com.example.employeeoffice.service.interfaces.AuthorityService;
//import com.example.employeeoffice.validation.annotation.UuidFormatChecker;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.UUID;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/authority")
//public class AuthorityController {
//    private final AuthorityService authorityService;
//
//    /**
//     * Retrieves authority information by its ID.
//     *
//     * Получает информацию об авторизации по ее идентификатору.
//     *
//     * @param authId The ID of the authority to retrieve.
//     *           Идентификатор авторизации для извлечения.
//     * @return The authority object.
//     *         Объект авторизации.
//     * @throws IdNotFoundException if the provided ID does not exist.
//     *                             если предоставленный идентификатор не существует.
//     */
//    @PreAuthorize("hasRole('ROLE_USER')")
//    @GetAuthority(path = "/{authId}")
//    public Authority getAuthorityById(@UuidFormatChecker @PathVariable("authId") String authId) throws IdNotFoundException {
//        return authorityService.getAuthorityById(UUID.fromString(authId));
//    }
//
////    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);
////
////    private final TokenService tokenService;
////
////    public AuthController(TokenService tokenService) {
////        this.tokenService = tokenService;
////    }
////
////    @PostMapping("/token")
////    public String token(Authentication authentication) {
////        LOG.debug("Token requested for user: '{}'", authentication.getName());
////        String token = tokenService.generateToken(authentication);
////        LOG.debug("Token granted: {}", token);
////        return token;
////    }
//
//}
