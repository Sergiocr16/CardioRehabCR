package com.aditum.cardiorehabcr.web.rest;

import com.aditum.cardiorehabcr.CardioRehabCrApp;
import com.aditum.cardiorehabcr.domain.DepressiveSymptom;
import com.aditum.cardiorehabcr.repository.DepressiveSymptomRepository;
import com.aditum.cardiorehabcr.service.DepressiveSymptomService;
import com.aditum.cardiorehabcr.service.dto.DepressiveSymptomDTO;
import com.aditum.cardiorehabcr.service.mapper.DepressiveSymptomMapper;
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
 * Integration tests for the {@link DepressiveSymptomResource} REST controller.
 */
@SpringBootTest(classes = CardioRehabCrApp.class)
public class DepressiveSymptomResourceIT {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DELETED = false;
    private static final Boolean UPDATED_DELETED = true;

    @Autowired
    private DepressiveSymptomRepository depressiveSymptomRepository;

    @Autowired
    private DepressiveSymptomMapper depressiveSymptomMapper;

    @Autowired
    private DepressiveSymptomService depressiveSymptomService;

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

    private MockMvc restDepressiveSymptomMockMvc;

    private DepressiveSymptom depressiveSymptom;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DepressiveSymptomResource depressiveSymptomResource = new DepressiveSymptomResource(depressiveSymptomService);
        this.restDepressiveSymptomMockMvc = MockMvcBuilders.standaloneSetup(depressiveSymptomResource)
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
    public static DepressiveSymptom createEntity(EntityManager em) {
        DepressiveSymptom depressiveSymptom = new DepressiveSymptom()
            .description(DEFAULT_DESCRIPTION)
            .code(DEFAULT_CODE)
            .deleted(DEFAULT_DELETED);
        return depressiveSymptom;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DepressiveSymptom createUpdatedEntity(EntityManager em) {
        DepressiveSymptom depressiveSymptom = new DepressiveSymptom()
            .description(UPDATED_DESCRIPTION)
            .code(UPDATED_CODE)
            .deleted(UPDATED_DELETED);
        return depressiveSymptom;
    }

    @BeforeEach
    public void initTest() {
        depressiveSymptom = createEntity(em);
    }

    @Test
    @Transactional
    public void createDepressiveSymptom() throws Exception {
        int databaseSizeBeforeCreate = depressiveSymptomRepository.findAll().size();

        // Create the DepressiveSymptom
        DepressiveSymptomDTO depressiveSymptomDTO = depressiveSymptomMapper.toDto(depressiveSymptom);
        restDepressiveSymptomMockMvc.perform(post("/api/depressive-symptoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(depressiveSymptomDTO)))
            .andExpect(status().isCreated());

        // Validate the DepressiveSymptom in the database
        List<DepressiveSymptom> depressiveSymptomList = depressiveSymptomRepository.findAll();
        assertThat(depressiveSymptomList).hasSize(databaseSizeBeforeCreate + 1);
        DepressiveSymptom testDepressiveSymptom = depressiveSymptomList.get(depressiveSymptomList.size() - 1);
        assertThat(testDepressiveSymptom.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testDepressiveSymptom.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testDepressiveSymptom.isDeleted()).isEqualTo(DEFAULT_DELETED);
    }

    @Test
    @Transactional
    public void createDepressiveSymptomWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = depressiveSymptomRepository.findAll().size();

        // Create the DepressiveSymptom with an existing ID
        depressiveSymptom.setId(1L);
        DepressiveSymptomDTO depressiveSymptomDTO = depressiveSymptomMapper.toDto(depressiveSymptom);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDepressiveSymptomMockMvc.perform(post("/api/depressive-symptoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(depressiveSymptomDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DepressiveSymptom in the database
        List<DepressiveSymptom> depressiveSymptomList = depressiveSymptomRepository.findAll();
        assertThat(depressiveSymptomList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = depressiveSymptomRepository.findAll().size();
        // set the field null
        depressiveSymptom.setDescription(null);

        // Create the DepressiveSymptom, which fails.
        DepressiveSymptomDTO depressiveSymptomDTO = depressiveSymptomMapper.toDto(depressiveSymptom);

        restDepressiveSymptomMockMvc.perform(post("/api/depressive-symptoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(depressiveSymptomDTO)))
            .andExpect(status().isBadRequest());

        List<DepressiveSymptom> depressiveSymptomList = depressiveSymptomRepository.findAll();
        assertThat(depressiveSymptomList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDepressiveSymptoms() throws Exception {
        // Initialize the database
        depressiveSymptomRepository.saveAndFlush(depressiveSymptom);

        // Get all the depressiveSymptomList
        restDepressiveSymptomMockMvc.perform(get("/api/depressive-symptoms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(depressiveSymptom.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getDepressiveSymptom() throws Exception {
        // Initialize the database
        depressiveSymptomRepository.saveAndFlush(depressiveSymptom);

        // Get the depressiveSymptom
        restDepressiveSymptomMockMvc.perform(get("/api/depressive-symptoms/{id}", depressiveSymptom.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(depressiveSymptom.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.deleted").value(DEFAULT_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDepressiveSymptom() throws Exception {
        // Get the depressiveSymptom
        restDepressiveSymptomMockMvc.perform(get("/api/depressive-symptoms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDepressiveSymptom() throws Exception {
        // Initialize the database
        depressiveSymptomRepository.saveAndFlush(depressiveSymptom);

        int databaseSizeBeforeUpdate = depressiveSymptomRepository.findAll().size();

        // Update the depressiveSymptom
        DepressiveSymptom updatedDepressiveSymptom = depressiveSymptomRepository.findById(depressiveSymptom.getId()).get();
        // Disconnect from session so that the updates on updatedDepressiveSymptom are not directly saved in db
        em.detach(updatedDepressiveSymptom);
        updatedDepressiveSymptom
            .description(UPDATED_DESCRIPTION)
            .code(UPDATED_CODE)
            .deleted(UPDATED_DELETED);
        DepressiveSymptomDTO depressiveSymptomDTO = depressiveSymptomMapper.toDto(updatedDepressiveSymptom);

        restDepressiveSymptomMockMvc.perform(put("/api/depressive-symptoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(depressiveSymptomDTO)))
            .andExpect(status().isOk());

        // Validate the DepressiveSymptom in the database
        List<DepressiveSymptom> depressiveSymptomList = depressiveSymptomRepository.findAll();
        assertThat(depressiveSymptomList).hasSize(databaseSizeBeforeUpdate);
        DepressiveSymptom testDepressiveSymptom = depressiveSymptomList.get(depressiveSymptomList.size() - 1);
        assertThat(testDepressiveSymptom.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testDepressiveSymptom.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testDepressiveSymptom.isDeleted()).isEqualTo(UPDATED_DELETED);
    }

    @Test
    @Transactional
    public void updateNonExistingDepressiveSymptom() throws Exception {
        int databaseSizeBeforeUpdate = depressiveSymptomRepository.findAll().size();

        // Create the DepressiveSymptom
        DepressiveSymptomDTO depressiveSymptomDTO = depressiveSymptomMapper.toDto(depressiveSymptom);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDepressiveSymptomMockMvc.perform(put("/api/depressive-symptoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(depressiveSymptomDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DepressiveSymptom in the database
        List<DepressiveSymptom> depressiveSymptomList = depressiveSymptomRepository.findAll();
        assertThat(depressiveSymptomList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDepressiveSymptom() throws Exception {
        // Initialize the database
        depressiveSymptomRepository.saveAndFlush(depressiveSymptom);

        int databaseSizeBeforeDelete = depressiveSymptomRepository.findAll().size();

        // Delete the depressiveSymptom
        restDepressiveSymptomMockMvc.perform(delete("/api/depressive-symptoms/{id}", depressiveSymptom.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DepressiveSymptom> depressiveSymptomList = depressiveSymptomRepository.findAll();
        assertThat(depressiveSymptomList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
