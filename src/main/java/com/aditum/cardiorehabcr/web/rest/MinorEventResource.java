package com.aditum.cardiorehabcr.web.rest;

import com.aditum.cardiorehabcr.service.MinorEventService;
import com.aditum.cardiorehabcr.service.PanelDataService;
import com.aditum.cardiorehabcr.service.dto.MinorEventsSessionDTO;
import com.aditum.cardiorehabcr.service.impl.MinorEventServiceImpl;
import com.aditum.cardiorehabcr.web.rest.errors.BadRequestAlertException;
import com.aditum.cardiorehabcr.service.dto.MinorEventDTO;

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
 * REST controller for managing {@link com.aditum.cardiorehabcr.domain.MinorEvent}.
 */
@RestController
@RequestMapping("/api")
public class MinorEventResource {

    private final Logger log = LoggerFactory.getLogger(MinorEventResource.class);

    private static final String ENTITY_NAME = "minorEvent";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MinorEventServiceImpl minorEventService;

    private final PanelDataService panelDataService;

    public MinorEventResource(MinorEventServiceImpl minorEventService, PanelDataService panelDataService) {
        this.minorEventService = minorEventService;
        this.panelDataService = panelDataService;
    }

    /**
     * {@code POST  /minor-events} : Create a new minorEvent.
     *
     * @param minorEventDTO the minorEventDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new minorEventDTO, or with status {@code 400 (Bad Request)} if the minorEvent has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/minor-events")
    public ResponseEntity<MinorEventDTO> createMinorEvent(@Valid @RequestBody MinorEventDTO minorEventDTO) throws URISyntaxException {
        log.debug("REST request to save MinorEvent : {}", minorEventDTO);
        if (minorEventDTO.getId() != null) {
            throw new BadRequestAlertException("A new minorEvent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MinorEventDTO result = minorEventService.save(minorEventDTO);
        return ResponseEntity.created(new URI("/api/minor-events/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /minor-events} : Updates an existing minorEvent.
     *
     * @param minorEventDTO the minorEventDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated minorEventDTO,
     * or with status {@code 400 (Bad Request)} if the minorEventDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the minorEventDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/minor-events")
    public ResponseEntity<MinorEventDTO> updateMinorEvent(@Valid @RequestBody MinorEventDTO minorEventDTO) throws URISyntaxException {
        log.debug("REST request to update MinorEvent : {}", minorEventDTO);
        if (minorEventDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MinorEventDTO result = minorEventService.save(minorEventDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, minorEventDTO.getId().toString()))
            .body(result);
    }

    @GetMapping("/minor-events/graph")
    public ResponseEntity<List<MinorEventDTO>> getSessionsPerMinorEvent(Long groupId, Long rehabilitationId,Long minorEventId) {
        log.debug("REST request to get a page of MinorEvents");
        List<MinorEventDTO> result = this.panelDataService.distributionMinorEventPerSessions(groupId,rehabilitationId,minorEventId);
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), null);
        return ResponseEntity.ok().headers(null).body(result);
    }
    /**
     * {@code GET  /minor-events} : get all the minorEvents.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of minorEvents in body.
     */
    @GetMapping("/minor-events")
    public ResponseEntity<List<MinorEventDTO>> getAllMinorEvents(Pageable pageable, Long rehabilitationId) {
        log.debug("REST request to get a page of MinorEvents");
        Page<MinorEventDTO> page = minorEventService.findAll(pageable,rehabilitationId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /minor-events/:id} : get the "id" minorEvent.
     *
     * @param id the id of the minorEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the minorEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/minor-events/{id}")
    public ResponseEntity<MinorEventDTO> getMinorEvent(@PathVariable Long id) {
        log.debug("REST request to get MinorEvent : {}", id);
        Optional<MinorEventDTO> minorEventDTO = minorEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(minorEventDTO);
    }

    /**
     * {@code DELETE  /minor-events/:id} : delete the "id" minorEvent.
     *
     * @param id the id of the minorEventDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/minor-events/{id}")
    public ResponseEntity<Void> deleteMinorEvent(@PathVariable Long id) {
        log.debug("REST request to delete MinorEvent : {}", id);
        minorEventService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
