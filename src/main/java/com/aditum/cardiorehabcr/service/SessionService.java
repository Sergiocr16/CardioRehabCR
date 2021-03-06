package com.aditum.cardiorehabcr.service;

import com.aditum.cardiorehabcr.service.dto.SessionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.aditum.cardiorehabcr.domain.Session}.
 */
public interface SessionService {

    /**
     * Save a session.
     *
     * @param sessionDTO the entity to save.
     * @return the persisted entity.
     */
    SessionDTO save(SessionDTO sessionDTO);

    /**
     * Get all the sessions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SessionDTO> findAll(Pageable pageable);

    Page<SessionDTO> findAllByPatient(Pageable pageable, Long patientId);


    /**
     * Get the "id" session.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SessionDTO> findOne(Long id);

    /**
     * Delete the "id" session.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
