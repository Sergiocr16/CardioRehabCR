package com.aditum.cardiorehabcr.service.impl;

import com.aditum.cardiorehabcr.service.NonSpecificPainService;
import com.aditum.cardiorehabcr.domain.NonSpecificPain;
import com.aditum.cardiorehabcr.repository.NonSpecificPainRepository;
import com.aditum.cardiorehabcr.service.dto.NonSpecificPainDTO;
import com.aditum.cardiorehabcr.service.mapper.NonSpecificPainMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link NonSpecificPain}.
 */
@Service
@Transactional
public class NonSpecificPainServiceImpl implements NonSpecificPainService {

    private final Logger log = LoggerFactory.getLogger(NonSpecificPainServiceImpl.class);

    private final NonSpecificPainRepository nonSpecificPainRepository;

    private final NonSpecificPainMapper nonSpecificPainMapper;

    public NonSpecificPainServiceImpl(NonSpecificPainRepository nonSpecificPainRepository, NonSpecificPainMapper nonSpecificPainMapper) {
        this.nonSpecificPainRepository = nonSpecificPainRepository;
        this.nonSpecificPainMapper = nonSpecificPainMapper;
    }

    /**
     * Save a nonSpecificPain.
     *
     * @param nonSpecificPainDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public NonSpecificPainDTO save(NonSpecificPainDTO nonSpecificPainDTO) {
        log.debug("Request to save NonSpecificPain : {}", nonSpecificPainDTO);
        NonSpecificPain nonSpecificPain = nonSpecificPainMapper.toEntity(nonSpecificPainDTO);
        nonSpecificPain = nonSpecificPainRepository.save(nonSpecificPain);
        return nonSpecificPainMapper.toDto(nonSpecificPain);
    }

    /**
     * Get all the nonSpecificPains.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NonSpecificPainDTO> findAll(Pageable pageable, Long rehabilitationId) {
        log.debug("Request to get all NonSpecificPains");
        return nonSpecificPainRepository.findByRehabilitationCenterIdAndAndDeleted(pageable,rehabilitationId, false)
            .map(nonSpecificPainMapper::toDto);
    }


    /**
     * Get one nonSpecificPain by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NonSpecificPainDTO> findOne(Long id) {
        log.debug("Request to get NonSpecificPain : {}", id);
        return nonSpecificPainRepository.findById(id)
            .map(nonSpecificPainMapper::toDto);
    }

    /**
     * Delete the nonSpecificPain by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NonSpecificPain : {}", id);
        nonSpecificPainRepository.deleteById(id);
    }
}
