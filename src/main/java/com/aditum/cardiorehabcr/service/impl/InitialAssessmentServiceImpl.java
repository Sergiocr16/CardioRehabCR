package com.aditum.cardiorehabcr.service.impl;

import com.aditum.cardiorehabcr.service.InitialAssessmentService;
import com.aditum.cardiorehabcr.domain.InitialAssessment;
import com.aditum.cardiorehabcr.repository.InitialAssessmentRepository;
import com.aditum.cardiorehabcr.service.dto.InitialAssessmentDTO;
import com.aditum.cardiorehabcr.service.mapper.InitialAssessmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link InitialAssessment}.
 */
@Service
@Transactional
public class InitialAssessmentServiceImpl implements InitialAssessmentService {

    private final Logger log = LoggerFactory.getLogger(InitialAssessmentServiceImpl.class);

    private final InitialAssessmentRepository initialAssessmentRepository;

    private final InitialAssessmentMapper initialAssessmentMapper;

    public InitialAssessmentServiceImpl(InitialAssessmentRepository initialAssessmentRepository, InitialAssessmentMapper initialAssessmentMapper) {
        this.initialAssessmentRepository = initialAssessmentRepository;
        this.initialAssessmentMapper = initialAssessmentMapper;
    }

    /**
     * Save a initialAssessment.
     *
     * @param initialAssessmentDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public InitialAssessmentDTO save(InitialAssessmentDTO initialAssessmentDTO) {
        log.debug("Request to save InitialAssessment : {}", initialAssessmentDTO);
        InitialAssessment initialAssessment = initialAssessmentMapper.toEntity(initialAssessmentDTO);
        initialAssessment = initialAssessmentRepository.save(initialAssessment);
        return initialAssessmentMapper.toDto(initialAssessment);
    }

    /**
     * Get all the initialAssessments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<InitialAssessmentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InitialAssessments");
        return initialAssessmentRepository.findAll(pageable)
            .map(initialAssessmentMapper::toDto);
    }


    /**
     * Get one initialAssessment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InitialAssessmentDTO> findOne(Long id) {
        log.debug("Request to get InitialAssessment : {}", id);
        return initialAssessmentRepository.findById(id)
            .map(initialAssessmentMapper::toDto);
    }

    /**
     * Delete the initialAssessment by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete InitialAssessment : {}", id);
        initialAssessmentRepository.deleteById(id);
    }
}
