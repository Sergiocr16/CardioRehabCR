package com.aditum.cardiorehabcr.web.rest;

import com.aditum.cardiorehabcr.service.MayorEventsSessionService;
import com.aditum.cardiorehabcr.service.impl.MayorEventsSessionServiceImpl;
import com.aditum.cardiorehabcr.web.rest.errors.BadRequestAlertException;
import com.aditum.cardiorehabcr.service.dto.MayorEventsSessionDTO;

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
 * REST controller for managing {@link com.aditum.cardiorehabcr.domain.MayorEventsSession}.
 */
@RestController
@RequestMapping("/api")
public class MayorEventsSessionResource {

    private final Logger log = LoggerFactory.getLogger(MayorEventsSessionResource.class);

    private static final String ENTITY_NAME = "mayorEventsSession";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MayorEventsSessionServiceImpl mayorEventsSessionService;

    public MayorEventsSessionResource(MayorEventsSessionServiceImpl mayorEventsSessionService) {
        this.mayorEventsSessionService = mayorEventsSessionService;
    }

    /**
     * {@code POST  /mayor-events-sessions} : Create a new mayorEventsSession.
     *
     * @param mayorEventsSessionDTO the mayorEventsSessionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mayorEventsSessionDTO, or with status {@code 400 (Bad Request)} if the mayorEventsSession has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mayor-events-sessions")
    public ResponseEntity<MayorEventsSessionDTO> createMayorEventsSession(@Valid @RequestBody MayorEventsSessionDTO mayorEventsSessionDTO) throws URISyntaxException {
        log.debug("REST request to save MayorEventsSession : {}", mayorEventsSessionDTO);
        if (mayorEventsSessionDTO.getId() != null) {
            throw new BadRequestAlertException("A new mayorEventsSession cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MayorEventsSessionDTO result = mayorEventsSessionService.save(mayorEventsSessionDTO);
        return ResponseEntity.created(new URI("/api/mayor-events-sessions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mayor-events-sessions} : Updates an existing mayorEventsSession.
     *
     * @param mayorEventsSessionDTO the mayorEventsSessionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mayorEventsSessionDTO,
     * or with status {@code 400 (Bad Request)} if the mayorEventsSessionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mayorEventsSessionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mayor-events-sessions")
    public ResponseEntity<MayorEventsSessionDTO> updateMayorEventsSession(@Valid @RequestBody MayorEventsSessionDTO mayorEventsSessionDTO) throws URISyntaxException {
        log.debug("REST request to update MayorEventsSession : {}", mayorEventsSessionDTO);
        if (mayorEventsSessionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MayorEventsSessionDTO result = mayorEventsSessionService.save(mayorEventsSessionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mayorEventsSessionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mayor-events-sessions} : get all the mayorEventsSessions.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mayorEventsSessions in body.
     */
    @GetMapping("/mayor-events-sessions")
    public ResponseEntity<List<MayorEventsSessionDTO>> getAllMayorEventsSessions(Pageable pageable) {
        log.debug("REST request to get a page of MayorEventsSessions");
        Page<MayorEventsSessionDTO> page = mayorEventsSessionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/mayor-events-sessions/by-session")
    public ResponseEntity<List<MayorEventsSessionDTO>> getAllMayorEventsSessions(Pageable pageable,Long sessionId) {
        log.debug("REST request to get a page of MayorEventsSessions");
        Page<MayorEventsSessionDTO> page = mayorEventsSessionService.findAllBySession(pageable,sessionId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /mayor-events-sessions/:id} : get the "id" mayorEventsSession.
     *
     * @param id the id of the mayorEventsSessionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mayorEventsSessionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mayor-events-sessions/{id}")
    public ResponseEntity<MayorEventsSessionDTO> getMayorEventsSession(@PathVariable Long id) {
        log.debug("REST request to get MayorEventsSession : {}", id);
        Optional<MayorEventsSessionDTO> mayorEventsSessionDTO = mayorEventsSessionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mayorEventsSessionDTO);
    }

    /**
     * {@code DELETE  /mayor-events-sessions/:id} : delete the "id" mayorEventsSession.
     *
     * @param id the id of the mayorEventsSessionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mayor-events-sessions/{id}")
    public ResponseEntity<Void> deleteMayorEventsSession(@PathVariable Long id) {
        log.debug("REST request to delete MayorEventsSession : {}", id);
        mayorEventsSessionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
