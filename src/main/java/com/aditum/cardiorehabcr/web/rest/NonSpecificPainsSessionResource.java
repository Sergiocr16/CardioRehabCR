package com.aditum.cardiorehabcr.web.rest;

import com.aditum.cardiorehabcr.service.NonSpecificPainsSessionService;
import com.aditum.cardiorehabcr.service.impl.NonSpecificPainsSessionServiceImpl;
import com.aditum.cardiorehabcr.web.rest.errors.BadRequestAlertException;
import com.aditum.cardiorehabcr.service.dto.NonSpecificPainsSessionDTO;

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
 * REST controller for managing {@link com.aditum.cardiorehabcr.domain.NonSpecificPainsSession}.
 */
@RestController
@RequestMapping("/api")
public class NonSpecificPainsSessionResource {

    private final Logger log = LoggerFactory.getLogger(NonSpecificPainsSessionResource.class);

    private static final String ENTITY_NAME = "nonSpecificPainsSession";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NonSpecificPainsSessionServiceImpl nonSpecificPainsSessionService;

    public NonSpecificPainsSessionResource(NonSpecificPainsSessionServiceImpl nonSpecificPainsSessionService) {
        this.nonSpecificPainsSessionService = nonSpecificPainsSessionService;
    }

    /**
     * {@code POST  /non-specific-pains-sessions} : Create a new nonSpecificPainsSession.
     *
     * @param nonSpecificPainsSessionDTO the nonSpecificPainsSessionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nonSpecificPainsSessionDTO, or with status {@code 400 (Bad Request)} if the nonSpecificPainsSession has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/non-specific-pains-sessions")
    public ResponseEntity<NonSpecificPainsSessionDTO> createNonSpecificPainsSession(@Valid @RequestBody NonSpecificPainsSessionDTO nonSpecificPainsSessionDTO) throws URISyntaxException {
        log.debug("REST request to save NonSpecificPainsSession : {}", nonSpecificPainsSessionDTO);
        if (nonSpecificPainsSessionDTO.getId() != null) {
            throw new BadRequestAlertException("A new nonSpecificPainsSession cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NonSpecificPainsSessionDTO result = nonSpecificPainsSessionService.save(nonSpecificPainsSessionDTO);
        return ResponseEntity.created(new URI("/api/non-specific-pains-sessions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /non-specific-pains-sessions} : Updates an existing nonSpecificPainsSession.
     *
     * @param nonSpecificPainsSessionDTO the nonSpecificPainsSessionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nonSpecificPainsSessionDTO,
     * or with status {@code 400 (Bad Request)} if the nonSpecificPainsSessionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nonSpecificPainsSessionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/non-specific-pains-sessions")
    public ResponseEntity<NonSpecificPainsSessionDTO> updateNonSpecificPainsSession(@Valid @RequestBody NonSpecificPainsSessionDTO nonSpecificPainsSessionDTO) throws URISyntaxException {
        log.debug("REST request to update NonSpecificPainsSession : {}", nonSpecificPainsSessionDTO);
        if (nonSpecificPainsSessionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NonSpecificPainsSessionDTO result = nonSpecificPainsSessionService.save(nonSpecificPainsSessionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nonSpecificPainsSessionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /non-specific-pains-sessions} : get all the nonSpecificPainsSessions.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nonSpecificPainsSessions in body.
     */
    @GetMapping("/non-specific-pains-sessions")
    public ResponseEntity<List<NonSpecificPainsSessionDTO>> getAllNonSpecificPainsSessions(Pageable pageable) {
        log.debug("REST request to get a page of NonSpecificPainsSessions");
        Page<NonSpecificPainsSessionDTO> page = nonSpecificPainsSessionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/non-specific-pains-sessions/by-session")
    public ResponseEntity<List<NonSpecificPainsSessionDTO>> getAllNonSpecificPainsSessionsBySession(Pageable pageable,Long sessionId) {
        log.debug("REST request to get a page of NonSpecificPainsSessions");
        Page<NonSpecificPainsSessionDTO> page = nonSpecificPainsSessionService.findAllBySession(pageable,sessionId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /non-specific-pains-sessions/:id} : get the "id" nonSpecificPainsSession.
     *
     * @param id the id of the nonSpecificPainsSessionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nonSpecificPainsSessionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/non-specific-pains-sessions/{id}")
    public ResponseEntity<NonSpecificPainsSessionDTO> getNonSpecificPainsSession(@PathVariable Long id) {
        log.debug("REST request to get NonSpecificPainsSession : {}", id);
        Optional<NonSpecificPainsSessionDTO> nonSpecificPainsSessionDTO = nonSpecificPainsSessionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nonSpecificPainsSessionDTO);
    }

    /**
     * {@code DELETE  /non-specific-pains-sessions/:id} : delete the "id" nonSpecificPainsSession.
     *
     * @param id the id of the nonSpecificPainsSessionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/non-specific-pains-sessions/{id}")
    public ResponseEntity<Void> deleteNonSpecificPainsSession(@PathVariable Long id) {
        log.debug("REST request to delete NonSpecificPainsSession : {}", id);
        nonSpecificPainsSessionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
