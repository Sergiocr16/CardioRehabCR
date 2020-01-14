package com.aditum.cardiorehabcr.service.impl;

import com.aditum.cardiorehabcr.service.ComorbiditiesPatientService;
import com.aditum.cardiorehabcr.domain.ComorbiditiesPatient;
import com.aditum.cardiorehabcr.repository.ComorbiditiesPatientRepository;
import com.aditum.cardiorehabcr.service.dto.ComorbiditiesPatientDTO;
import com.aditum.cardiorehabcr.service.mapper.ComorbiditiesPatientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ComorbiditiesPatient}.
 */
@Service
@Transactional
public class ComorbiditiesPatientServiceImpl implements ComorbiditiesPatientService {

    private final Logger log = LoggerFactory.getLogger(ComorbiditiesPatientServiceImpl.class);

    private final ComorbiditiesPatientRepository comorbiditiesPatientRepository;

    private final ComorbiditiesPatientMapper comorbiditiesPatientMapper;

    public ComorbiditiesPatientServiceImpl(ComorbiditiesPatientRepository comorbiditiesPatientRepository, ComorbiditiesPatientMapper comorbiditiesPatientMapper) {
        this.comorbiditiesPatientRepository = comorbiditiesPatientRepository;
        this.comorbiditiesPatientMapper = comorbiditiesPatientMapper;
    }

    /**
     * Save a comorbiditiesPatient.
     *
     * @param comorbiditiesPatientDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ComorbiditiesPatientDTO save(ComorbiditiesPatientDTO comorbiditiesPatientDTO) {
        log.debug("Request to save ComorbiditiesPatient : {}", comorbiditiesPatientDTO);
        ComorbiditiesPatient comorbiditiesPatient = comorbiditiesPatientMapper.toEntity(comorbiditiesPatientDTO);
        comorbiditiesPatient = comorbiditiesPatientRepository.save(comorbiditiesPatient);
        return comorbiditiesPatientMapper.toDto(comorbiditiesPatient);
    }

    /**
     * Get all the comorbiditiesPatients.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ComorbiditiesPatientDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ComorbiditiesPatients");
        return comorbiditiesPatientRepository.findAll(pageable)
            .map(comorbiditiesPatientMapper::toDto);
    }


    /**
     * Get one comorbiditiesPatient by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ComorbiditiesPatientDTO> findOne(Long id) {
        log.debug("Request to get ComorbiditiesPatient : {}", id);
        return comorbiditiesPatientRepository.findById(id)
            .map(comorbiditiesPatientMapper::toDto);
    }

    /**
     * Delete the comorbiditiesPatient by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ComorbiditiesPatient : {}", id);
        comorbiditiesPatientRepository.deleteById(id);
    }
}
