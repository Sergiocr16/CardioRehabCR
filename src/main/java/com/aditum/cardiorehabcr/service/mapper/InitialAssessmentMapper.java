package com.aditum.cardiorehabcr.service.mapper;

import com.aditum.cardiorehabcr.domain.*;
import com.aditum.cardiorehabcr.service.dto.InitialAssessmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link InitialAssessment} and its DTO {@link InitialAssessmentDTO}.
 */
@Mapper(componentModel = "spring", uses = {PatientMapper.class})
public interface InitialAssessmentMapper extends EntityMapper<InitialAssessmentDTO, InitialAssessment> {

    @Mapping(source = "patient.id", target = "patientId")
    InitialAssessmentDTO toDto(InitialAssessment initialAssessment);

    @Mapping(target = "comorbiditiesPatients", ignore = true)
    @Mapping(target = "removeComorbiditiesPatient", ignore = true)
    @Mapping(target = "incomeDiagnosisPatients", ignore = true)
    @Mapping(target = "removeIncomeDiagnosisPatient", ignore = true)
    @Mapping(source = "patientId", target = "patient")
    InitialAssessment toEntity(InitialAssessmentDTO initialAssessmentDTO);

    default InitialAssessment fromId(Long id) {
        if (id == null) {
            return null;
        }
        InitialAssessment initialAssessment = new InitialAssessment();
        initialAssessment.setId(id);
        return initialAssessment;
    }
}
