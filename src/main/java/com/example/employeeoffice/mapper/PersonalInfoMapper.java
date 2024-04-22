package com.example.employeeoffice.mapper;

import com.example.employeeoffice.dto.PersonalInfoAfterCreationDto;
import com.example.employeeoffice.dto.PersonalInfoCreateDto;
import com.example.employeeoffice.entity.PersonalInfo;
import com.example.employeeoffice.entity.Role;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PersonalInfoMapper {
    @Mapping(target = "birthday", source = "birthday")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "salary", source = "salary")
    @Mapping(target = "roles", ignore = true)


    @Mapping(target = "persInfoId", ignore = true)
    PersonalInfo toEntity(PersonalInfoCreateDto personalInfoCreateDto);

    @Mapping(target = "persInfoId", source = "persInfoId")
    PersonalInfoAfterCreationDto toDto(PersonalInfo personalInfoAfterCreation);

    default String mapRoles(Set<Role> roles) {
        if (roles == null || roles.isEmpty()) {
            return null;
        }
        return roles.stream()
                .map(Role::getRoleName)
                .map(Enum::name)
                .collect(Collectors.joining(","));
    }

}
