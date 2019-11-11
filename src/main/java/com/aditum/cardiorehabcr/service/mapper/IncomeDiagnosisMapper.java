package com.aditum.cardiorehabcr.service.mapper;

import com.aditum.cardiorehabcr.domain.*;
import com.aditum.cardiorehabcr.service.dto.IncomeDiagnosisDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link IncomeDiagnosis} and its DTO {@link IncomeDiagnosisDTO}.
 */
@Mapper(componentModel = "spring", uses = {RehabilitationCenterMapper.class})
public interface IncomeDiagnosisMapper extends EntityMapper<IncomeDiagnosisDTO, IncomeDiagnosis> {

    @Mapping(source = "rehabilitationCenter.id", target = "rehabilitationCenterId")
    IncomeDiagnosisDTO toDto(IncomeDiagnosis incomeDiagnosis);

    @Mapping(source = "rehabilitationCenterId", target = "rehabilitationCenter")
    IncomeDiagnosis toEntity(IncomeDiagnosisDTO incomeDiagnosisDTO);

    default IncomeDiagnosis fromId(Long id) {
        if (id == null) {
            return null;
        }
        IncomeDiagnosis incomeDiagnosis = new IncomeDiagnosis();
        incomeDiagnosis.setId(id);
        return incomeDiagnosis;
    }
}
