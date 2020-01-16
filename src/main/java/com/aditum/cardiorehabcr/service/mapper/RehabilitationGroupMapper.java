package com.aditum.cardiorehabcr.service.mapper;

import com.aditum.cardiorehabcr.domain.*;
import com.aditum.cardiorehabcr.service.dto.RehabilitationGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RehabilitationGroup} and its DTO {@link RehabilitationGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {PatientMapper.class, RehabilitationCenterMapper.class})
public interface RehabilitationGroupMapper extends EntityMapper<RehabilitationGroupDTO, RehabilitationGroup> {

    @Mapping(source = "rehabilitationCenter.id", target = "rehabilitationCenterId")
    RehabilitationGroupDTO toDto(RehabilitationGroup rehabilitationGroup);

    @Mapping(target = "removePatient", ignore = true)
    @Mapping(source = "rehabilitationCenterId", target = "rehabilitationCenter")
    RehabilitationGroup toEntity(RehabilitationGroupDTO rehabilitationGroupDTO);

    default RehabilitationGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        RehabilitationGroup rehabilitationGroup = new RehabilitationGroup();
        rehabilitationGroup.setId(id);
        return rehabilitationGroup;
    }
}
