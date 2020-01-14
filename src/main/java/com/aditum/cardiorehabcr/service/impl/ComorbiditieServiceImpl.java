package com.aditum.cardiorehabcr.service.impl;

import com.aditum.cardiorehabcr.service.ComorbiditieService;
import com.aditum.cardiorehabcr.domain.Comorbiditie;
import com.aditum.cardiorehabcr.repository.ComorbiditieRepository;
import com.aditum.cardiorehabcr.service.dto.ComorbiditieDTO;
import com.aditum.cardiorehabcr.service.mapper.ComorbiditieMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Comorbiditie}.
 */
@Service
@Transactional
public class ComorbiditieServiceImpl implements ComorbiditieService {

    private final Logger log = LoggerFactory.getLogger(ComorbiditieServiceImpl.class);

    private final ComorbiditieRepository comorbiditieRepository;

    private final ComorbiditieMapper comorbiditieMapper;

    public ComorbiditieServiceImpl(ComorbiditieRepository comorbiditieRepository, ComorbiditieMapper comorbiditieMapper) {
        this.comorbiditieRepository = comorbiditieRepository;
        this.comorbiditieMapper = comorbiditieMapper;
    }

    /**
     * Save a comorbiditie.
     *
     * @param comorbiditieDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ComorbiditieDTO save(ComorbiditieDTO comorbiditieDTO) {
        log.debug("Request to save Comorbiditie : {}", comorbiditieDTO);
        Comorbiditie comorbiditie = comorbiditieMapper.toEntity(comorbiditieDTO);
        comorbiditie = comorbiditieRepository.save(comorbiditie);
        return comorbiditieMapper.toDto(comorbiditie);
    }

    /**
     * Get all the comorbidities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ComorbiditieDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Comorbidities");
        return comorbiditieRepository.findAll(pageable)
            .map(comorbiditieMapper::toDto);
    }


    /**
     * Get one comorbiditie by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ComorbiditieDTO> findOne(Long id) {
        log.debug("Request to get Comorbiditie : {}", id);
        return comorbiditieRepository.findById(id)
            .map(comorbiditieMapper::toDto);
    }

    /**
     * Delete the comorbiditie by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Comorbiditie : {}", id);
        comorbiditieRepository.deleteById(id);
    }
}
