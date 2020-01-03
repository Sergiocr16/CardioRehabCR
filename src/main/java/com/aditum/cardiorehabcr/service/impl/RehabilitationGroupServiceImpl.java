package com.aditum.cardiorehabcr.service.impl;

import com.aditum.cardiorehabcr.service.RehabilitationGroupService;
import com.aditum.cardiorehabcr.domain.RehabilitationGroup;
import com.aditum.cardiorehabcr.repository.RehabilitationGroupRepository;
import com.aditum.cardiorehabcr.service.dto.RehabilitationGroupDTO;
import com.aditum.cardiorehabcr.service.mapper.RehabilitationGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link RehabilitationGroup}.
 */
@Service
@Transactional
public class RehabilitationGroupServiceImpl implements RehabilitationGroupService {

    private final Logger log = LoggerFactory.getLogger(RehabilitationGroupServiceImpl.class);

    private final RehabilitationGroupRepository rehabilitationGroupRepository;

    private final RehabilitationGroupMapper rehabilitationGroupMapper;

    public RehabilitationGroupServiceImpl(RehabilitationGroupRepository rehabilitationGroupRepository, RehabilitationGroupMapper rehabilitationGroupMapper) {
        this.rehabilitationGroupRepository = rehabilitationGroupRepository;
        this.rehabilitationGroupMapper = rehabilitationGroupMapper;
    }

    /**
     * Save a rehabilitationGroup.
     *
     * @param rehabilitationGroupDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public RehabilitationGroupDTO save(RehabilitationGroupDTO rehabilitationGroupDTO) {
        log.debug("Request to save RehabilitationGroup : {}", rehabilitationGroupDTO);
        RehabilitationGroup rehabilitationGroup = rehabilitationGroupMapper.toEntity(rehabilitationGroupDTO);
        rehabilitationGroup = rehabilitationGroupRepository.save(rehabilitationGroup);
        return rehabilitationGroupMapper.toDto(rehabilitationGroup);
    }

    /**
     * Get all the rehabilitationGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RehabilitationGroupDTO> findAll(Pageable pageable, Long rehabilitationId) {
        log.debug("Request to get all RehabilitationGroups");
        return rehabilitationGroupRepository.findByRehabilitationCenterIdAndAndDeleted(pageable,rehabilitationId,false)
            .map(rehabilitationGroupMapper::toDto);
    }

    /**
     * Get all the rehabilitationGroups with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<RehabilitationGroupDTO> findAllWithEagerRelationships(Pageable pageable) {
        return rehabilitationGroupRepository.findAllWithEagerRelationships(pageable).map(rehabilitationGroupMapper::toDto);
    }


    /**
     * Get one rehabilitationGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RehabilitationGroupDTO> findOne(Long id) {
        log.debug("Request to get RehabilitationGroup : {}", id);
        return rehabilitationGroupRepository.findOneWithEagerRelationships(id)
            .map(rehabilitationGroupMapper::toDto);
    }

    /**
     * Delete the rehabilitationGroup by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RehabilitationGroup : {}", id);
        rehabilitationGroupRepository.deleteById(id);
    }
}
