package com.aditum.cardiorehabcr.service;

import com.aditum.cardiorehabcr.service.dto.NonSpecificPainsSessionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.aditum.cardiorehabcr.domain.NonSpecificPainsSession}.
 */
public interface NonSpecificPainsSessionService {

    /**
     * Save a nonSpecificPainsSession.
     *
     * @param nonSpecificPainsSessionDTO the entity to save.
     * @return the persisted entity.
     */
    NonSpecificPainsSessionDTO save(NonSpecificPainsSessionDTO nonSpecificPainsSessionDTO);

    /**
     * Get all the nonSpecificPainsSessions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NonSpecificPainsSessionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" nonSpecificPainsSession.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NonSpecificPainsSessionDTO> findOne(Long id);

    /**
     * Delete the "id" nonSpecificPainsSession.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
