package com.aditum.cardiorehabcr.service.mapper;

import com.aditum.cardiorehabcr.domain.*;
import com.aditum.cardiorehabcr.service.dto.RehabilitationCenterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RehabilitationCenter} and its DTO {@link RehabilitationCenterDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RehabilitationCenterMapper extends EntityMapper<RehabilitationCenterDTO, RehabilitationCenter> {


    @Mapping(target = "rehabilitationGroups", ignore = true)
    @Mapping(target = "removeRehabilitationGroup", ignore = true)
    @Mapping(target = "incomeDiagnoses", ignore = true)
    @Mapping(target = "removeIncomeDiagnosis", ignore = true)
    @Mapping(target = "comorbidities", ignore = true)
    @Mapping(target = "removeComorbiditie", ignore = true)
    @Mapping(target = "minorEvents", ignore = true)
    @Mapping(target = "removeMinorEvent", ignore = true)
    @Mapping(target = "mayorEvents", ignore = true)
    @Mapping(target = "removeMayorEvent", ignore = true)
    @Mapping(target = "depressiveSymptoms", ignore = true)
    @Mapping(target = "removeDepressiveSymptom", ignore = true)
    @Mapping(target = "nonSpecificPains", ignore = true)
    @Mapping(target = "removeNonSpecificPain", ignore = true)
    @Mapping(target = "appUsers", ignore = true)
    @Mapping(target = "removeAppUser", ignore = true)
    RehabilitationCenter toEntity(RehabilitationCenterDTO rehabilitationCenterDTO);

    default RehabilitationCenter fromId(Long id) {
        if (id == null) {
            return null;
        }
        RehabilitationCenter rehabilitationCenter = new RehabilitationCenter();
        rehabilitationCenter.setId(id);
        return rehabilitationCenter;
    }
}
