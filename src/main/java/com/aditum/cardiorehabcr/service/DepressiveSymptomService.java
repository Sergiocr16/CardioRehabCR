package com.aditum.cardiorehabcr.service;

import com.aditum.cardiorehabcr.service.dto.DepressiveSymptomDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.aditum.cardiorehabcr.domain.DepressiveSymptom}.
 */
public interface DepressiveSymptomService {

    /**
     * Save a depressiveSymptom.
     *
     * @param depressiveSymptomDTO the entity to save.
     * @return the persisted entity.
     */
    DepressiveSymptomDTO save(DepressiveSymptomDTO depressiveSymptomDTO);

    /**
     * Get all the depressiveSymptoms.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DepressiveSymptomDTO> findAll(Pageable pageable, Long rehabilitationId);


    /**
     * Get the "id" depressiveSymptom.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DepressiveSymptomDTO> findOne(Long id);

    /**
     * Delete the "id" depressiveSymptom.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
