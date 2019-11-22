package com.aditum.cardiorehabcr.web.rest;

import com.aditum.cardiorehabcr.service.NonSpecificPainService;
import com.aditum.cardiorehabcr.service.impl.NonSpecificPainServiceImpl;
import com.aditum.cardiorehabcr.web.rest.errors.BadRequestAlertException;
import com.aditum.cardiorehabcr.service.dto.NonSpecificPainDTO;

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
 * REST controller for managing {@link com.aditum.cardiorehabcr.domain.NonSpecificPain}.
 */
@RestController
@RequestMapping("/api")
public class NonSpecificPainResource {

    private final Logger log = LoggerFactory.getLogger(NonSpecificPainResource.class);

    private static final String ENTITY_NAME = "nonSpecificPain";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NonSpecificPainServiceImpl nonSpecificPainService;

    public NonSpecificPainResource(NonSpecificPainServiceImpl nonSpecificPainService) {
        this.nonSpecificPainService = nonSpecificPainService;
    }

    /**
     * {@code POST  /non-specific-pains} : Create a new nonSpecificPain.
     *
     * @param nonSpecificPainDTO the nonSpecificPainDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nonSpecificPainDTO, or with status {@code 400 (Bad Request)} if the nonSpecificPain has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/non-specific-pains")
    public ResponseEntity<NonSpecificPainDTO> createNonSpecificPain(@Valid @RequestBody NonSpecificPainDTO nonSpecificPainDTO) throws URISyntaxException {
        log.debug("REST request to save NonSpecificPain : {}", nonSpecificPainDTO);
        if (nonSpecificPainDTO.getId() != null) {
            throw new BadRequestAlertException("A new nonSpecificPain cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NonSpecificPainDTO result = nonSpecificPainService.save(nonSpecificPainDTO);
        return ResponseEntity.created(new URI("/api/non-specific-pains/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /non-specific-pains} : Updates an existing nonSpecificPain.
     *
     * @param nonSpecificPainDTO the nonSpecificPainDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nonSpecificPainDTO,
     * or with status {@code 400 (Bad Request)} if the nonSpecificPainDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nonSpecificPainDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/non-specific-pains")
    public ResponseEntity<NonSpecificPainDTO> updateNonSpecificPain(@Valid @RequestBody NonSpecificPainDTO nonSpecificPainDTO) throws URISyntaxException {
        log.debug("REST request to update NonSpecificPain : {}", nonSpecificPainDTO);
        if (nonSpecificPainDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NonSpecificPainDTO result = nonSpecificPainService.save(nonSpecificPainDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nonSpecificPainDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /non-specific-pains} : get all the nonSpecificPains.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nonSpecificPains in body.
     */
    @GetMapping("/non-specific-pains")
    public ResponseEntity<List<NonSpecificPainDTO>> getAllNonSpecificPains(Pageable pageable, Long rehabilitationId) {
        log.debug("REST request to get a page of NonSpecificPains");
        Page<NonSpecificPainDTO> page = nonSpecificPainService.findAll(pageable,rehabilitationId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /non-specific-pains/:id} : get the "id" nonSpecificPain.
     *
     * @param id the id of the nonSpecificPainDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nonSpecificPainDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/non-specific-pains/{id}")
    public ResponseEntity<NonSpecificPainDTO> getNonSpecificPain(@PathVariable Long id) {
        log.debug("REST request to get NonSpecificPain : {}", id);
        Optional<NonSpecificPainDTO> nonSpecificPainDTO = nonSpecificPainService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nonSpecificPainDTO);
    }

    /**
     * {@code DELETE  /non-specific-pains/:id} : delete the "id" nonSpecificPain.
     *
     * @param id the id of the nonSpecificPainDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/non-specific-pains/{id}")
    public ResponseEntity<Void> deleteNonSpecificPain(@PathVariable Long id) {
        log.debug("REST request to delete NonSpecificPain : {}", id);
        nonSpecificPainService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
