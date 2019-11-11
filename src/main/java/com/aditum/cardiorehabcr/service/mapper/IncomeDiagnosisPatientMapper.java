package com.aditum.cardiorehabcr.service.mapper;

import com.aditum.cardiorehabcr.domain.*;
import com.aditum.cardiorehabcr.service.dto.IncomeDiagnosisPatientDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link IncomeDiagnosisPatient} and its DTO {@link IncomeDiagnosisPatientDTO}.
 */
@Mapper(componentModel = "spring", uses = {InitialAssessmentMapper.class})
public interface IncomeDiagnosisPatientMapper extends EntityMapper<IncomeDiagnosisPatientDTO, IncomeDiagnosisPatient> {

    @Mapping(source = "initialAssessment.id", target = "initialAssessmentId")
    IncomeDiagnosisPatientDTO toDto(IncomeDiagnosisPatient incomeDiagnosisPatient);

    @Mapping(source = "initialAssessmentId", target = "initialAssessment")
    IncomeDiagnosisPatient toEntity(IncomeDiagnosisPatientDTO incomeDiagnosisPatientDTO);

    default IncomeDiagnosisPatient fromId(Long id) {
        if (id == null) {
            return null;
        }
        IncomeDiagnosisPatient incomeDiagnosisPatient = new IncomeDiagnosisPatient();
        incomeDiagnosisPatient.setId(id);
        return incomeDiagnosisPatient;
    }
}
