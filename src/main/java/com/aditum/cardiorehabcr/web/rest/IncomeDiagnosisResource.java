package com.aditum.cardiorehabcr.web.rest;

import com.aditum.cardiorehabcr.service.IncomeDiagnosisService;
import com.aditum.cardiorehabcr.service.impl.IncomeDiagnosisServiceImpl;
import com.aditum.cardiorehabcr.web.rest.errors.BadRequestAlertException;
import com.aditum.cardiorehabcr.service.dto.IncomeDiagnosisDTO;

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
 * REST controller for managing {@link com.aditum.cardiorehabcr.domain.IncomeDiagnosis}.
 */
@RestController
@RequestMapping("/api")
public class IncomeDiagnosisResource {

    private final Logger log = LoggerFactory.getLogger(IncomeDiagnosisResource.class);

    private static final String ENTITY_NAME = "incomeDiagnosis";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final IncomeDiagnosisServiceImpl incomeDiagnosisService;


    public IncomeDiagnosisResource(IncomeDiagnosisServiceImpl incomeDiagnosisService) {
        this.incomeDiagnosisService = incomeDiagnosisService;
    }

    /**
     * {@code POST  /income-diagnoses} : Create a new incomeDiagnosis.
     *
     * @param incomeDiagnosisDTO the incomeDiagnosisDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new incomeDiagnosisDTO, or with status {@code 400 (Bad Request)} if the incomeDiagnosis has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/income-diagnoses")
    public ResponseEntity<IncomeDiagnosisDTO> createIncomeDiagnosis(@Valid @RequestBody IncomeDiagnosisDTO incomeDiagnosisDTO) throws URISyntaxException {
        log.debug("REST request to save IncomeDiagnosis : {}", incomeDiagnosisDTO);
        if (incomeDiagnosisDTO.getId() != null) {
            throw new BadRequestAlertException("A new incomeDiagnosis cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IncomeDiagnosisDTO result = incomeDiagnosisService.save(incomeDiagnosisDTO);
        return ResponseEntity.created(new URI("/api/income-diagnoses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /income-diagnoses} : Updates an existing incomeDiagnosis.
     *
     * @param incomeDiagnosisDTO the incomeDiagnosisDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated incomeDiagnosisDTO,
     * or with status {@code 400 (Bad Request)} if the incomeDiagnosisDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the incomeDiagnosisDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/income-diagnoses")
    public ResponseEntity<IncomeDiagnosisDTO> updateIncomeDiagnosis(@Valid @RequestBody IncomeDiagnosisDTO incomeDiagnosisDTO) throws URISyntaxException {
        log.debug("REST request to update IncomeDiagnosis : {}", incomeDiagnosisDTO);
        if (incomeDiagnosisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        IncomeDiagnosisDTO result = incomeDiagnosisService.save(incomeDiagnosisDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, incomeDiagnosisDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /income-diagnoses} : get all the incomeDiagnoses.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of incomeDiagnoses in body.
     */
    @GetMapping("/income-diagnoses")
    public ResponseEntity<List<IncomeDiagnosisDTO>> getAllIncomeDiagnoses(Pageable pageable, Long rehabilitationId) {
        log.debug("REST request to get a page of IncomeDiagnoses");
        Page<IncomeDiagnosisDTO> page = incomeDiagnosisService.findAll(pageable,rehabilitationId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /income-diagnoses/:id} : get the "id" incomeDiagnosis.
     *
     * @param id the id of the incomeDiagnosisDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the incomeDiagnosisDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/income-diagnoses/{id}")
    public ResponseEntity<IncomeDiagnosisDTO> getIncomeDiagnosis(@PathVariable Long id) {
        log.debug("REST request to get IncomeDiagnosis : {}", id);
        Optional<IncomeDiagnosisDTO> incomeDiagnosisDTO = incomeDiagnosisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(incomeDiagnosisDTO);
    }

    /**
     * {@code DELETE  /income-diagnoses/:id} : delete the "id" incomeDiagnosis.
     *
     * @param id the id of the incomeDiagnosisDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/income-diagnoses/{id}")
    public ResponseEntity<Void> deleteIncomeDiagnosis(@PathVariable Long id) {
        log.debug("REST request to delete IncomeDiagnosis : {}", id);
        incomeDiagnosisService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
