package com.aditum.cardiorehabcr.web.rest;

import com.aditum.cardiorehabcr.CardioRehabCrApp;
import com.aditum.cardiorehabcr.domain.ComorbiditiesPatient;
import com.aditum.cardiorehabcr.repository.ComorbiditiesPatientRepository;
import com.aditum.cardiorehabcr.service.ComorbiditiesPatientService;
import com.aditum.cardiorehabcr.service.dto.ComorbiditiesPatientDTO;
import com.aditum.cardiorehabcr.service.impl.ComorbiditiesPatientServiceImpl;
import com.aditum.cardiorehabcr.service.mapper.ComorbiditiesPatientMapper;
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
 * Integration tests for the {@link ComorbiditiesPatientResource} REST controller.
 */
@SpringBootTest(classes = CardioRehabCrApp.class)
public class ComorbiditiesPatientResourceIT {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Long DEFAULT_COMORBIDITIET_ID = 1L;
    private static final Long UPDATED_COMORBIDITIET_ID = 2L;

    private static final Boolean DEFAULT_EXIST = false;
    private static final Boolean UPDATED_EXIST = true;

    @Autowired
    private ComorbiditiesPatientRepository comorbiditiesPatientRepository;

    @Autowired
    private ComorbiditiesPatientMapper comorbiditiesPatientMapper;

    @Autowired
    private ComorbiditiesPatientServiceImpl comorbiditiesPatientService;

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

    private MockMvc restComorbiditiesPatientMockMvc;

    private ComorbiditiesPatient comorbiditiesPatient;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ComorbiditiesPatientResource comorbiditiesPatientResource = new ComorbiditiesPatientResource(comorbiditiesPatientService);
        this.restComorbiditiesPatientMockMvc = MockMvcBuilders.standaloneSetup(comorbiditiesPatientResource)
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
    public static ComorbiditiesPatient createEntity(EntityManager em) {
        ComorbiditiesPatient comorbiditiesPatient = new ComorbiditiesPatient()
            .description(DEFAULT_DESCRIPTION)
            .comorbiditietId(DEFAULT_COMORBIDITIET_ID)
            .exist(DEFAULT_EXIST);
        return comorbiditiesPatient;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ComorbiditiesPatient createUpdatedEntity(EntityManager em) {
        ComorbiditiesPatient comorbiditiesPatient = new ComorbiditiesPatient()
            .description(UPDATED_DESCRIPTION)
            .comorbiditietId(UPDATED_COMORBIDITIET_ID)
            .exist(UPDATED_EXIST);
        return comorbiditiesPatient;
    }

    @BeforeEach
    public void initTest() {
        comorbiditiesPatient = createEntity(em);
    }

    @Test
    @Transactional
    public void createComorbiditiesPatient() throws Exception {
        int databaseSizeBeforeCreate = comorbiditiesPatientRepository.findAll().size();

        // Create the ComorbiditiesPatient
        ComorbiditiesPatientDTO comorbiditiesPatientDTO = comorbiditiesPatientMapper.toDto(comorbiditiesPatient);
        restComorbiditiesPatientMockMvc.perform(post("/api/comorbidities-patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(comorbiditiesPatientDTO)))
            .andExpect(status().isCreated());

        // Validate the ComorbiditiesPatient in the database
        List<ComorbiditiesPatient> comorbiditiesPatientList = comorbiditiesPatientRepository.findAll();
        assertThat(comorbiditiesPatientList).hasSize(databaseSizeBeforeCreate + 1);
        ComorbiditiesPatient testComorbiditiesPatient = comorbiditiesPatientList.get(comorbiditiesPatientList.size() - 1);
        assertThat(testComorbiditiesPatient.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testComorbiditiesPatient.getComorbiditietId()).isEqualTo(DEFAULT_COMORBIDITIET_ID);
        assertThat(testComorbiditiesPatient.isExist()).isEqualTo(DEFAULT_EXIST);
    }

    @Test
    @Transactional
    public void createComorbiditiesPatientWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = comorbiditiesPatientRepository.findAll().size();

        // Create the ComorbiditiesPatient with an existing ID
        comorbiditiesPatient.setId(1L);
        ComorbiditiesPatientDTO comorbiditiesPatientDTO = comorbiditiesPatientMapper.toDto(comorbiditiesPatient);

        // An entity with an existing ID cannot be created, so this API call must fail
        restComorbiditiesPatientMockMvc.perform(post("/api/comorbidities-patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(comorbiditiesPatientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ComorbiditiesPatient in the database
        List<ComorbiditiesPatient> comorbiditiesPatientList = comorbiditiesPatientRepository.findAll();
        assertThat(comorbiditiesPatientList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkComorbiditietIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = comorbiditiesPatientRepository.findAll().size();
        // set the field null
        comorbiditiesPatient.setComorbiditietId(null);

        // Create the ComorbiditiesPatient, which fails.
        ComorbiditiesPatientDTO comorbiditiesPatientDTO = comorbiditiesPatientMapper.toDto(comorbiditiesPatient);

        restComorbiditiesPatientMockMvc.perform(post("/api/comorbidities-patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(comorbiditiesPatientDTO)))
            .andExpect(status().isBadRequest());

        List<ComorbiditiesPatient> comorbiditiesPatientList = comorbiditiesPatientRepository.findAll();
        assertThat(comorbiditiesPatientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExistIsRequired() throws Exception {
        int databaseSizeBeforeTest = comorbiditiesPatientRepository.findAll().size();
        // set the field null
        comorbiditiesPatient.setExist(null);

        // Create the ComorbiditiesPatient, which fails.
        ComorbiditiesPatientDTO comorbiditiesPatientDTO = comorbiditiesPatientMapper.toDto(comorbiditiesPatient);

        restComorbiditiesPatientMockMvc.perform(post("/api/comorbidities-patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(comorbiditiesPatientDTO)))
            .andExpect(status().isBadRequest());

        List<ComorbiditiesPatient> comorbiditiesPatientList = comorbiditiesPatientRepository.findAll();
        assertThat(comorbiditiesPatientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllComorbiditiesPatients() throws Exception {
        // Initialize the database
        comorbiditiesPatientRepository.saveAndFlush(comorbiditiesPatient);

        // Get all the comorbiditiesPatientList
        restComorbiditiesPatientMockMvc.perform(get("/api/comorbidities-patients?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(comorbiditiesPatient.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].comorbiditietId").value(hasItem(DEFAULT_COMORBIDITIET_ID.intValue())))
            .andExpect(jsonPath("$.[*].exist").value(hasItem(DEFAULT_EXIST.booleanValue())));
    }

    @Test
    @Transactional
    public void getComorbiditiesPatient() throws Exception {
        // Initialize the database
        comorbiditiesPatientRepository.saveAndFlush(comorbiditiesPatient);

        // Get the comorbiditiesPatient
        restComorbiditiesPatientMockMvc.perform(get("/api/comorbidities-patients/{id}", comorbiditiesPatient.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(comorbiditiesPatient.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.comorbiditietId").value(DEFAULT_COMORBIDITIET_ID.intValue()))
            .andExpect(jsonPath("$.exist").value(DEFAULT_EXIST.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingComorbiditiesPatient() throws Exception {
        // Get the comorbiditiesPatient
        restComorbiditiesPatientMockMvc.perform(get("/api/comorbidities-patients/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateComorbiditiesPatient() throws Exception {
        // Initialize the database
        comorbiditiesPatientRepository.saveAndFlush(comorbiditiesPatient);

        int databaseSizeBeforeUpdate = comorbiditiesPatientRepository.findAll().size();

        // Update the comorbiditiesPatient
        ComorbiditiesPatient updatedComorbiditiesPatient = comorbiditiesPatientRepository.findById(comorbiditiesPatient.getId()).get();
        // Disconnect from session so that the updates on updatedComorbiditiesPatient are not directly saved in db
        em.detach(updatedComorbiditiesPatient);
        updatedComorbiditiesPatient
            .description(UPDATED_DESCRIPTION)
            .comorbiditietId(UPDATED_COMORBIDITIET_ID)
            .exist(UPDATED_EXIST);
        ComorbiditiesPatientDTO comorbiditiesPatientDTO = comorbiditiesPatientMapper.toDto(updatedComorbiditiesPatient);

        restComorbiditiesPatientMockMvc.perform(put("/api/comorbidities-patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(comorbiditiesPatientDTO)))
            .andExpect(status().isOk());

        // Validate the ComorbiditiesPatient in the database
        List<ComorbiditiesPatient> comorbiditiesPatientList = comorbiditiesPatientRepository.findAll();
        assertThat(comorbiditiesPatientList).hasSize(databaseSizeBeforeUpdate);
        ComorbiditiesPatient testComorbiditiesPatient = comorbiditiesPatientList.get(comorbiditiesPatientList.size() - 1);
        assertThat(testComorbiditiesPatient.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testComorbiditiesPatient.getComorbiditietId()).isEqualTo(UPDATED_COMORBIDITIET_ID);
        assertThat(testComorbiditiesPatient.isExist()).isEqualTo(UPDATED_EXIST);
    }

    @Test
    @Transactional
    public void updateNonExistingComorbiditiesPatient() throws Exception {
        int databaseSizeBeforeUpdate = comorbiditiesPatientRepository.findAll().size();

        // Create the ComorbiditiesPatient
        ComorbiditiesPatientDTO comorbiditiesPatientDTO = comorbiditiesPatientMapper.toDto(comorbiditiesPatient);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restComorbiditiesPatientMockMvc.perform(put("/api/comorbidities-patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(comorbiditiesPatientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ComorbiditiesPatient in the database
        List<ComorbiditiesPatient> comorbiditiesPatientList = comorbiditiesPatientRepository.findAll();
        assertThat(comorbiditiesPatientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteComorbiditiesPatient() throws Exception {
        // Initialize the database
        comorbiditiesPatientRepository.saveAndFlush(comorbiditiesPatient);

        int databaseSizeBeforeDelete = comorbiditiesPatientRepository.findAll().size();

        // Delete the comorbiditiesPatient
        restComorbiditiesPatientMockMvc.perform(delete("/api/comorbidities-patients/{id}", comorbiditiesPatient.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ComorbiditiesPatient> comorbiditiesPatientList = comorbiditiesPatientRepository.findAll();
        assertThat(comorbiditiesPatientList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
