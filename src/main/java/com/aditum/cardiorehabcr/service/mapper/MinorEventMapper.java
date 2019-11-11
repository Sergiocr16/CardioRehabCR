package com.aditum.cardiorehabcr.service.mapper;

import com.aditum.cardiorehabcr.domain.*;
import com.aditum.cardiorehabcr.service.dto.MinorEventDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MinorEvent} and its DTO {@link MinorEventDTO}.
 */
@Mapper(componentModel = "spring", uses = {RehabilitationCenterMapper.class})
public interface MinorEventMapper extends EntityMapper<MinorEventDTO, MinorEvent> {

    @Mapping(source = "rehabilitationCenter.id", target = "rehabilitationCenterId")
    MinorEventDTO toDto(MinorEvent minorEvent);

    @Mapping(source = "rehabilitationCenterId", target = "rehabilitationCenter")
    MinorEvent toEntity(MinorEventDTO minorEventDTO);

    default MinorEvent fromId(Long id) {
        if (id == null) {
            return null;
        }
        MinorEvent minorEvent = new MinorEvent();
        minorEvent.setId(id);
        return minorEvent;
    }
}
