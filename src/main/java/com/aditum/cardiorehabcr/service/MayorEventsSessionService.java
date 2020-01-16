package com.aditum.cardiorehabcr.service;

import com.aditum.cardiorehabcr.service.dto.MayorEventsSessionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.aditum.cardiorehabcr.domain.MayorEventsSession}.
 */
public interface MayorEventsSessionService {

    /**
     * Save a mayorEventsSession.
     *
     * @param mayorEventsSessionDTO the entity to save.
     * @return the persisted entity.
     */
    MayorEventsSessionDTO save(MayorEventsSessionDTO mayorEventsSessionDTO);

    /**
     * Get all the mayorEventsSessions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MayorEventsSessionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" mayorEventsSession.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MayorEventsSessionDTO> findOne(Long id);

    /**
     * Delete the "id" mayorEventsSession.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
