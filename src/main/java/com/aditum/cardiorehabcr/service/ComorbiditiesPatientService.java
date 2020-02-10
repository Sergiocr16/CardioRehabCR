package com.aditum.cardiorehabcr.service;

import com.aditum.cardiorehabcr.service.dto.ComorbiditiesPatientDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.aditum.cardiorehabcr.domain.ComorbiditiesPatient}.
 */
public interface ComorbiditiesPatientService {

    /**
     * Save a comorbiditiesPatient.
     *
     * @param comorbiditiesPatientDTO the entity to save.
     * @return the persisted entity.
     */
    ComorbiditiesPatientDTO save(ComorbiditiesPatientDTO comorbiditiesPatientDTO);

    /**
     * Get all the comorbiditiesPatients.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ComorbiditiesPatientDTO> findAll(Pageable pageable);

    List<ComorbiditiesPatientDTO> findAllByInitialAsessment(Long initialAsessmentId);

    /**
     * Get the "id" comorbiditiesPatient.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ComorbiditiesPatientDTO> findOne(Long id);

    /**
     * Delete the "id" comorbiditiesPatient.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
