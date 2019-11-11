package com.aditum.cardiorehabcr.service;

import com.aditum.cardiorehabcr.service.dto.MinorEventsSessionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.aditum.cardiorehabcr.domain.MinorEventsSession}.
 */
public interface MinorEventsSessionService {

    /**
     * Save a minorEventsSession.
     *
     * @param minorEventsSessionDTO the entity to save.
     * @return the persisted entity.
     */
    MinorEventsSessionDTO save(MinorEventsSessionDTO minorEventsSessionDTO);

    /**
     * Get all the minorEventsSessions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MinorEventsSessionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" minorEventsSession.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MinorEventsSessionDTO> findOne(Long id);

    /**
     * Delete the "id" minorEventsSession.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
