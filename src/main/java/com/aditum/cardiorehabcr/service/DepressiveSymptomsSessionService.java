package com.aditum.cardiorehabcr.service;

import com.aditum.cardiorehabcr.service.dto.DepressiveSymptomsSessionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.aditum.cardiorehabcr.domain.DepressiveSymptomsSession}.
 */
public interface DepressiveSymptomsSessionService {

    /**
     * Save a depressiveSymptomsSession.
     *
     * @param depressiveSymptomsSessionDTO the entity to save.
     * @return the persisted entity.
     */
    DepressiveSymptomsSessionDTO save(DepressiveSymptomsSessionDTO depressiveSymptomsSessionDTO);

    /**
     * Get all the depressiveSymptomsSessions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DepressiveSymptomsSessionDTO> findAll(Pageable pageable);

    Page<DepressiveSymptomsSessionDTO> findAllBySession(Pageable pageable, Long sessionId);


    /**
     * Get the "id" depressiveSymptomsSession.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DepressiveSymptomsSessionDTO> findOne(Long id);

    /**
     * Delete the "id" depressiveSymptomsSession.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
