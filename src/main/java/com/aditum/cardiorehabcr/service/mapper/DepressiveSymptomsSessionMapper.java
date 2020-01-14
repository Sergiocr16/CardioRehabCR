package com.aditum.cardiorehabcr.service.mapper;

import com.aditum.cardiorehabcr.domain.*;
import com.aditum.cardiorehabcr.service.dto.DepressiveSymptomsSessionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DepressiveSymptomsSession} and its DTO {@link DepressiveSymptomsSessionDTO}.
 */
@Mapper(componentModel = "spring", uses = {SessionMapper.class})
public interface DepressiveSymptomsSessionMapper extends EntityMapper<DepressiveSymptomsSessionDTO, DepressiveSymptomsSession> {

    @Mapping(source = "session.id", target = "sessionId")
    DepressiveSymptomsSessionDTO toDto(DepressiveSymptomsSession depressiveSymptomsSession);

    @Mapping(source = "sessionId", target = "session")
    DepressiveSymptomsSession toEntity(DepressiveSymptomsSessionDTO depressiveSymptomsSessionDTO);

    default DepressiveSymptomsSession fromId(Long id) {
        if (id == null) {
            return null;
        }
        DepressiveSymptomsSession depressiveSymptomsSession = new DepressiveSymptomsSession();
        depressiveSymptomsSession.setId(id);
        return depressiveSymptomsSession;
    }
}
