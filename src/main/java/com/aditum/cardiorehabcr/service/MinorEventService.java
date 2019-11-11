package com.aditum.cardiorehabcr.service;

import com.aditum.cardiorehabcr.service.dto.MinorEventDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.aditum.cardiorehabcr.domain.MinorEvent}.
 */
public interface MinorEventService {

    /**
     * Save a minorEvent.
     *
     * @param minorEventDTO the entity to save.
     * @return the persisted entity.
     */
    MinorEventDTO save(MinorEventDTO minorEventDTO);

    /**
     * Get all the minorEvents.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MinorEventDTO> findAll(Pageable pageable);


    /**
     * Get the "id" minorEvent.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MinorEventDTO> findOne(Long id);

    /**
     * Delete the "id" minorEvent.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
