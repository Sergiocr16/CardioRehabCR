package com.aditum.cardiorehabcr.web.rest;

import com.aditum.cardiorehabcr.service.InitialAssessmentService;
import com.aditum.cardiorehabcr.service.impl.InitialAssessmentServiceImpl;
import com.aditum.cardiorehabcr.web.rest.errors.BadRequestAlertException;
import com.aditum.cardiorehabcr.service.dto.InitialAssessmentDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.aditum.cardiorehabcr.domain.InitialAssessment}.
 */
@RestController
@RequestMapping("/api")
public class InitialAssessmentResource {

    private final Logger log = LoggerFactory.getLogger(InitialAssessmentResource.class);

    private static final String ENTITY_NAME = "initialAssessment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InitialAssessmentServiceImpl initialAssessmentService;

    public InitialAssessmentResource(InitialAssessmentServiceImpl initialAssessmentService) {
        this.initialAssessmentService = initialAssessmentService;
    }

    /**
     * {@code POST  /initial-assessments} : Create a new initialAssessment.
     *
     * @param initialAssessmentDTO the initialAssessmentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new initialAssessmentDTO, or with status {@code 400 (Bad Request)} if the initialAssessment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/initial-assessments")
    public ResponseEntity<InitialAssessmentDTO> createInitialAssessment(@Valid @RequestBody InitialAssessmentDTO initialAssessmentDTO) throws URISyntaxException {
        log.debug("REST request to save InitialAssessment : {}", initialAssessmentDTO);
        if (initialAssessmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new initialAssessment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InitialAssessmentDTO result = initialAssessmentService.save(initialAssessmentDTO);
        return ResponseEntity.created(new URI("/api/initial-assessments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /initial-assessments} : Updates an existing initialAssessment.
     *
     * @param initialAssessmentDTO the initialAssessmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated initialAssessmentDTO,
     * or with status {@code 400 (Bad Request)} if the initialAssessmentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the initialAssessmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/initial-assessments")
    public ResponseEntity<InitialAssessmentDTO> updateInitialAssessment(@Valid @RequestBody InitialAssessmentDTO initialAssessmentDTO) throws URISyntaxException {
        log.debug("REST request to update InitialAssessment : {}", initialAssessmentDTO);
        if (initialAssessmentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InitialAssessmentDTO result = initialAssessmentService.save(initialAssessmentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, initialAssessmentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /initial-assessments} : get all the initialAssessments.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of initialAssessments in body.
     */
    @GetMapping("/initial-assessments")
    public ResponseEntity<List<InitialAssessmentDTO>> getAllInitialAssessments(Pageable pageable) {
        log.debug("REST request to get a page of InitialAssessments");
        Page<InitialAssessmentDTO> page = initialAssessmentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /initial-assessments/:id} : get the "id" initialAssessment.
     *
     * @param id the id of the initialAssessmentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the initialAssessmentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/initial-assessments/{id}")
    public ResponseEntity<InitialAssessmentDTO> getInitialAssessment(@PathVariable Long id) {
        log.debug("REST request to get InitialAssessment : {}", id);
        Optional<InitialAssessmentDTO> initialAssessmentDTO = initialAssessmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(initialAssessmentDTO);
    }

    @GetMapping("/initial-assessments/by-patient/{patientId}")
    public ResponseEntity<InitialAssessmentDTO> getInitialAssessmentByPatient(@PathVariable Long patientId) {
        log.debug("REST request to get InitialAssessment : {}", patientId);
        Optional<InitialAssessmentDTO> initialAssessmentDTO = initialAssessmentService.findOneByPatient(patientId);
        return ResponseUtil.wrapOrNotFound(initialAssessmentDTO);
    }

    /**
     * {@code DELETE  /initial-assessments/:id} : delete the "id" initialAssessment.
     *
     * @param id the id of the initialAssessmentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/initial-assessments/{id}")
    public ResponseEntity<Void> deleteInitialAssessment(@PathVariable Long id) {
        log.debug("REST request to delete InitialAssessment : {}", id);
        initialAssessmentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
