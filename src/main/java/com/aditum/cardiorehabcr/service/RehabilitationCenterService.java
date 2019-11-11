package com.aditum.cardiorehabcr.service;

import com.aditum.cardiorehabcr.service.dto.RehabilitationCenterDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.aditum.cardiorehabcr.domain.RehabilitationCenter}.
 */
public interface RehabilitationCenterService {

    /**
     * Save a rehabilitationCenter.
     *
     * @param rehabilitationCenterDTO the entity to save.
     * @return the persisted entity.
     */
    RehabilitationCenterDTO save(RehabilitationCenterDTO rehabilitationCenterDTO);

    /**
     * Get all the rehabilitationCenters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RehabilitationCenterDTO> findAll(Pageable pageable);


    /**
     * Get the "id" rehabilitationCenter.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RehabilitationCenterDTO> findOne(Long id);

    /**
     * Delete the "id" rehabilitationCenter.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
