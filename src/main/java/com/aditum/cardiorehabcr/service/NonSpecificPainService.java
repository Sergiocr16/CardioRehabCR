package com.aditum.cardiorehabcr.service;

import com.aditum.cardiorehabcr.service.dto.NonSpecificPainDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.aditum.cardiorehabcr.domain.NonSpecificPain}.
 */
public interface NonSpecificPainService {

    /**
     * Save a nonSpecificPain.
     *
     * @param nonSpecificPainDTO the entity to save.
     * @return the persisted entity.
     */
    NonSpecificPainDTO save(NonSpecificPainDTO nonSpecificPainDTO);

    /**
     * Get all the nonSpecificPains.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NonSpecificPainDTO> findAll(Pageable pageable);


    /**
     * Get the "id" nonSpecificPain.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NonSpecificPainDTO> findOne(Long id);

    /**
     * Delete the "id" nonSpecificPain.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
