package com.aditum.cardiorehabcr.service.mapper;

import com.aditum.cardiorehabcr.domain.*;
import com.aditum.cardiorehabcr.service.dto.FinalAssessmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FinalAssessment} and its DTO {@link FinalAssessmentDTO}.
 */
@Mapper(componentModel = "spring", uses = {PatientMapper.class})
public interface FinalAssessmentMapper extends EntityMapper<FinalAssessmentDTO, FinalAssessment> {

    @Mapping(source = "patient.id", target = "patientId")
    FinalAssessmentDTO toDto(FinalAssessment finalAssessment);

    @Mapping(source = "patientId", target = "patient")
    FinalAssessment toEntity(FinalAssessmentDTO finalAssessmentDTO);

    default FinalAssessment fromId(Long id) {
        if (id == null) {
            return null;
        }
        FinalAssessment finalAssessment = new FinalAssessment();
        finalAssessment.setId(id);
        return finalAssessment;
    }
}
