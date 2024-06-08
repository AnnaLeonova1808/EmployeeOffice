package com.example.employeeoffice.service.impl;

import com.example.employeeoffice.entity.Authority;
import com.example.employeeoffice.exception.ErrorMessage;
import com.example.employeeoffice.exception.IdNotFoundException;
//import com.example.employeeoffice.repository.AuthorityRepository;
//import com.example.employeeoffice.service.interfaces.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Implementation of the AuthorityService interface.
 *
 * Реализация интерфейса AuthorityService.
 */
//@Service
//@RequiredArgsConstructor
//public class AuthorityServiceImpl implements AuthorityService {
//
//    private final AuthorityRepository authorityRepository;
//
//    /**
//     * Retrieves an Authority entity by its ID.
//     *
//     * Получает сущность Authority по ее идентификатору.
//     *
//     * @param authId The ID of the Authority entity.
//     * @return The Authority entity.
//     * @throws IdNotFoundException if no Authority entity with the given ID is found.
//     */
//    @Override
//    @Transactional(isolation = Isolation.READ_COMMITTED)
//    public Authority getAuthorityById(UUID authId) {
//        Authority authority = authorityRepository.getAuthorityByAuthId(authId);
//
//        if (authority != null) {
//            return authority;
//        } else {
//            throw new IdNotFoundException(ErrorMessage.ID_NOT_FOUND);
//        }
//    }
//}

