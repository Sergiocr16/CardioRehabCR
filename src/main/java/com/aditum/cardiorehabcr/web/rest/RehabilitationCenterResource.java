package com.aditum.cardiorehabcr.web.rest;

import com.aditum.cardiorehabcr.service.RehabilitationCenterService;
import com.aditum.cardiorehabcr.web.rest.errors.BadRequestAlertException;
import com.aditum.cardiorehabcr.service.dto.RehabilitationCenterDTO;

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
 * REST controller for managing {@link com.aditum.cardiorehabcr.domain.RehabilitationCenter}.
 */
@RestController
@RequestMapping("/api")
public class RehabilitationCenterResource {

    private final Logger log = LoggerFactory.getLogger(RehabilitationCenterResource.class);

    private static final String ENTITY_NAME = "rehabilitationCenter";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RehabilitationCenterService rehabilitationCenterService;

    public RehabilitationCenterResource(RehabilitationCenterService rehabilitationCenterService) {
        this.rehabilitationCenterService = rehabilitationCenterService;
    }

    /**
     * {@code POST  /rehabilitation-centers} : Create a new rehabilitationCenter.
     *
     * @param rehabilitationCenterDTO the rehabilitationCenterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rehabilitationCenterDTO, or with status {@code 400 (Bad Request)} if the rehabilitationCenter has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rehabilitation-centers")
    public ResponseEntity<RehabilitationCenterDTO> createRehabilitationCenter(@Valid @RequestBody RehabilitationCenterDTO rehabilitationCenterDTO) throws URISyntaxException {
        log.debug("REST request to save RehabilitationCenter : {}", rehabilitationCenterDTO);
        if (rehabilitationCenterDTO.getId() != null) {
            throw new BadRequestAlertException("A new rehabilitationCenter cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RehabilitationCenterDTO result = rehabilitationCenterService.save(rehabilitationCenterDTO);
        return ResponseEntity.created(new URI("/api/rehabilitation-centers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /rehabilitation-centers} : Updates an existing rehabilitationCenter.
     *
     * @param rehabilitationCenterDTO the rehabilitationCenterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rehabilitationCenterDTO,
     * or with status {@code 400 (Bad Request)} if the rehabilitationCenterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rehabilitationCenterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rehabilitation-centers")
    public ResponseEntity<RehabilitationCenterDTO> updateRehabilitationCenter(@Valid @RequestBody RehabilitationCenterDTO rehabilitationCenterDTO) throws URISyntaxException {
        log.debug("REST request to update RehabilitationCenter : {}", rehabilitationCenterDTO);
        if (rehabilitationCenterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RehabilitationCenterDTO result = rehabilitationCenterService.save(rehabilitationCenterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rehabilitationCenterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /rehabilitation-centers} : get all the rehabilitationCenters.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rehabilitationCenters in body.
     */
    @GetMapping("/rehabilitation-centers")
    public ResponseEntity<List<RehabilitationCenterDTO>> getAllRehabilitationCenters(Pageable pageable) {
        log.debug("REST request to get a page of RehabilitationCenters");
        Page<RehabilitationCenterDTO> page = rehabilitationCenterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /rehabilitation-centers/:id} : get the "id" rehabilitationCenter.
     *
     * @param id the id of the rehabilitationCenterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rehabilitationCenterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rehabilitation-centers/{id}")
    public ResponseEntity<RehabilitationCenterDTO> getRehabilitationCenter(@PathVariable Long id) {
        log.debug("REST request to get RehabilitationCenter : {}", id);
        Optional<RehabilitationCenterDTO> rehabilitationCenterDTO = rehabilitationCenterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rehabilitationCenterDTO);
    }

    /**
     * {@code DELETE  /rehabilitation-centers/:id} : delete the "id" rehabilitationCenter.
     *
     * @param id the id of the rehabilitationCenterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rehabilitation-centers/{id}")
    public ResponseEntity<Void> deleteRehabilitationCenter(@PathVariable Long id) {
        log.debug("REST request to delete RehabilitationCenter : {}", id);
        rehabilitationCenterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
