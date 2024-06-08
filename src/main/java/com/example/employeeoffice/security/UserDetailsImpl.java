package com.example.employeeoffice.security;

//import com.example.employeeoffice.entity.PersonalInfo;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.stream.Collectors;
//
//public class UserDetailsImpl implements UserDetails {
//    PersonalInfo personalInfo;
//    public UserDetailsImpl (PersonalInfo personalInfo) {
//        this.personalInfo = personalInfo;
//    }
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return personalInfo.getRoles().stream()
//                .flatMap(role -> Arrays.stream(role.getRoleName().name().split(",")))
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//    }
//
//
//    @Override
//    public String getPassword() {
//        return personalInfo.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return personalInfo.getUsername();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
