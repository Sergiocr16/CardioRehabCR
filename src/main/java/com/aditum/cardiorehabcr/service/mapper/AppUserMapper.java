package com.aditum.cardiorehabcr.service.mapper;

import com.aditum.cardiorehabcr.domain.*;
import com.aditum.cardiorehabcr.service.dto.AppUserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AppUser} and its DTO {@link AppUserDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, RehabilitationCenterMapper.class})
public interface AppUserMapper extends EntityMapper<AppUserDTO, AppUser> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    @Mapping(source = "rehabilitationCenter.id", target = "rehabilitationCenterId")
    AppUserDTO toDto(AppUser appUser);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "rehabilitationCenterId", target = "rehabilitationCenter")
    AppUser toEntity(AppUserDTO appUserDTO);

    default AppUser fromId(Long id) {
        if (id == null) {
            return null;
        }
        AppUser appUser = new AppUser();
        appUser.setId(id);
        return appUser;
    }
}
