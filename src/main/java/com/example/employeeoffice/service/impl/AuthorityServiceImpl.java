package com.example.employeeoffice.service.impl;

import com.example.employeeoffice.entity.Authority;
import com.example.employeeoffice.entity.enums.AuthorityName;
import com.example.employeeoffice.exception.ErrorMessage;
import com.example.employeeoffice.exception.ListOfAuthorityIsEmptyException;
import com.example.employeeoffice.repository.AuthorityRepository;
import com.example.employeeoffice.service.interfaces.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    /**
     * Retrieves all authorities.
     *
     * @return a set of authority names
     * @throws ListOfAuthorityIsEmptyException if no authorities are found
     */

    @Override
    public Set<String> showAllAuthority(){
        List<Authority> authorityList = authorityRepository.findAll();
        if (authorityList.isEmpty()) throw new ListOfAuthorityIsEmptyException(ErrorMessage.LIST_OF_AUTHORITY_IS_EMPTY);
        return authorityList.stream()
                .map(Authority::getAuthority)
                .map(AuthorityName::name)
                .collect(Collectors.toSet());
    }
}
