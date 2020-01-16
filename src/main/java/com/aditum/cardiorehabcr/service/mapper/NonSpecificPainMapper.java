package com.aditum.cardiorehabcr.service.mapper;

import com.aditum.cardiorehabcr.domain.*;
import com.aditum.cardiorehabcr.service.dto.NonSpecificPainDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NonSpecificPain} and its DTO {@link NonSpecificPainDTO}.
 */
@Mapper(componentModel = "spring", uses = {RehabilitationCenterMapper.class})
public interface NonSpecificPainMapper extends EntityMapper<NonSpecificPainDTO, NonSpecificPain> {

    @Mapping(source = "rehabilitationCenter.id", target = "rehabilitationCenterId")
    NonSpecificPainDTO toDto(NonSpecificPain nonSpecificPain);

    @Mapping(source = "rehabilitationCenterId", target = "rehabilitationCenter")
    NonSpecificPain toEntity(NonSpecificPainDTO nonSpecificPainDTO);

    default NonSpecificPain fromId(Long id) {
        if (id == null) {
            return null;
        }
        NonSpecificPain nonSpecificPain = new NonSpecificPain();
        nonSpecificPain.setId(id);
        return nonSpecificPain;
    }
}
