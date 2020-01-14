package com.aditum.cardiorehabcr.web.rest;

import com.aditum.cardiorehabcr.service.DepressiveSymptomService;
import com.aditum.cardiorehabcr.web.rest.errors.BadRequestAlertException;
import com.aditum.cardiorehabcr.service.dto.DepressiveSymptomDTO;

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
 * REST controller for managing {@link com.aditum.cardiorehabcr.domain.DepressiveSymptom}.
 */
@RestController
@RequestMapping("/api")
public class DepressiveSymptomResource {

    private final Logger log = LoggerFactory.getLogger(DepressiveSymptomResource.class);

    private static final String ENTITY_NAME = "depressiveSymptom";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DepressiveSymptomService depressiveSymptomService;

    public DepressiveSymptomResource(DepressiveSymptomService depressiveSymptomService) {
        this.depressiveSymptomService = depressiveSymptomService;
    }

    /**
     * {@code POST  /depressive-symptoms} : Create a new depressiveSymptom.
     *
     * @param depressiveSymptomDTO the depressiveSymptomDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new depressiveSymptomDTO, or with status {@code 400 (Bad Request)} if the depressiveSymptom has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/depressive-symptoms")
    public ResponseEntity<DepressiveSymptomDTO> createDepressiveSymptom(@Valid @RequestBody DepressiveSymptomDTO depressiveSymptomDTO) throws URISyntaxException {
        log.debug("REST request to save DepressiveSymptom : {}", depressiveSymptomDTO);
        if (depressiveSymptomDTO.getId() != null) {
            throw new BadRequestAlertException("A new depressiveSymptom cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DepressiveSymptomDTO result = depressiveSymptomService.save(depressiveSymptomDTO);
        return ResponseEntity.created(new URI("/api/depressive-symptoms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /depressive-symptoms} : Updates an existing depressiveSymptom.
     *
     * @param depressiveSymptomDTO the depressiveSymptomDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated depressiveSymptomDTO,
     * or with status {@code 400 (Bad Request)} if the depressiveSymptomDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the depressiveSymptomDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/depressive-symptoms")
    public ResponseEntity<DepressiveSymptomDTO> updateDepressiveSymptom(@Valid @RequestBody DepressiveSymptomDTO depressiveSymptomDTO) throws URISyntaxException {
        log.debug("REST request to update DepressiveSymptom : {}", depressiveSymptomDTO);
        if (depressiveSymptomDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DepressiveSymptomDTO result = depressiveSymptomService.save(depressiveSymptomDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, depressiveSymptomDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /depressive-symptoms} : get all the depressiveSymptoms.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of depressiveSymptoms in body.
     */
    @GetMapping("/depressive-symptoms")
    public ResponseEntity<List<DepressiveSymptomDTO>> getAllDepressiveSymptoms(Pageable pageable) {
        log.debug("REST request to get a page of DepressiveSymptoms");
        Page<DepressiveSymptomDTO> page = depressiveSymptomService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /depressive-symptoms/:id} : get the "id" depressiveSymptom.
     *
     * @param id the id of the depressiveSymptomDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the depressiveSymptomDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/depressive-symptoms/{id}")
    public ResponseEntity<DepressiveSymptomDTO> getDepressiveSymptom(@PathVariable Long id) {
        log.debug("REST request to get DepressiveSymptom : {}", id);
        Optional<DepressiveSymptomDTO> depressiveSymptomDTO = depressiveSymptomService.findOne(id);
        return ResponseUtil.wrapOrNotFound(depressiveSymptomDTO);
    }

    /**
     * {@code DELETE  /depressive-symptoms/:id} : delete the "id" depressiveSymptom.
     *
     * @param id the id of the depressiveSymptomDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/depressive-symptoms/{id}")
    public ResponseEntity<Void> deleteDepressiveSymptom(@PathVariable Long id) {
        log.debug("REST request to delete DepressiveSymptom : {}", id);
        depressiveSymptomService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
