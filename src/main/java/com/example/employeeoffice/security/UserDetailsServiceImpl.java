package com.example.employeeoffice.security;

import com.example.employeeoffice.entity.PersonalInfo;
import com.example.employeeoffice.entity.Role;
import com.example.employeeoffice.repository.PersonalInfoRepository;
import com.example.employeeoffice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PersonalInfoRepository personalInfoRepository;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<PersonalInfo> personalInfo = personalInfoRepository.findByUsername(username);
        if (personalInfo.isEmpty()) {
            throw new UsernameNotFoundException("User with login '" + username + "' not found");
        }

//        PersonalInfo info = personalInfo.get();
//        initializeRoles(info.getRoles());

        return User.withUsername(username)
                .username(personalInfo.get().getUsername())
                .password(personalInfo.get().getPassword())
                .authorities(getAuthorities(personalInfo.get().getRoles()))
                .build();
    }

//    private void initializeRoles(Collection<Role> roles) {
//        for (Role role : roles) {
//            roleRepository.findByRoleName(role.getRoleName()).ifPresent(fetchedRole -> {
//                role.setAuthorities(fetchedRole.getAuthorities());
//            });
//        }
    //}

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName().name()));
            role.getAuthorities().forEach(authority ->
                    authorities.add(new SimpleGrantedAuthority(authority.getAuthority().name())));
        }
        return authorities;
    }
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Arrays.stream(personalInfo.getRoleName().split(","))
//                .map(SimpleGrantedAuthority::new)
//                .toList();
//    }
}
