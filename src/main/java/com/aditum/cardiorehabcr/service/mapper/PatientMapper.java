package com.aditum.cardiorehabcr.service.mapper;

import com.aditum.cardiorehabcr.domain.*;
import com.aditum.cardiorehabcr.service.dto.PatientDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Patient} and its DTO {@link PatientDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PatientMapper extends EntityMapper<PatientDTO, Patient> {


    @Mapping(target = "finalAssessments", ignore = true)
    @Mapping(target = "removeFinalAssessment", ignore = true)
    @Mapping(target = "initialAssessments", ignore = true)
    @Mapping(target = "removeInitialAssessment", ignore = true)
    @Mapping(target = "sessions", ignore = true)
    @Mapping(target = "removeSession", ignore = true)
    @Mapping(target = "rehabilitationGroups", ignore = true)
    @Mapping(target = "removeRehabilitationGroup", ignore = true)
    Patient toEntity(PatientDTO patientDTO);

    default Patient fromId(Long id) {
        if (id == null) {
            return null;
        }
        Patient patient = new Patient();
        patient.setId(id);
        return patient;
    }
}
