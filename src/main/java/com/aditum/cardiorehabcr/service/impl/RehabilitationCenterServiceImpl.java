package com.aditum.cardiorehabcr.service.impl;

import com.aditum.cardiorehabcr.service.RehabilitationCenterService;
import com.aditum.cardiorehabcr.domain.RehabilitationCenter;
import com.aditum.cardiorehabcr.repository.RehabilitationCenterRepository;
import com.aditum.cardiorehabcr.service.dto.RehabilitationCenterDTO;
import com.aditum.cardiorehabcr.service.mapper.RehabilitationCenterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link RehabilitationCenter}.
 */
@Service
@Transactional
public class RehabilitationCenterServiceImpl implements RehabilitationCenterService {

    private final Logger log = LoggerFactory.getLogger(RehabilitationCenterServiceImpl.class);

    private final RehabilitationCenterRepository rehabilitationCenterRepository;

    private final RehabilitationCenterMapper rehabilitationCenterMapper;

    public RehabilitationCenterServiceImpl(RehabilitationCenterRepository rehabilitationCenterRepository, RehabilitationCenterMapper rehabilitationCenterMapper) {
        this.rehabilitationCenterRepository = rehabilitationCenterRepository;
        this.rehabilitationCenterMapper = rehabilitationCenterMapper;
    }

    /**
     * Save a rehabilitationCenter.
     *
     * @param rehabilitationCenterDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public RehabilitationCenterDTO save(RehabilitationCenterDTO rehabilitationCenterDTO) {
        log.debug("Request to save RehabilitationCenter : {}", rehabilitationCenterDTO);
        RehabilitationCenter rehabilitationCenter = rehabilitationCenterMapper.toEntity(rehabilitationCenterDTO);
        rehabilitationCenter = rehabilitationCenterRepository.save(rehabilitationCenter);
        return rehabilitationCenterMapper.toDto(rehabilitationCenter);
    }

    /**
     * Get all the rehabilitationCenters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RehabilitationCenterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RehabilitationCenters");
        return rehabilitationCenterRepository.findAll(pageable)
            .map(rehabilitationCenterMapper::toDto);
    }


    /**
     * Get one rehabilitationCenter by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RehabilitationCenterDTO> findOne(Long id) {
        log.debug("Request to get RehabilitationCenter : {}", id);
        return rehabilitationCenterRepository.findById(id)
            .map(rehabilitationCenterMapper::toDto);
    }

    /**
     * Delete the rehabilitationCenter by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RehabilitationCenter : {}", id);
        rehabilitationCenterRepository.deleteById(id);
    }
}
