package com.aditum.cardiorehabcr.web.rest;

import com.aditum.cardiorehabcr.CardioRehabCrApp;
import com.aditum.cardiorehabcr.domain.RehabilitationCenter;
import com.aditum.cardiorehabcr.repository.RehabilitationCenterRepository;
import com.aditum.cardiorehabcr.service.RehabilitationCenterService;
import com.aditum.cardiorehabcr.service.dto.RehabilitationCenterDTO;
import com.aditum.cardiorehabcr.service.mapper.RehabilitationCenterMapper;
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
 * Integration tests for the {@link RehabilitationCenterResource} REST controller.
 */
@SpringBootTest(classes = CardioRehabCrApp.class)
public class RehabilitationCenterResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DELETED = false;
    private static final Boolean UPDATED_DELETED = true;

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private RehabilitationCenterRepository rehabilitationCenterRepository;

    @Autowired
    private RehabilitationCenterMapper rehabilitationCenterMapper;

    @Autowired
    private RehabilitationCenterService rehabilitationCenterService;

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

    private MockMvc restRehabilitationCenterMockMvc;

    private RehabilitationCenter rehabilitationCenter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RehabilitationCenterResource rehabilitationCenterResource = new RehabilitationCenterResource(rehabilitationCenterService);
        this.restRehabilitationCenterMockMvc = MockMvcBuilders.standaloneSetup(rehabilitationCenterResource)
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
    public static RehabilitationCenter createEntity(EntityManager em) {
        RehabilitationCenter rehabilitationCenter = new RehabilitationCenter()
            .name(DEFAULT_NAME)
            .telephone(DEFAULT_TELEPHONE)
            .deleted(DEFAULT_DELETED)
            .status(DEFAULT_STATUS);
        return rehabilitationCenter;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RehabilitationCenter createUpdatedEntity(EntityManager em) {
        RehabilitationCenter rehabilitationCenter = new RehabilitationCenter()
            .name(UPDATED_NAME)
            .telephone(UPDATED_TELEPHONE)
            .deleted(UPDATED_DELETED)
            .status(UPDATED_STATUS);
        return rehabilitationCenter;
    }

    @BeforeEach
    public void initTest() {
        rehabilitationCenter = createEntity(em);
    }

    @Test
    @Transactional
    public void createRehabilitationCenter() throws Exception {
        int databaseSizeBeforeCreate = rehabilitationCenterRepository.findAll().size();

        // Create the RehabilitationCenter
        RehabilitationCenterDTO rehabilitationCenterDTO = rehabilitationCenterMapper.toDto(rehabilitationCenter);
        restRehabilitationCenterMockMvc.perform(post("/api/rehabilitation-centers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rehabilitationCenterDTO)))
            .andExpect(status().isCreated());

        // Validate the RehabilitationCenter in the database
        List<RehabilitationCenter> rehabilitationCenterList = rehabilitationCenterRepository.findAll();
        assertThat(rehabilitationCenterList).hasSize(databaseSizeBeforeCreate + 1);
        RehabilitationCenter testRehabilitationCenter = rehabilitationCenterList.get(rehabilitationCenterList.size() - 1);
        assertThat(testRehabilitationCenter.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testRehabilitationCenter.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testRehabilitationCenter.isDeleted()).isEqualTo(DEFAULT_DELETED);
        assertThat(testRehabilitationCenter.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createRehabilitationCenterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rehabilitationCenterRepository.findAll().size();

        // Create the RehabilitationCenter with an existing ID
        rehabilitationCenter.setId(1L);
        RehabilitationCenterDTO rehabilitationCenterDTO = rehabilitationCenterMapper.toDto(rehabilitationCenter);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRehabilitationCenterMockMvc.perform(post("/api/rehabilitation-centers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rehabilitationCenterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RehabilitationCenter in the database
        List<RehabilitationCenter> rehabilitationCenterList = rehabilitationCenterRepository.findAll();
        assertThat(rehabilitationCenterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = rehabilitationCenterRepository.findAll().size();
        // set the field null
        rehabilitationCenter.setName(null);

        // Create the RehabilitationCenter, which fails.
        RehabilitationCenterDTO rehabilitationCenterDTO = rehabilitationCenterMapper.toDto(rehabilitationCenter);

        restRehabilitationCenterMockMvc.perform(post("/api/rehabilitation-centers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rehabilitationCenterDTO)))
            .andExpect(status().isBadRequest());

        List<RehabilitationCenter> rehabilitationCenterList = rehabilitationCenterRepository.findAll();
        assertThat(rehabilitationCenterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRehabilitationCenters() throws Exception {
        // Initialize the database
        rehabilitationCenterRepository.saveAndFlush(rehabilitationCenter);

        // Get all the rehabilitationCenterList
        restRehabilitationCenterMockMvc.perform(get("/api/rehabilitation-centers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rehabilitationCenter.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE)))
            .andExpect(jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    
    @Test
    @Transactional
    public void getRehabilitationCenter() throws Exception {
        // Initialize the database
        rehabilitationCenterRepository.saveAndFlush(rehabilitationCenter);

        // Get the rehabilitationCenter
        restRehabilitationCenterMockMvc.perform(get("/api/rehabilitation-centers/{id}", rehabilitationCenter.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(rehabilitationCenter.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE))
            .andExpect(jsonPath("$.deleted").value(DEFAULT_DELETED.booleanValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getNonExistingRehabilitationCenter() throws Exception {
        // Get the rehabilitationCenter
        restRehabilitationCenterMockMvc.perform(get("/api/rehabilitation-centers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRehabilitationCenter() throws Exception {
        // Initialize the database
        rehabilitationCenterRepository.saveAndFlush(rehabilitationCenter);

        int databaseSizeBeforeUpdate = rehabilitationCenterRepository.findAll().size();

        // Update the rehabilitationCenter
        RehabilitationCenter updatedRehabilitationCenter = rehabilitationCenterRepository.findById(rehabilitationCenter.getId()).get();
        // Disconnect from session so that the updates on updatedRehabilitationCenter are not directly saved in db
        em.detach(updatedRehabilitationCenter);
        updatedRehabilitationCenter
            .name(UPDATED_NAME)
            .telephone(UPDATED_TELEPHONE)
            .deleted(UPDATED_DELETED)
            .status(UPDATED_STATUS);
        RehabilitationCenterDTO rehabilitationCenterDTO = rehabilitationCenterMapper.toDto(updatedRehabilitationCenter);

        restRehabilitationCenterMockMvc.perform(put("/api/rehabilitation-centers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rehabilitationCenterDTO)))
            .andExpect(status().isOk());

        // Validate the RehabilitationCenter in the database
        List<RehabilitationCenter> rehabilitationCenterList = rehabilitationCenterRepository.findAll();
        assertThat(rehabilitationCenterList).hasSize(databaseSizeBeforeUpdate);
        RehabilitationCenter testRehabilitationCenter = rehabilitationCenterList.get(rehabilitationCenterList.size() - 1);
        assertThat(testRehabilitationCenter.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRehabilitationCenter.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testRehabilitationCenter.isDeleted()).isEqualTo(UPDATED_DELETED);
        assertThat(testRehabilitationCenter.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingRehabilitationCenter() throws Exception {
        int databaseSizeBeforeUpdate = rehabilitationCenterRepository.findAll().size();

        // Create the RehabilitationCenter
        RehabilitationCenterDTO rehabilitationCenterDTO = rehabilitationCenterMapper.toDto(rehabilitationCenter);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRehabilitationCenterMockMvc.perform(put("/api/rehabilitation-centers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rehabilitationCenterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RehabilitationCenter in the database
        List<RehabilitationCenter> rehabilitationCenterList = rehabilitationCenterRepository.findAll();
        assertThat(rehabilitationCenterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRehabilitationCenter() throws Exception {
        // Initialize the database
        rehabilitationCenterRepository.saveAndFlush(rehabilitationCenter);

        int databaseSizeBeforeDelete = rehabilitationCenterRepository.findAll().size();

        // Delete the rehabilitationCenter
        restRehabilitationCenterMockMvc.perform(delete("/api/rehabilitation-centers/{id}", rehabilitationCenter.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RehabilitationCenter> rehabilitationCenterList = rehabilitationCenterRepository.findAll();
        assertThat(rehabilitationCenterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
