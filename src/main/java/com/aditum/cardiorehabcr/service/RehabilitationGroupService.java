package com.aditum.cardiorehabcr.service;

import com.aditum.cardiorehabcr.service.dto.RehabilitationGroupDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.aditum.cardiorehabcr.domain.RehabilitationGroup}.
 */
public interface RehabilitationGroupService {

    /**
     * Save a rehabilitationGroup.
     *
     * @param rehabilitationGroupDTO the entity to save.
     * @return the persisted entity.
     */
    RehabilitationGroupDTO save(RehabilitationGroupDTO rehabilitationGroupDTO);

    /**
     * Get all the rehabilitationGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RehabilitationGroupDTO> findAll(Pageable pageable);

    /**
     * Get all the rehabilitationGroups with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<RehabilitationGroupDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" rehabilitationGroup.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RehabilitationGroupDTO> findOne(Long id);

    /**
     * Delete the "id" rehabilitationGroup.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
