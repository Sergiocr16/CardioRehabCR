package com.aditum.cardiorehabcr.service.impl;

import com.aditum.cardiorehabcr.service.MayorEventService;
import com.aditum.cardiorehabcr.domain.MayorEvent;
import com.aditum.cardiorehabcr.repository.MayorEventRepository;
import com.aditum.cardiorehabcr.service.dto.MayorEventDTO;
import com.aditum.cardiorehabcr.service.mapper.MayorEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MayorEvent}.
 */
@Service
@Transactional
public class MayorEventServiceImpl implements MayorEventService {

    private final Logger log = LoggerFactory.getLogger(MayorEventServiceImpl.class);

    private final MayorEventRepository mayorEventRepository;

    private final MayorEventMapper mayorEventMapper;

    public MayorEventServiceImpl(MayorEventRepository mayorEventRepository, MayorEventMapper mayorEventMapper) {
        this.mayorEventRepository = mayorEventRepository;
        this.mayorEventMapper = mayorEventMapper;
    }

    /**
     * Save a mayorEvent.
     *
     * @param mayorEventDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MayorEventDTO save(MayorEventDTO mayorEventDTO) {
        log.debug("Request to save MayorEvent : {}", mayorEventDTO);
        MayorEvent mayorEvent = mayorEventMapper.toEntity(mayorEventDTO);
        mayorEvent = mayorEventRepository.save(mayorEvent);
        return mayorEventMapper.toDto(mayorEvent);
    }

    /**
     * Get all the mayorEvents.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MayorEventDTO> findAll(Pageable pageable, Long rehabilitationId) {
        log.debug("Request to get all MayorEvents");
        return mayorEventRepository.findByRehabilitationCenterIdAndAndDeleted(pageable,rehabilitationId, false)
            .map(mayorEventMapper::toDto);
    }


    /**
     * Get one mayorEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MayorEventDTO> findOne(Long id) {
        log.debug("Request to get MayorEvent : {}", id);
        return mayorEventRepository.findById(id)
            .map(mayorEventMapper::toDto);
    }

    /**
     * Delete the mayorEvent by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MayorEvent : {}", id);
        mayorEventRepository.deleteById(id);
    }
}
