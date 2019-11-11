package com.aditum.cardiorehabcr.service.mapper;

import com.aditum.cardiorehabcr.domain.*;
import com.aditum.cardiorehabcr.service.dto.MinorEventsSessionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MinorEventsSession} and its DTO {@link MinorEventsSessionDTO}.
 */
@Mapper(componentModel = "spring", uses = {SessionMapper.class})
public interface MinorEventsSessionMapper extends EntityMapper<MinorEventsSessionDTO, MinorEventsSession> {

    @Mapping(source = "session.id", target = "sessionId")
    MinorEventsSessionDTO toDto(MinorEventsSession minorEventsSession);

    @Mapping(source = "sessionId", target = "session")
    MinorEventsSession toEntity(MinorEventsSessionDTO minorEventsSessionDTO);

    default MinorEventsSession fromId(Long id) {
        if (id == null) {
            return null;
        }
        MinorEventsSession minorEventsSession = new MinorEventsSession();
        minorEventsSession.setId(id);
        return minorEventsSession;
    }
}
