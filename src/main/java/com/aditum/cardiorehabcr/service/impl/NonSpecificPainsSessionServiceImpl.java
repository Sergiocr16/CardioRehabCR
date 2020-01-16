package com.aditum.cardiorehabcr.service.impl;

import com.aditum.cardiorehabcr.service.NonSpecificPainsSessionService;
import com.aditum.cardiorehabcr.domain.NonSpecificPainsSession;
import com.aditum.cardiorehabcr.repository.NonSpecificPainsSessionRepository;
import com.aditum.cardiorehabcr.service.dto.NonSpecificPainsSessionDTO;
import com.aditum.cardiorehabcr.service.mapper.NonSpecificPainsSessionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link NonSpecificPainsSession}.
 */
@Service
@Transactional
public class NonSpecificPainsSessionServiceImpl implements NonSpecificPainsSessionService {

    private final Logger log = LoggerFactory.getLogger(NonSpecificPainsSessionServiceImpl.class);

    private final NonSpecificPainsSessionRepository nonSpecificPainsSessionRepository;

    private final NonSpecificPainsSessionMapper nonSpecificPainsSessionMapper;

    public NonSpecificPainsSessionServiceImpl(NonSpecificPainsSessionRepository nonSpecificPainsSessionRepository, NonSpecificPainsSessionMapper nonSpecificPainsSessionMapper) {
        this.nonSpecificPainsSessionRepository = nonSpecificPainsSessionRepository;
        this.nonSpecificPainsSessionMapper = nonSpecificPainsSessionMapper;
    }

    /**
     * Save a nonSpecificPainsSession.
     *
     * @param nonSpecificPainsSessionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public NonSpecificPainsSessionDTO save(NonSpecificPainsSessionDTO nonSpecificPainsSessionDTO) {
        log.debug("Request to save NonSpecificPainsSession : {}", nonSpecificPainsSessionDTO);
        NonSpecificPainsSession nonSpecificPainsSession = nonSpecificPainsSessionMapper.toEntity(nonSpecificPainsSessionDTO);
        nonSpecificPainsSession = nonSpecificPainsSessionRepository.save(nonSpecificPainsSession);
        return nonSpecificPainsSessionMapper.toDto(nonSpecificPainsSession);
    }

    /**
     * Get all the nonSpecificPainsSessions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NonSpecificPainsSessionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NonSpecificPainsSessions");
        return nonSpecificPainsSessionRepository.findAll(pageable)
            .map(nonSpecificPainsSessionMapper::toDto);
    }


    /**
     * Get one nonSpecificPainsSession by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NonSpecificPainsSessionDTO> findOne(Long id) {
        log.debug("Request to get NonSpecificPainsSession : {}", id);
        return nonSpecificPainsSessionRepository.findById(id)
            .map(nonSpecificPainsSessionMapper::toDto);
    }

    /**
     * Delete the nonSpecificPainsSession by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NonSpecificPainsSession : {}", id);
        nonSpecificPainsSessionRepository.deleteById(id);
    }
}
