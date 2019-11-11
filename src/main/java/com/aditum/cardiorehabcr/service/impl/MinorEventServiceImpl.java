package com.aditum.cardiorehabcr.service.impl;

import com.aditum.cardiorehabcr.service.MinorEventService;
import com.aditum.cardiorehabcr.domain.MinorEvent;
import com.aditum.cardiorehabcr.repository.MinorEventRepository;
import com.aditum.cardiorehabcr.service.dto.MinorEventDTO;
import com.aditum.cardiorehabcr.service.mapper.MinorEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MinorEvent}.
 */
@Service
@Transactional
public class MinorEventServiceImpl implements MinorEventService {

    private final Logger log = LoggerFactory.getLogger(MinorEventServiceImpl.class);

    private final MinorEventRepository minorEventRepository;

    private final MinorEventMapper minorEventMapper;

    public MinorEventServiceImpl(MinorEventRepository minorEventRepository, MinorEventMapper minorEventMapper) {
        this.minorEventRepository = minorEventRepository;
        this.minorEventMapper = minorEventMapper;
    }

    /**
     * Save a minorEvent.
     *
     * @param minorEventDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MinorEventDTO save(MinorEventDTO minorEventDTO) {
        log.debug("Request to save MinorEvent : {}", minorEventDTO);
        MinorEvent minorEvent = minorEventMapper.toEntity(minorEventDTO);
        minorEvent = minorEventRepository.save(minorEvent);
        return minorEventMapper.toDto(minorEvent);
    }

    /**
     * Get all the minorEvents.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MinorEventDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MinorEvents");
        return minorEventRepository.findAll(pageable)
            .map(minorEventMapper::toDto);
    }


    /**
     * Get one minorEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MinorEventDTO> findOne(Long id) {
        log.debug("Request to get MinorEvent : {}", id);
        return minorEventRepository.findById(id)
            .map(minorEventMapper::toDto);
    }

    /**
     * Delete the minorEvent by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MinorEvent : {}", id);
        minorEventRepository.deleteById(id);
    }
}
