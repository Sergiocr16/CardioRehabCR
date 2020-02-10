package com.aditum.cardiorehabcr.service;

import com.aditum.cardiorehabcr.service.dto.ComorbiditieDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.aditum.cardiorehabcr.domain.Comorbiditie}.
 */
public interface ComorbiditieService {

    /**
     * Save a comorbiditie.
     *
     * @param comorbiditieDTO the entity to save.
     * @return the persisted entity.
     */
    ComorbiditieDTO save(ComorbiditieDTO comorbiditieDTO);

    /**
     * Get all the comorbidities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ComorbiditieDTO> findAll(Pageable pageable, Long rehabilitationId);

    List<ComorbiditieDTO> findAllNoPage(Long rehabilitationId);

    /**
     * Get the "id" comorbiditie.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ComorbiditieDTO> findOne(Long id);


    /**
     * Delete the "id" comorbiditie.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
