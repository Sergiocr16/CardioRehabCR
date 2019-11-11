package com.aditum.cardiorehabcr.service.impl;

import com.aditum.cardiorehabcr.service.MinorEventsSessionService;
import com.aditum.cardiorehabcr.domain.MinorEventsSession;
import com.aditum.cardiorehabcr.repository.MinorEventsSessionRepository;
import com.aditum.cardiorehabcr.service.dto.MinorEventsSessionDTO;
import com.aditum.cardiorehabcr.service.mapper.MinorEventsSessionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MinorEventsSession}.
 */
@Service
@Transactional
public class MinorEventsSessionServiceImpl implements MinorEventsSessionService {

    private final Logger log = LoggerFactory.getLogger(MinorEventsSessionServiceImpl.class);

    private final MinorEventsSessionRepository minorEventsSessionRepository;

    private final MinorEventsSessionMapper minorEventsSessionMapper;

    public MinorEventsSessionServiceImpl(MinorEventsSessionRepository minorEventsSessionRepository, MinorEventsSessionMapper minorEventsSessionMapper) {
        this.minorEventsSessionRepository = minorEventsSessionRepository;
        this.minorEventsSessionMapper = minorEventsSessionMapper;
    }

    /**
     * Save a minorEventsSession.
     *
     * @param minorEventsSessionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MinorEventsSessionDTO save(MinorEventsSessionDTO minorEventsSessionDTO) {
        log.debug("Request to save MinorEventsSession : {}", minorEventsSessionDTO);
        MinorEventsSession minorEventsSession = minorEventsSessionMapper.toEntity(minorEventsSessionDTO);
        minorEventsSession = minorEventsSessionRepository.save(minorEventsSession);
        return minorEventsSessionMapper.toDto(minorEventsSession);
    }

    /**
     * Get all the minorEventsSessions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MinorEventsSessionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MinorEventsSessions");
        return minorEventsSessionRepository.findAll(pageable)
            .map(minorEventsSessionMapper::toDto);
    }


    /**
     * Get one minorEventsSession by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MinorEventsSessionDTO> findOne(Long id) {
        log.debug("Request to get MinorEventsSession : {}", id);
        return minorEventsSessionRepository.findById(id)
            .map(minorEventsSessionMapper::toDto);
    }

    /**
     * Delete the minorEventsSession by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MinorEventsSession : {}", id);
        minorEventsSessionRepository.deleteById(id);
    }
}
