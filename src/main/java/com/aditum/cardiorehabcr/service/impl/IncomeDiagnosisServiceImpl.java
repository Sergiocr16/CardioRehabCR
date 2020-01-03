package com.aditum.cardiorehabcr.service.impl;

import com.aditum.cardiorehabcr.service.IncomeDiagnosisService;
import com.aditum.cardiorehabcr.domain.IncomeDiagnosis;
import com.aditum.cardiorehabcr.repository.IncomeDiagnosisRepository;
import com.aditum.cardiorehabcr.service.dto.IncomeDiagnosisDTO;
import com.aditum.cardiorehabcr.service.mapper.IncomeDiagnosisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link IncomeDiagnosis}.
 */
@Service
@Transactional
public class IncomeDiagnosisServiceImpl implements IncomeDiagnosisService {

    private final Logger log = LoggerFactory.getLogger(IncomeDiagnosisServiceImpl.class);

    private final IncomeDiagnosisRepository incomeDiagnosisRepository;

    private final IncomeDiagnosisMapper incomeDiagnosisMapper;

    public IncomeDiagnosisServiceImpl(IncomeDiagnosisRepository incomeDiagnosisRepository, IncomeDiagnosisMapper incomeDiagnosisMapper) {
        this.incomeDiagnosisRepository = incomeDiagnosisRepository;
        this.incomeDiagnosisMapper = incomeDiagnosisMapper;
    }

    /**
     * Save a incomeDiagnosis.
     *
     * @param incomeDiagnosisDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public IncomeDiagnosisDTO save(IncomeDiagnosisDTO incomeDiagnosisDTO) {
        log.debug("Request to save IncomeDiagnosis : {}", incomeDiagnosisDTO);
        IncomeDiagnosis incomeDiagnosis = incomeDiagnosisMapper.toEntity(incomeDiagnosisDTO);
        incomeDiagnosis = incomeDiagnosisRepository.save(incomeDiagnosis);
        return incomeDiagnosisMapper.toDto(incomeDiagnosis);
    }

    /**
     * Get all the incomeDiagnoses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<IncomeDiagnosisDTO> findAll(Pageable pageable, Long rehabilitationId) {
        log.debug("Request to get all IncomeDiagnoses");
        return incomeDiagnosisRepository.findByRehabilitationCenterIdAndAndDeleted(pageable,rehabilitationId,false)
            .map(incomeDiagnosisMapper::toDto);
    }


    /**
     * Get one incomeDiagnosis by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<IncomeDiagnosisDTO> findOne(Long id) {
        log.debug("Request to get IncomeDiagnosis : {}", id);
        return incomeDiagnosisRepository.findById(id)
            .map(incomeDiagnosisMapper::toDto);
    }

    /**
     * Delete the incomeDiagnosis by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete IncomeDiagnosis : {}", id);
        incomeDiagnosisRepository.deleteById(id);
    }
}
