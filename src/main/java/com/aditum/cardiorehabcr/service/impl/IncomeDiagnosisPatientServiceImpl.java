package com.aditum.cardiorehabcr.service.impl;

import com.aditum.cardiorehabcr.service.IncomeDiagnosisPatientService;
import com.aditum.cardiorehabcr.domain.IncomeDiagnosisPatient;
import com.aditum.cardiorehabcr.repository.IncomeDiagnosisPatientRepository;
import com.aditum.cardiorehabcr.service.dto.IncomeDiagnosisPatientDTO;
import com.aditum.cardiorehabcr.service.mapper.IncomeDiagnosisPatientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link IncomeDiagnosisPatient}.
 */
@Service
@Transactional
public class IncomeDiagnosisPatientServiceImpl implements IncomeDiagnosisPatientService {

    private final Logger log = LoggerFactory.getLogger(IncomeDiagnosisPatientServiceImpl.class);

    private final IncomeDiagnosisPatientRepository incomeDiagnosisPatientRepository;

    private final IncomeDiagnosisPatientMapper incomeDiagnosisPatientMapper;

    public IncomeDiagnosisPatientServiceImpl(IncomeDiagnosisPatientRepository incomeDiagnosisPatientRepository, IncomeDiagnosisPatientMapper incomeDiagnosisPatientMapper) {
        this.incomeDiagnosisPatientRepository = incomeDiagnosisPatientRepository;
        this.incomeDiagnosisPatientMapper = incomeDiagnosisPatientMapper;
    }

    /**
     * Save a incomeDiagnosisPatient.
     *
     * @param incomeDiagnosisPatientDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public IncomeDiagnosisPatientDTO save(IncomeDiagnosisPatientDTO incomeDiagnosisPatientDTO) {
        log.debug("Request to save IncomeDiagnosisPatient : {}", incomeDiagnosisPatientDTO);

        IncomeDiagnosisPatient incomeDiagnosisPatient = incomeDiagnosisPatientMapper.toEntity(incomeDiagnosisPatientDTO);
        Optional<IncomeDiagnosisPatient> incomeDiagnosisPatientOld = incomeDiagnosisPatientRepository.findFirstByInitialAssessmentIdAndIncomeDiagnosisId(incomeDiagnosisPatientDTO.getInitialAssessmentId(),incomeDiagnosisPatientDTO.getIncomeDiagnosisId());
        if(incomeDiagnosisPatientOld.isPresent()){
            this.delete(incomeDiagnosisPatientOld.get().getId());
        }
        incomeDiagnosisPatient = incomeDiagnosisPatientRepository.save(incomeDiagnosisPatient);
        return incomeDiagnosisPatientMapper.toDto(incomeDiagnosisPatient);
    }

    /**
     * Get all the incomeDiagnosisPatients.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<IncomeDiagnosisPatientDTO> findAll(Pageable pageable) {
        log.debug("Request to get all IncomeDiagnosisPatients");
        return incomeDiagnosisPatientRepository.findAll(pageable)
            .map(incomeDiagnosisPatientMapper::toDto);
    }


    /**
     * Get one incomeDiagnosisPatient by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<IncomeDiagnosisPatientDTO> findOne(Long id) {
        log.debug("Request to get IncomeDiagnosisPatient : {}", id);
        return incomeDiagnosisPatientRepository.findById(id)
            .map(incomeDiagnosisPatientMapper::toDto);
    }
    @Transactional(readOnly = true)
    public List<IncomeDiagnosisPatientDTO> findAllByInitialAsessment(Long initialAsessmentId) {
        log.debug("Request to get all ComorbiditiesPatients");
        List<IncomeDiagnosisPatientDTO> formatedIncomeDiagnosis= new ArrayList<>();
        incomeDiagnosisPatientRepository.findByInitialAssessmentId(initialAsessmentId).forEach(incomeDiagnosisPatient -> {
            formatedIncomeDiagnosis.add(this.incomeDiagnosisPatientMapper.toDto(incomeDiagnosisPatient));
        });
        return formatedIncomeDiagnosis;
    }
    /**
     * Delete the incomeDiagnosisPatient by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete IncomeDiagnosisPatient : {}", id);
        incomeDiagnosisPatientRepository.deleteById(id);
    }
}
