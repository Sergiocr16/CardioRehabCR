package com.aditum.cardiorehabcr.service.mapper;

import com.aditum.cardiorehabcr.domain.*;
import com.aditum.cardiorehabcr.service.dto.ComorbiditieDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Comorbiditie} and its DTO {@link ComorbiditieDTO}.
 */
@Mapper(componentModel = "spring", uses = {RehabilitationCenterMapper.class})
public interface ComorbiditieMapper extends EntityMapper<ComorbiditieDTO, Comorbiditie> {

    @Mapping(source = "rehabilitationCenter.id", target = "rehabilitationCenterId")
    ComorbiditieDTO toDto(Comorbiditie comorbiditie);

    @Mapping(source = "rehabilitationCenterId", target = "rehabilitationCenter")
    Comorbiditie toEntity(ComorbiditieDTO comorbiditieDTO);

    default Comorbiditie fromId(Long id) {
        if (id == null) {
            return null;
        }
        Comorbiditie comorbiditie = new Comorbiditie();
        comorbiditie.setId(id);
        return comorbiditie;
    }
}
