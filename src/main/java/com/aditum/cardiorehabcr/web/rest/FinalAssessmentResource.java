package com.aditum.cardiorehabcr.web.rest;

import com.aditum.cardiorehabcr.service.FinalAssessmentService;
import com.aditum.cardiorehabcr.web.rest.errors.BadRequestAlertException;
import com.aditum.cardiorehabcr.service.dto.FinalAssessmentDTO;

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

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.aditum.cardiorehabcr.domain.FinalAssessment}.
 */
@RestController
@RequestMapping("/api")
public class FinalAssessmentResource {

    private final Logger log = LoggerFactory.getLogger(FinalAssessmentResource.class);

    private static final String ENTITY_NAME = "finalAssessment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FinalAssessmentService finalAssessmentService;

    public FinalAssessmentResource(FinalAssessmentService finalAssessmentService) {
        this.finalAssessmentService = finalAssessmentService;
    }

    /**
     * {@code POST  /final-assessments} : Create a new finalAssessment.
     *
     * @param finalAssessmentDTO the finalAssessmentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new finalAssessmentDTO, or with status {@code 400 (Bad Request)} if the finalAssessment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/final-assessments")
    public ResponseEntity<FinalAssessmentDTO> createFinalAssessment(@RequestBody FinalAssessmentDTO finalAssessmentDTO) throws URISyntaxException {
        log.debug("REST request to save FinalAssessment : {}", finalAssessmentDTO);
        if (finalAssessmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new finalAssessment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FinalAssessmentDTO result = finalAssessmentService.save(finalAssessmentDTO);
        return ResponseEntity.created(new URI("/api/final-assessments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /final-assessments} : Updates an existing finalAssessment.
     *
     * @param finalAssessmentDTO the finalAssessmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated finalAssessmentDTO,
     * or with status {@code 400 (Bad Request)} if the finalAssessmentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the finalAssessmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/final-assessments")
    public ResponseEntity<FinalAssessmentDTO> updateFinalAssessment(@RequestBody FinalAssessmentDTO finalAssessmentDTO) throws URISyntaxException {
        log.debug("REST request to update FinalAssessment : {}", finalAssessmentDTO);
        if (finalAssessmentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FinalAssessmentDTO result = finalAssessmentService.save(finalAssessmentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, finalAssessmentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /final-assessments} : get all the finalAssessments.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of finalAssessments in body.
     */
    @GetMapping("/final-assessments")
    public ResponseEntity<List<FinalAssessmentDTO>> getAllFinalAssessments(Pageable pageable) {
        log.debug("REST request to get a page of FinalAssessments");
        Page<FinalAssessmentDTO> page = finalAssessmentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /final-assessments/:id} : get the "id" finalAssessment.
     *
     * @param id the id of the finalAssessmentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the finalAssessmentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/final-assessments/{id}")
    public ResponseEntity<FinalAssessmentDTO> getFinalAssessment(@PathVariable Long id) {
        log.debug("REST request to get FinalAssessment : {}", id);
        Optional<FinalAssessmentDTO> finalAssessmentDTO = finalAssessmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(finalAssessmentDTO);
    }

    /**
     * {@code DELETE  /final-assessments/:id} : delete the "id" finalAssessment.
     *
     * @param id the id of the finalAssessmentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/final-assessments/{id}")
    public ResponseEntity<Void> deleteFinalAssessment(@PathVariable Long id) {
        log.debug("REST request to delete FinalAssessment : {}", id);
        finalAssessmentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
