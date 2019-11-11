package com.aditum.cardiorehabcr.service.mapper;

import com.aditum.cardiorehabcr.domain.*;
import com.aditum.cardiorehabcr.service.dto.NonSpecificPainsSessionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NonSpecificPainsSession} and its DTO {@link NonSpecificPainsSessionDTO}.
 */
@Mapper(componentModel = "spring", uses = {SessionMapper.class})
public interface NonSpecificPainsSessionMapper extends EntityMapper<NonSpecificPainsSessionDTO, NonSpecificPainsSession> {

    @Mapping(source = "session.id", target = "sessionId")
    NonSpecificPainsSessionDTO toDto(NonSpecificPainsSession nonSpecificPainsSession);

    @Mapping(source = "sessionId", target = "session")
    NonSpecificPainsSession toEntity(NonSpecificPainsSessionDTO nonSpecificPainsSessionDTO);

    default NonSpecificPainsSession fromId(Long id) {
        if (id == null) {
            return null;
        }
        NonSpecificPainsSession nonSpecificPainsSession = new NonSpecificPainsSession();
        nonSpecificPainsSession.setId(id);
        return nonSpecificPainsSession;
    }
}
