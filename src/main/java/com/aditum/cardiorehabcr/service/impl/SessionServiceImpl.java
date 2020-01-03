package com.aditum.cardiorehabcr.service.impl;

import com.aditum.cardiorehabcr.service.SessionService;
import com.aditum.cardiorehabcr.domain.Session;
import com.aditum.cardiorehabcr.repository.SessionRepository;
import com.aditum.cardiorehabcr.service.dto.NonSpecificPainsSessionDTO;
import com.aditum.cardiorehabcr.service.dto.SessionDTO;
import com.aditum.cardiorehabcr.service.mapper.SessionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Session}.
 */
@Service
@Transactional
public class SessionServiceImpl implements SessionService {

    private final Logger log = LoggerFactory.getLogger(SessionServiceImpl.class);

    private final SessionRepository sessionRepository;

    private final SessionMapper sessionMapper;

    private final DepressiveSymptomsSessionServiceImpl depressiveSymptomService;

    private final MinorEventsSessionServiceImpl minorEventService;

    private final MayorEventsSessionServiceImpl mayorEventService;

    private final NonSpecificPainsSessionServiceImpl nonSpecificPainService;

    public SessionServiceImpl(NonSpecificPainsSessionServiceImpl nonSpecificPainService, MayorEventsSessionServiceImpl mayorEventService, MinorEventsSessionServiceImpl minorEventService, DepressiveSymptomsSessionServiceImpl depressiveSymptomService, SessionRepository sessionRepository, SessionMapper sessionMapper) {
        this.sessionRepository = sessionRepository;
        this.sessionMapper = sessionMapper;
        this.depressiveSymptomService = depressiveSymptomService;
        this.mayorEventService = mayorEventService;
        this.minorEventService = minorEventService;
        this.nonSpecificPainService = nonSpecificPainService;

    }

    /**
     * Save a session.
     *
     * @param sessionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SessionDTO save(SessionDTO sessionDTO) {
        log.debug("Request to save Session : {}", sessionDTO);
        Session session = sessionMapper.toEntity(sessionDTO);
        session = sessionRepository.save(session);
        Session finalSession = session;
        sessionDTO.getDepressiveSymptomsSessions().forEach(depressiveSymptomsSessionDTO -> {
             depressiveSymptomsSessionDTO.setSessionId(finalSession.getId());
             this.depressiveSymptomService.save(depressiveSymptomsSessionDTO);
         });
        sessionDTO.getMayorEventsSessions().forEach(mayorEventsSessionDTO -> {
            mayorEventsSessionDTO.setSessionId(finalSession.getId());
            this.mayorEventService.save(mayorEventsSessionDTO);
        });
        sessionDTO.getMinorEventsSessions().forEach(minorEventsSessionDTO -> {
            minorEventsSessionDTO.setSessionId(finalSession.getId());
            this.minorEventService.save(minorEventsSessionDTO);
        });
        sessionDTO.getNonSpecificPainsSessions().forEach(nonSpecificPainsSessionDTO -> {
            nonSpecificPainsSessionDTO.setSessionId(finalSession.getId());
            this.nonSpecificPainService.save(nonSpecificPainsSessionDTO);
        });
        return sessionMapper.toDto(session);
    }

    /**
     * Get all the sessions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SessionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Sessions");
        return sessionRepository.findAll(pageable)
            .map(sessionMapper::toDto);
    }
    @Override
    @Transactional(readOnly = true)
    public Page<SessionDTO> findAllByPatient(Pageable pageable,Long patientId) {
        log.debug("Request to get all Sessions");
        return sessionRepository.findAllByPatientId(pageable, patientId)
            .map(sessionMapper::toDto);
    }


    /**
     * Get one session by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SessionDTO> findOne(Long id) {
        log.debug("Request to get Session : {}", id);
        return sessionRepository.findById(id)
            .map(sessionMapper::toDto);
    }

    /**
     * Delete the session by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Session : {}", id);
        sessionRepository.deleteById(id);
    }
}
