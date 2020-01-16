package com.aditum.cardiorehabcr.service.mapper;

import com.aditum.cardiorehabcr.domain.*;
import com.aditum.cardiorehabcr.service.dto.MayorEventDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MayorEvent} and its DTO {@link MayorEventDTO}.
 */
@Mapper(componentModel = "spring", uses = {RehabilitationCenterMapper.class})
public interface MayorEventMapper extends EntityMapper<MayorEventDTO, MayorEvent> {

    @Mapping(source = "rehabilitationCenter.id", target = "rehabilitationCenterId")
    MayorEventDTO toDto(MayorEvent mayorEvent);

    @Mapping(source = "rehabilitationCenterId", target = "rehabilitationCenter")
    MayorEvent toEntity(MayorEventDTO mayorEventDTO);

    default MayorEvent fromId(Long id) {
        if (id == null) {
            return null;
        }
        MayorEvent mayorEvent = new MayorEvent();
        mayorEvent.setId(id);
        return mayorEvent;
    }
}
