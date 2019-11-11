package com.aditum.cardiorehabcr.web.rest;

import com.aditum.cardiorehabcr.service.ComorbiditieService;
import com.aditum.cardiorehabcr.web.rest.errors.BadRequestAlertException;
import com.aditum.cardiorehabcr.service.dto.ComorbiditieDTO;

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
 * REST controller for managing {@link com.aditum.cardiorehabcr.domain.Comorbiditie}.
 */
@RestController
@RequestMapping("/api")
public class ComorbiditieResource {

    private final Logger log = LoggerFactory.getLogger(ComorbiditieResource.class);

    private static final String ENTITY_NAME = "comorbiditie";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ComorbiditieService comorbiditieService;

    public ComorbiditieResource(ComorbiditieService comorbiditieService) {
        this.comorbiditieService = comorbiditieService;
    }

    /**
     * {@code POST  /comorbidities} : Create a new comorbiditie.
     *
     * @param comorbiditieDTO the comorbiditieDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new comorbiditieDTO, or with status {@code 400 (Bad Request)} if the comorbiditie has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/comorbidities")
    public ResponseEntity<ComorbiditieDTO> createComorbiditie(@Valid @RequestBody ComorbiditieDTO comorbiditieDTO) throws URISyntaxException {
        log.debug("REST request to save Comorbiditie : {}", comorbiditieDTO);
        if (comorbiditieDTO.getId() != null) {
            throw new BadRequestAlertException("A new comorbiditie cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ComorbiditieDTO result = comorbiditieService.save(comorbiditieDTO);
        return ResponseEntity.created(new URI("/api/comorbidities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /comorbidities} : Updates an existing comorbiditie.
     *
     * @param comorbiditieDTO the comorbiditieDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated comorbiditieDTO,
     * or with status {@code 400 (Bad Request)} if the comorbiditieDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the comorbiditieDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/comorbidities")
    public ResponseEntity<ComorbiditieDTO> updateComorbiditie(@Valid @RequestBody ComorbiditieDTO comorbiditieDTO) throws URISyntaxException {
        log.debug("REST request to update Comorbiditie : {}", comorbiditieDTO);
        if (comorbiditieDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ComorbiditieDTO result = comorbiditieService.save(comorbiditieDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, comorbiditieDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /comorbidities} : get all the comorbidities.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of comorbidities in body.
     */
    @GetMapping("/comorbidities")
    public ResponseEntity<List<ComorbiditieDTO>> getAllComorbidities(Pageable pageable) {
        log.debug("REST request to get a page of Comorbidities");
        Page<ComorbiditieDTO> page = comorbiditieService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /comorbidities/:id} : get the "id" comorbiditie.
     *
     * @param id the id of the comorbiditieDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the comorbiditieDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/comorbidities/{id}")
    public ResponseEntity<ComorbiditieDTO> getComorbiditie(@PathVariable Long id) {
        log.debug("REST request to get Comorbiditie : {}", id);
        Optional<ComorbiditieDTO> comorbiditieDTO = comorbiditieService.findOne(id);
        return ResponseUtil.wrapOrNotFound(comorbiditieDTO);
    }

    /**
     * {@code DELETE  /comorbidities/:id} : delete the "id" comorbiditie.
     *
     * @param id the id of the comorbiditieDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/comorbidities/{id}")
    public ResponseEntity<Void> deleteComorbiditie(@PathVariable Long id) {
        log.debug("REST request to delete Comorbiditie : {}", id);
        comorbiditieService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
