package com.aditum.cardiorehabcr.service;

import com.aditum.cardiorehabcr.service.dto.ComorbiditieDTO;
import com.aditum.cardiorehabcr.service.dto.IncomeDiagnosisDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.aditum.cardiorehabcr.domain.IncomeDiagnosis}.
 */
public interface IncomeDiagnosisService {

    /**
     * Save a incomeDiagnosis.
     *
     * @param incomeDiagnosisDTO the entity to save.
     * @return the persisted entity.
     */
    IncomeDiagnosisDTO save(IncomeDiagnosisDTO incomeDiagnosisDTO);

    /**
     * Get all the incomeDiagnoses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<IncomeDiagnosisDTO> findAll(Pageable pageable, Long rehabilitationId);

    List<IncomeDiagnosisDTO> findAllNoPage(Long rehabilitationId);

    /**
     * Get the "id" incomeDiagnosis.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<IncomeDiagnosisDTO> findOne(Long id);

    /**
     * Delete the "id" incomeDiagnosis.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
