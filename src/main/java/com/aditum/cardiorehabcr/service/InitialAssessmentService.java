package com.aditum.cardiorehabcr.service;

import com.aditum.cardiorehabcr.service.dto.InitialAssessmentDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.aditum.cardiorehabcr.domain.InitialAssessment}.
 */
public interface InitialAssessmentService {

    /**
     * Save a initialAssessment.
     *
     * @param initialAssessmentDTO the entity to save.
     * @return the persisted entity.
     */
    InitialAssessmentDTO save(InitialAssessmentDTO initialAssessmentDTO);

    /**
     * Get all the initialAssessments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InitialAssessmentDTO> findAll(Pageable pageable);


    /**
     * Get the "id" initialAssessment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InitialAssessmentDTO> findOne(Long id);

    /**
     * Delete the "id" initialAssessment.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
