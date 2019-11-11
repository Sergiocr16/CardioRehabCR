package com.aditum.cardiorehabcr.service;

import com.aditum.cardiorehabcr.service.dto.IncomeDiagnosisPatientDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.aditum.cardiorehabcr.domain.IncomeDiagnosisPatient}.
 */
public interface IncomeDiagnosisPatientService {

    /**
     * Save a incomeDiagnosisPatient.
     *
     * @param incomeDiagnosisPatientDTO the entity to save.
     * @return the persisted entity.
     */
    IncomeDiagnosisPatientDTO save(IncomeDiagnosisPatientDTO incomeDiagnosisPatientDTO);

    /**
     * Get all the incomeDiagnosisPatients.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<IncomeDiagnosisPatientDTO> findAll(Pageable pageable);


    /**
     * Get the "id" incomeDiagnosisPatient.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<IncomeDiagnosisPatientDTO> findOne(Long id);

    /**
     * Delete the "id" incomeDiagnosisPatient.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
