package com.aditum.cardiorehabcr.web.rest;

import com.aditum.cardiorehabcr.CardioRehabCrApp;
import com.aditum.cardiorehabcr.domain.IncomeDiagnosisPatient;
import com.aditum.cardiorehabcr.repository.IncomeDiagnosisPatientRepository;
import com.aditum.cardiorehabcr.service.IncomeDiagnosisPatientService;
import com.aditum.cardiorehabcr.service.dto.IncomeDiagnosisPatientDTO;
import com.aditum.cardiorehabcr.service.impl.IncomeDiagnosisPatientServiceImpl;
import com.aditum.cardiorehabcr.service.mapper.IncomeDiagnosisPatientMapper;
import com.aditum.cardiorehabcr.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.aditum.cardiorehabcr.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link IncomeDiagnosisPatientResource} REST controller.
 */
@SpringBootTest(classes = CardioRehabCrApp.class)
public class IncomeDiagnosisPatientResourceIT {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Long DEFAULT_INCOME_DIAGNOSIS_ID = 1L;
    private static final Long UPDATED_INCOME_DIAGNOSIS_ID = 2L;

    private static final Boolean DEFAULT_EXIST = false;
    private static final Boolean UPDATED_EXIST = true;

    @Autowired
    private IncomeDiagnosisPatientRepository incomeDiagnosisPatientRepository;

    @Autowired
    private IncomeDiagnosisPatientMapper incomeDiagnosisPatientMapper;

    @Autowired
    private IncomeDiagnosisPatientServiceImpl incomeDiagnosisPatientService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restIncomeDiagnosisPatientMockMvc;

    private IncomeDiagnosisPatient incomeDiagnosisPatient;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final IncomeDiagnosisPatientResource incomeDiagnosisPatientResource = new IncomeDiagnosisPatientResource(incomeDiagnosisPatientService);
        this.restIncomeDiagnosisPatientMockMvc = MockMvcBuilders.standaloneSetup(incomeDiagnosisPatientResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static IncomeDiagnosisPatient createEntity(EntityManager em) {
        IncomeDiagnosisPatient incomeDiagnosisPatient = new IncomeDiagnosisPatient()
            .description(DEFAULT_DESCRIPTION)
            .incomeDiagnosisId(DEFAULT_INCOME_DIAGNOSIS_ID)
            .exist(DEFAULT_EXIST);
        return incomeDiagnosisPatient;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static IncomeDiagnosisPatient createUpdatedEntity(EntityManager em) {
        IncomeDiagnosisPatient incomeDiagnosisPatient = new IncomeDiagnosisPatient()
            .description(UPDATED_DESCRIPTION)
            .incomeDiagnosisId(UPDATED_INCOME_DIAGNOSIS_ID)
            .exist(UPDATED_EXIST);
        return incomeDiagnosisPatient;
    }

    @BeforeEach
    public void initTest() {
        incomeDiagnosisPatient = createEntity(em);
    }

    @Test
    @Transactional
    public void createIncomeDiagnosisPatient() throws Exception {
        int databaseSizeBeforeCreate = incomeDiagnosisPatientRepository.findAll().size();

        // Create the IncomeDiagnosisPatient
        IncomeDiagnosisPatientDTO incomeDiagnosisPatientDTO = incomeDiagnosisPatientMapper.toDto(incomeDiagnosisPatient);
        restIncomeDiagnosisPatientMockMvc.perform(post("/api/income-diagnosis-patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(incomeDiagnosisPatientDTO)))
            .andExpect(status().isCreated());

        // Validate the IncomeDiagnosisPatient in the database
        List<IncomeDiagnosisPatient> incomeDiagnosisPatientList = incomeDiagnosisPatientRepository.findAll();
        assertThat(incomeDiagnosisPatientList).hasSize(databaseSizeBeforeCreate + 1);
        IncomeDiagnosisPatient testIncomeDiagnosisPatient = incomeDiagnosisPatientList.get(incomeDiagnosisPatientList.size() - 1);
        assertThat(testIncomeDiagnosisPatient.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testIncomeDiagnosisPatient.getIncomeDiagnosisId()).isEqualTo(DEFAULT_INCOME_DIAGNOSIS_ID);
        assertThat(testIncomeDiagnosisPatient.isExist()).isEqualTo(DEFAULT_EXIST);
    }

    @Test
    @Transactional
    public void createIncomeDiagnosisPatientWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = incomeDiagnosisPatientRepository.findAll().size();

        // Create the IncomeDiagnosisPatient with an existing ID
        incomeDiagnosisPatient.setId(1L);
        IncomeDiagnosisPatientDTO incomeDiagnosisPatientDTO = incomeDiagnosisPatientMapper.toDto(incomeDiagnosisPatient);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIncomeDiagnosisPatientMockMvc.perform(post("/api/income-diagnosis-patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(incomeDiagnosisPatientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the IncomeDiagnosisPatient in the database
        List<IncomeDiagnosisPatient> incomeDiagnosisPatientList = incomeDiagnosisPatientRepository.findAll();
        assertThat(incomeDiagnosisPatientList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIncomeDiagnosisIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = incomeDiagnosisPatientRepository.findAll().size();
        // set the field null
        incomeDiagnosisPatient.setIncomeDiagnosisId(null);

        // Create the IncomeDiagnosisPatient, which fails.
        IncomeDiagnosisPatientDTO incomeDiagnosisPatientDTO = incomeDiagnosisPatientMapper.toDto(incomeDiagnosisPatient);

        restIncomeDiagnosisPatientMockMvc.perform(post("/api/income-diagnosis-patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(incomeDiagnosisPatientDTO)))
            .andExpect(status().isBadRequest());

        List<IncomeDiagnosisPatient> incomeDiagnosisPatientList = incomeDiagnosisPatientRepository.findAll();
        assertThat(incomeDiagnosisPatientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExistIsRequired() throws Exception {
        int databaseSizeBeforeTest = incomeDiagnosisPatientRepository.findAll().size();
        // set the field null
        incomeDiagnosisPatient.setExist(null);

        // Create the IncomeDiagnosisPatient, which fails.
        IncomeDiagnosisPatientDTO incomeDiagnosisPatientDTO = incomeDiagnosisPatientMapper.toDto(incomeDiagnosisPatient);

        restIncomeDiagnosisPatientMockMvc.perform(post("/api/income-diagnosis-patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(incomeDiagnosisPatientDTO)))
            .andExpect(status().isBadRequest());

        List<IncomeDiagnosisPatient> incomeDiagnosisPatientList = incomeDiagnosisPatientRepository.findAll();
        assertThat(incomeDiagnosisPatientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllIncomeDiagnosisPatients() throws Exception {
        // Initialize the database
        incomeDiagnosisPatientRepository.saveAndFlush(incomeDiagnosisPatient);

        // Get all the incomeDiagnosisPatientList
        restIncomeDiagnosisPatientMockMvc.perform(get("/api/income-diagnosis-patients?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(incomeDiagnosisPatient.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].incomeDiagnosisId").value(hasItem(DEFAULT_INCOME_DIAGNOSIS_ID.intValue())))
            .andExpect(jsonPath("$.[*].exist").value(hasItem(DEFAULT_EXIST.booleanValue())));
    }

    @Test
    @Transactional
    public void getIncomeDiagnosisPatient() throws Exception {
        // Initialize the database
        incomeDiagnosisPatientRepository.saveAndFlush(incomeDiagnosisPatient);

        // Get the incomeDiagnosisPatient
        restIncomeDiagnosisPatientMockMvc.perform(get("/api/income-diagnosis-patients/{id}", incomeDiagnosisPatient.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(incomeDiagnosisPatient.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.incomeDiagnosisId").value(DEFAULT_INCOME_DIAGNOSIS_ID.intValue()))
            .andExpect(jsonPath("$.exist").value(DEFAULT_EXIST.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingIncomeDiagnosisPatient() throws Exception {
        // Get the incomeDiagnosisPatient
        restIncomeDiagnosisPatientMockMvc.perform(get("/api/income-diagnosis-patients/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIncomeDiagnosisPatient() throws Exception {
        // Initialize the database
        incomeDiagnosisPatientRepository.saveAndFlush(incomeDiagnosisPatient);

        int databaseSizeBeforeUpdate = incomeDiagnosisPatientRepository.findAll().size();

        // Update the incomeDiagnosisPatient
        IncomeDiagnosisPatient updatedIncomeDiagnosisPatient = incomeDiagnosisPatientRepository.findById(incomeDiagnosisPatient.getId()).get();
        // Disconnect from session so that the updates on updatedIncomeDiagnosisPatient are not directly saved in db
        em.detach(updatedIncomeDiagnosisPatient);
        updatedIncomeDiagnosisPatient
            .description(UPDATED_DESCRIPTION)
            .incomeDiagnosisId(UPDATED_INCOME_DIAGNOSIS_ID)
            .exist(UPDATED_EXIST);
        IncomeDiagnosisPatientDTO incomeDiagnosisPatientDTO = incomeDiagnosisPatientMapper.toDto(updatedIncomeDiagnosisPatient);

        restIncomeDiagnosisPatientMockMvc.perform(put("/api/income-diagnosis-patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(incomeDiagnosisPatientDTO)))
            .andExpect(status().isOk());

        // Validate the IncomeDiagnosisPatient in the database
        List<IncomeDiagnosisPatient> incomeDiagnosisPatientList = incomeDiagnosisPatientRepository.findAll();
        assertThat(incomeDiagnosisPatientList).hasSize(databaseSizeBeforeUpdate);
        IncomeDiagnosisPatient testIncomeDiagnosisPatient = incomeDiagnosisPatientList.get(incomeDiagnosisPatientList.size() - 1);
        assertThat(testIncomeDiagnosisPatient.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testIncomeDiagnosisPatient.getIncomeDiagnosisId()).isEqualTo(UPDATED_INCOME_DIAGNOSIS_ID);
        assertThat(testIncomeDiagnosisPatient.isExist()).isEqualTo(UPDATED_EXIST);
    }

    @Test
    @Transactional
    public void updateNonExistingIncomeDiagnosisPatient() throws Exception {
        int databaseSizeBeforeUpdate = incomeDiagnosisPatientRepository.findAll().size();

        // Create the IncomeDiagnosisPatient
        IncomeDiagnosisPatientDTO incomeDiagnosisPatientDTO = incomeDiagnosisPatientMapper.toDto(incomeDiagnosisPatient);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIncomeDiagnosisPatientMockMvc.perform(put("/api/income-diagnosis-patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(incomeDiagnosisPatientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the IncomeDiagnosisPatient in the database
        List<IncomeDiagnosisPatient> incomeDiagnosisPatientList = incomeDiagnosisPatientRepository.findAll();
        assertThat(incomeDiagnosisPatientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteIncomeDiagnosisPatient() throws Exception {
        // Initialize the database
        incomeDiagnosisPatientRepository.saveAndFlush(incomeDiagnosisPatient);

        int databaseSizeBeforeDelete = incomeDiagnosisPatientRepository.findAll().size();

        // Delete the incomeDiagnosisPatient
        restIncomeDiagnosisPatientMockMvc.perform(delete("/api/income-diagnosis-patients/{id}", incomeDiagnosisPatient.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<IncomeDiagnosisPatient> incomeDiagnosisPatientList = incomeDiagnosisPatientRepository.findAll();
        assertThat(incomeDiagnosisPatientList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
