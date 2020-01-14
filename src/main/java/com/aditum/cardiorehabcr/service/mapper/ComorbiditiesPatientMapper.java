package com.aditum.cardiorehabcr.service.mapper;

import com.aditum.cardiorehabcr.domain.*;
import com.aditum.cardiorehabcr.service.dto.ComorbiditiesPatientDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ComorbiditiesPatient} and its DTO {@link ComorbiditiesPatientDTO}.
 */
@Mapper(componentModel = "spring", uses = {InitialAssessmentMapper.class})
public interface ComorbiditiesPatientMapper extends EntityMapper<ComorbiditiesPatientDTO, ComorbiditiesPatient> {

    @Mapping(source = "initialAssessment.id", target = "initialAssessmentId")
    ComorbiditiesPatientDTO toDto(ComorbiditiesPatient comorbiditiesPatient);

    @Mapping(source = "initialAssessmentId", target = "initialAssessment")
    ComorbiditiesPatient toEntity(ComorbiditiesPatientDTO comorbiditiesPatientDTO);

    default ComorbiditiesPatient fromId(Long id) {
        if (id == null) {
            return null;
        }
        ComorbiditiesPatient comorbiditiesPatient = new ComorbiditiesPatient();
        comorbiditiesPatient.setId(id);
        return comorbiditiesPatient;
    }
}
