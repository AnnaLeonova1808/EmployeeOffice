package com.example.employeeoffice.security;

import com.example.employeeoffice.entity.PersonalInfo;
import com.example.employeeoffice.entity.Role;
import com.example.employeeoffice.exception.ErrorMessage;
import com.example.employeeoffice.exception.PersonalInfoNotExistException;
import com.example.employeeoffice.repository.PersonalInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PersonalInfoRepository personalInfoRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<PersonalInfo> personalInfo = personalInfoRepository.findByUsername(username);
        if (personalInfo.isEmpty()) {
            throw new UsernameNotFoundException("User with login '" + username + "' not found");
        }

        return User.withUsername(username)
                .username(personalInfo.get().getUsername())
                .password(personalInfo.get().getPassword())
                .authorities(getAuthorities(personalInfo.get().getRoles()))
                .build();
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName().name()));
            role.getAuthorities().forEach(authority ->
                    authorities.add(new SimpleGrantedAuthority(authority.getAuthority().name())));
        }
        return authorities;
    }
}
