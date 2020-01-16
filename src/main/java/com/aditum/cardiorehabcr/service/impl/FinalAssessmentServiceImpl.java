package com.aditum.cardiorehabcr.service.impl;

import com.aditum.cardiorehabcr.service.FinalAssessmentService;
import com.aditum.cardiorehabcr.domain.FinalAssessment;
import com.aditum.cardiorehabcr.repository.FinalAssessmentRepository;
import com.aditum.cardiorehabcr.service.dto.FinalAssessmentDTO;
import com.aditum.cardiorehabcr.service.mapper.FinalAssessmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FinalAssessment}.
 */
@Service
@Transactional
public class FinalAssessmentServiceImpl implements FinalAssessmentService {

    private final Logger log = LoggerFactory.getLogger(FinalAssessmentServiceImpl.class);

    private final FinalAssessmentRepository finalAssessmentRepository;

    private final FinalAssessmentMapper finalAssessmentMapper;

    public FinalAssessmentServiceImpl(FinalAssessmentRepository finalAssessmentRepository, FinalAssessmentMapper finalAssessmentMapper) {
        this.finalAssessmentRepository = finalAssessmentRepository;
        this.finalAssessmentMapper = finalAssessmentMapper;
    }

    /**
     * Save a finalAssessment.
     *
     * @param finalAssessmentDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FinalAssessmentDTO save(FinalAssessmentDTO finalAssessmentDTO) {
        log.debug("Request to save FinalAssessment : {}", finalAssessmentDTO);
        FinalAssessment finalAssessment = finalAssessmentMapper.toEntity(finalAssessmentDTO);
        finalAssessment = finalAssessmentRepository.save(finalAssessment);
        return finalAssessmentMapper.toDto(finalAssessment);
    }

    /**
     * Get all the finalAssessments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FinalAssessmentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FinalAssessments");
        return finalAssessmentRepository.findAll(pageable)
            .map(finalAssessmentMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FinalAssessmentDTO> findAllByPatient(Pageable pageable, Long patientId) {
        log.debug("Request to get all FinalAssessments");
        return finalAssessmentRepository.findAllByPatientId(pageable, patientId)
            .map(finalAssessmentMapper::toDto);
    }


    /**
     * Get one finalAssessment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FinalAssessmentDTO> findOne(Long id) {
        log.debug("Request to get FinalAssessment : {}", id);
        return finalAssessmentRepository.findById(id)
            .map(finalAssessmentMapper::toDto);
    }

    /**
     * Delete the finalAssessment by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FinalAssessment : {}", id);
        finalAssessmentRepository.deleteById(id);
    }
}
