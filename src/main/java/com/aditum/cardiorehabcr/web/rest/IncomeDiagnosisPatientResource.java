package com.aditum.cardiorehabcr.web.rest;

import com.aditum.cardiorehabcr.service.IncomeDiagnosisPatientService;
import com.aditum.cardiorehabcr.service.impl.IncomeDiagnosisPatientServiceImpl;
import com.aditum.cardiorehabcr.web.rest.errors.BadRequestAlertException;
import com.aditum.cardiorehabcr.service.dto.IncomeDiagnosisPatientDTO;

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
 * REST controller for managing {@link com.aditum.cardiorehabcr.domain.IncomeDiagnosisPatient}.
 */
@RestController
@RequestMapping("/api")
public class IncomeDiagnosisPatientResource {

    private final Logger log = LoggerFactory.getLogger(IncomeDiagnosisPatientResource.class);

    private static final String ENTITY_NAME = "incomeDiagnosisPatient";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final IncomeDiagnosisPatientServiceImpl incomeDiagnosisPatientService;

    public IncomeDiagnosisPatientResource(IncomeDiagnosisPatientServiceImpl incomeDiagnosisPatientService) {
        this.incomeDiagnosisPatientService = incomeDiagnosisPatientService;
    }

    /**
     * {@code POST  /income-diagnosis-patients} : Create a new incomeDiagnosisPatient.
     *
     * @param incomeDiagnosisPatientDTO the incomeDiagnosisPatientDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new incomeDiagnosisPatientDTO, or with status {@code 400 (Bad Request)} if the incomeDiagnosisPatient has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/income-diagnosis-patients")
    public ResponseEntity<IncomeDiagnosisPatientDTO> createIncomeDiagnosisPatient(@Valid @RequestBody IncomeDiagnosisPatientDTO incomeDiagnosisPatientDTO) throws URISyntaxException {
        log.debug("REST request to save IncomeDiagnosisPatient : {}", incomeDiagnosisPatientDTO);
        if (incomeDiagnosisPatientDTO.getId() != null) {
            throw new BadRequestAlertException("A new incomeDiagnosisPatient cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IncomeDiagnosisPatientDTO result = incomeDiagnosisPatientService.save(incomeDiagnosisPatientDTO);
        return ResponseEntity.created(new URI("/api/income-diagnosis-patients/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /income-diagnosis-patients} : Updates an existing incomeDiagnosisPatient.
     *
     * @param incomeDiagnosisPatientDTO the incomeDiagnosisPatientDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated incomeDiagnosisPatientDTO,
     * or with status {@code 400 (Bad Request)} if the incomeDiagnosisPatientDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the incomeDiagnosisPatientDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/income-diagnosis-patients")
    public ResponseEntity<IncomeDiagnosisPatientDTO> updateIncomeDiagnosisPatient(@Valid @RequestBody IncomeDiagnosisPatientDTO incomeDiagnosisPatientDTO) throws URISyntaxException {
        log.debug("REST request to update IncomeDiagnosisPatient : {}", incomeDiagnosisPatientDTO);
        if (incomeDiagnosisPatientDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        IncomeDiagnosisPatientDTO result = incomeDiagnosisPatientService.save(incomeDiagnosisPatientDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, incomeDiagnosisPatientDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /income-diagnosis-patients} : get all the incomeDiagnosisPatients.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of incomeDiagnosisPatients in body.
     */
    @GetMapping("/income-diagnosis-patients")
    public ResponseEntity<List<IncomeDiagnosisPatientDTO>> getAllIncomeDiagnosisPatients(Pageable pageable) {
        log.debug("REST request to get a page of IncomeDiagnosisPatients");
        Page<IncomeDiagnosisPatientDTO> page = incomeDiagnosisPatientService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /income-diagnosis-patients/:id} : get the "id" incomeDiagnosisPatient.
     *
     * @param id the id of the incomeDiagnosisPatientDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the incomeDiagnosisPatientDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/income-diagnosis-patients/{id}")
    public ResponseEntity<IncomeDiagnosisPatientDTO> getIncomeDiagnosisPatient(@PathVariable Long id) {
        log.debug("REST request to get IncomeDiagnosisPatient : {}", id);
        Optional<IncomeDiagnosisPatientDTO> incomeDiagnosisPatientDTO = incomeDiagnosisPatientService.findOne(id);
        return ResponseUtil.wrapOrNotFound(incomeDiagnosisPatientDTO);
    }

    @GetMapping("/income-diagnosis-patients/by-asessment/")
    public ResponseEntity<List<IncomeDiagnosisPatientDTO>> getAllComorbiditiesPatients(Long id) {
        log.debug("REST request to get a page of ComorbiditiesPatients");
        List<IncomeDiagnosisPatientDTO> list = incomeDiagnosisPatientService.findAllByInitialAsessment(id);
        return ResponseEntity.ok().body(list);
    }
    /**
     * {@code DELETE  /income-diagnosis-patients/:id} : delete the "id" incomeDiagnosisPatient.
     *
     * @param id the id of the incomeDiagnosisPatientDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/income-diagnosis-patients/{id}")
    public ResponseEntity<Void> deleteIncomeDiagnosisPatient(@PathVariable Long id) {
        log.debug("REST request to delete IncomeDiagnosisPatient : {}", id);
        incomeDiagnosisPatientService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
