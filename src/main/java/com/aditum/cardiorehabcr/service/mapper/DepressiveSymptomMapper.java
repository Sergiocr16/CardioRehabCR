package com.aditum.cardiorehabcr.service.mapper;

import com.aditum.cardiorehabcr.domain.*;
import com.aditum.cardiorehabcr.service.dto.DepressiveSymptomDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DepressiveSymptom} and its DTO {@link DepressiveSymptomDTO}.
 */
@Mapper(componentModel = "spring", uses = {RehabilitationCenterMapper.class})
public interface DepressiveSymptomMapper extends EntityMapper<DepressiveSymptomDTO, DepressiveSymptom> {

    @Mapping(source = "rehabilitationCenter.id", target = "rehabilitationCenterId")
    DepressiveSymptomDTO toDto(DepressiveSymptom depressiveSymptom);

    @Mapping(source = "rehabilitationCenterId", target = "rehabilitationCenter")
    DepressiveSymptom toEntity(DepressiveSymptomDTO depressiveSymptomDTO);

    default DepressiveSymptom fromId(Long id) {
        if (id == null) {
            return null;
        }
        DepressiveSymptom depressiveSymptom = new DepressiveSymptom();
        depressiveSymptom.setId(id);
        return depressiveSymptom;
    }
}
