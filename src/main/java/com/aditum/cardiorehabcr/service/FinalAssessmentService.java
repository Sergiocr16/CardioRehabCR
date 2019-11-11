package com.aditum.cardiorehabcr.service;

import com.aditum.cardiorehabcr.service.dto.FinalAssessmentDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.aditum.cardiorehabcr.domain.FinalAssessment}.
 */
public interface FinalAssessmentService {

    /**
     * Save a finalAssessment.
     *
     * @param finalAssessmentDTO the entity to save.
     * @return the persisted entity.
     */
    FinalAssessmentDTO save(FinalAssessmentDTO finalAssessmentDTO);

    /**
     * Get all the finalAssessments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FinalAssessmentDTO> findAll(Pageable pageable);


    /**
     * Get the "id" finalAssessment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FinalAssessmentDTO> findOne(Long id);

    /**
     * Delete the "id" finalAssessment.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
