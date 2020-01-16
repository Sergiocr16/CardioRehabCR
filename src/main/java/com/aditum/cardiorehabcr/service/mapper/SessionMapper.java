package com.aditum.cardiorehabcr.service.mapper;

import com.aditum.cardiorehabcr.domain.*;
import com.aditum.cardiorehabcr.service.dto.SessionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Session} and its DTO {@link SessionDTO}.
 */
@Mapper(componentModel = "spring", uses = {PatientMapper.class})
public interface SessionMapper extends EntityMapper<SessionDTO, Session> {

    @Mapping(source = "patient.id", target = "patientId")
    SessionDTO toDto(Session session);

    @Mapping(target = "depressiveSymptomsSessions", ignore = true)
    @Mapping(target = "removeDepressiveSymptomsSession", ignore = true)
    @Mapping(target = "mayorEventsSessions", ignore = true)
    @Mapping(target = "removeMayorEventsSession", ignore = true)
    @Mapping(target = "minorEventsSessions", ignore = true)
    @Mapping(target = "removeMinorEventsSession", ignore = true)
    @Mapping(target = "nonSpecificPainsSessions", ignore = true)
    @Mapping(target = "removeNonSpecificPainsSession", ignore = true)
    @Mapping(source = "patientId", target = "patient")
    Session toEntity(SessionDTO sessionDTO);

    default Session fromId(Long id) {
        if (id == null) {
            return null;
        }
        Session session = new Session();
        session.setId(id);
        return session;
    }
}
