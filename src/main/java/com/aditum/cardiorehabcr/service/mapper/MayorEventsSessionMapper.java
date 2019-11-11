package com.aditum.cardiorehabcr.service.mapper;

import com.aditum.cardiorehabcr.domain.*;
import com.aditum.cardiorehabcr.service.dto.MayorEventsSessionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MayorEventsSession} and its DTO {@link MayorEventsSessionDTO}.
 */
@Mapper(componentModel = "spring", uses = {SessionMapper.class})
public interface MayorEventsSessionMapper extends EntityMapper<MayorEventsSessionDTO, MayorEventsSession> {

    @Mapping(source = "session.id", target = "sessionId")
    MayorEventsSessionDTO toDto(MayorEventsSession mayorEventsSession);

    @Mapping(source = "sessionId", target = "session")
    MayorEventsSession toEntity(MayorEventsSessionDTO mayorEventsSessionDTO);

    default MayorEventsSession fromId(Long id) {
        if (id == null) {
            return null;
        }
        MayorEventsSession mayorEventsSession = new MayorEventsSession();
        mayorEventsSession.setId(id);
        return mayorEventsSession;
    }
}
