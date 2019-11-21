package com.aditum.cardiorehabcr.service;

import com.aditum.cardiorehabcr.service.dto.MayorEventDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.aditum.cardiorehabcr.domain.MayorEvent}.
 */
public interface MayorEventService {

    /**
     * Save a mayorEvent.
     *
     * @param mayorEventDTO the entity to save.
     * @return the persisted entity.
     */
    MayorEventDTO save(MayorEventDTO mayorEventDTO);

    /**
     * Get all the mayorEvents.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MayorEventDTO> findAll(Pageable pageable, Long rehabilitationId);


    /**
     * Get the "id" mayorEvent.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MayorEventDTO> findOne(Long id);

    /**
     * Delete the "id" mayorEvent.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
