package com.aditum.cardiorehabcr.web.rest;

import com.aditum.cardiorehabcr.CardioRehabCrApp;
import com.aditum.cardiorehabcr.domain.NonSpecificPainsSession;
import com.aditum.cardiorehabcr.repository.NonSpecificPainsSessionRepository;
import com.aditum.cardiorehabcr.service.NonSpecificPainsSessionService;
import com.aditum.cardiorehabcr.service.dto.NonSpecificPainsSessionDTO;
import com.aditum.cardiorehabcr.service.mapper.NonSpecificPainsSessionMapper;
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
 * Integration tests for the {@link NonSpecificPainsSessionResource} REST controller.
 */
@SpringBootTest(classes = CardioRehabCrApp.class)
public class NonSpecificPainsSessionResourceIT {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Long DEFAULT_NON_SPECIFIC_PAIN_ID = 1L;
    private static final Long UPDATED_NON_SPECIFIC_PAIN_ID = 2L;

    private static final Boolean DEFAULT_EXIST = false;
    private static final Boolean UPDATED_EXIST = true;

    @Autowired
    private NonSpecificPainsSessionRepository nonSpecificPainsSessionRepository;

    @Autowired
    private NonSpecificPainsSessionMapper nonSpecificPainsSessionMapper;

    @Autowired
    private NonSpecificPainsSessionService nonSpecificPainsSessionService;

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

    private MockMvc restNonSpecificPainsSessionMockMvc;

    private NonSpecificPainsSession nonSpecificPainsSession;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NonSpecificPainsSessionResource nonSpecificPainsSessionResource = new NonSpecificPainsSessionResource(nonSpecificPainsSessionService);
        this.restNonSpecificPainsSessionMockMvc = MockMvcBuilders.standaloneSetup(nonSpecificPainsSessionResource)
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
    public static NonSpecificPainsSession createEntity(EntityManager em) {
        NonSpecificPainsSession nonSpecificPainsSession = new NonSpecificPainsSession()
            .description(DEFAULT_DESCRIPTION)
            .nonSpecificPainId(DEFAULT_NON_SPECIFIC_PAIN_ID)
            .exist(DEFAULT_EXIST);
        return nonSpecificPainsSession;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NonSpecificPainsSession createUpdatedEntity(EntityManager em) {
        NonSpecificPainsSession nonSpecificPainsSession = new NonSpecificPainsSession()
            .description(UPDATED_DESCRIPTION)
            .nonSpecificPainId(UPDATED_NON_SPECIFIC_PAIN_ID)
            .exist(UPDATED_EXIST);
        return nonSpecificPainsSession;
    }

    @BeforeEach
    public void initTest() {
        nonSpecificPainsSession = createEntity(em);
    }

    @Test
    @Transactional
    public void createNonSpecificPainsSession() throws Exception {
        int databaseSizeBeforeCreate = nonSpecificPainsSessionRepository.findAll().size();

        // Create the NonSpecificPainsSession
        NonSpecificPainsSessionDTO nonSpecificPainsSessionDTO = nonSpecificPainsSessionMapper.toDto(nonSpecificPainsSession);
        restNonSpecificPainsSessionMockMvc.perform(post("/api/non-specific-pains-sessions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nonSpecificPainsSessionDTO)))
            .andExpect(status().isCreated());

        // Validate the NonSpecificPainsSession in the database
        List<NonSpecificPainsSession> nonSpecificPainsSessionList = nonSpecificPainsSessionRepository.findAll();
        assertThat(nonSpecificPainsSessionList).hasSize(databaseSizeBeforeCreate + 1);
        NonSpecificPainsSession testNonSpecificPainsSession = nonSpecificPainsSessionList.get(nonSpecificPainsSessionList.size() - 1);
        assertThat(testNonSpecificPainsSession.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testNonSpecificPainsSession.getNonSpecificPainId()).isEqualTo(DEFAULT_NON_SPECIFIC_PAIN_ID);
        assertThat(testNonSpecificPainsSession.isExist()).isEqualTo(DEFAULT_EXIST);
    }

    @Test
    @Transactional
    public void createNonSpecificPainsSessionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nonSpecificPainsSessionRepository.findAll().size();

        // Create the NonSpecificPainsSession with an existing ID
        nonSpecificPainsSession.setId(1L);
        NonSpecificPainsSessionDTO nonSpecificPainsSessionDTO = nonSpecificPainsSessionMapper.toDto(nonSpecificPainsSession);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNonSpecificPainsSessionMockMvc.perform(post("/api/non-specific-pains-sessions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nonSpecificPainsSessionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NonSpecificPainsSession in the database
        List<NonSpecificPainsSession> nonSpecificPainsSessionList = nonSpecificPainsSessionRepository.findAll();
        assertThat(nonSpecificPainsSessionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNonSpecificPainIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = nonSpecificPainsSessionRepository.findAll().size();
        // set the field null
        nonSpecificPainsSession.setNonSpecificPainId(null);

        // Create the NonSpecificPainsSession, which fails.
        NonSpecificPainsSessionDTO nonSpecificPainsSessionDTO = nonSpecificPainsSessionMapper.toDto(nonSpecificPainsSession);

        restNonSpecificPainsSessionMockMvc.perform(post("/api/non-specific-pains-sessions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nonSpecificPainsSessionDTO)))
            .andExpect(status().isBadRequest());

        List<NonSpecificPainsSession> nonSpecificPainsSessionList = nonSpecificPainsSessionRepository.findAll();
        assertThat(nonSpecificPainsSessionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExistIsRequired() throws Exception {
        int databaseSizeBeforeTest = nonSpecificPainsSessionRepository.findAll().size();
        // set the field null
        nonSpecificPainsSession.setExist(null);

        // Create the NonSpecificPainsSession, which fails.
        NonSpecificPainsSessionDTO nonSpecificPainsSessionDTO = nonSpecificPainsSessionMapper.toDto(nonSpecificPainsSession);

        restNonSpecificPainsSessionMockMvc.perform(post("/api/non-specific-pains-sessions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nonSpecificPainsSessionDTO)))
            .andExpect(status().isBadRequest());

        List<NonSpecificPainsSession> nonSpecificPainsSessionList = nonSpecificPainsSessionRepository.findAll();
        assertThat(nonSpecificPainsSessionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNonSpecificPainsSessions() throws Exception {
        // Initialize the database
        nonSpecificPainsSessionRepository.saveAndFlush(nonSpecificPainsSession);

        // Get all the nonSpecificPainsSessionList
        restNonSpecificPainsSessionMockMvc.perform(get("/api/non-specific-pains-sessions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nonSpecificPainsSession.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].nonSpecificPainId").value(hasItem(DEFAULT_NON_SPECIFIC_PAIN_ID.intValue())))
            .andExpect(jsonPath("$.[*].exist").value(hasItem(DEFAULT_EXIST.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getNonSpecificPainsSession() throws Exception {
        // Initialize the database
        nonSpecificPainsSessionRepository.saveAndFlush(nonSpecificPainsSession);

        // Get the nonSpecificPainsSession
        restNonSpecificPainsSessionMockMvc.perform(get("/api/non-specific-pains-sessions/{id}", nonSpecificPainsSession.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nonSpecificPainsSession.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.nonSpecificPainId").value(DEFAULT_NON_SPECIFIC_PAIN_ID.intValue()))
            .andExpect(jsonPath("$.exist").value(DEFAULT_EXIST.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingNonSpecificPainsSession() throws Exception {
        // Get the nonSpecificPainsSession
        restNonSpecificPainsSessionMockMvc.perform(get("/api/non-specific-pains-sessions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNonSpecificPainsSession() throws Exception {
        // Initialize the database
        nonSpecificPainsSessionRepository.saveAndFlush(nonSpecificPainsSession);

        int databaseSizeBeforeUpdate = nonSpecificPainsSessionRepository.findAll().size();

        // Update the nonSpecificPainsSession
        NonSpecificPainsSession updatedNonSpecificPainsSession = nonSpecificPainsSessionRepository.findById(nonSpecificPainsSession.getId()).get();
        // Disconnect from session so that the updates on updatedNonSpecificPainsSession are not directly saved in db
        em.detach(updatedNonSpecificPainsSession);
        updatedNonSpecificPainsSession
            .description(UPDATED_DESCRIPTION)
            .nonSpecificPainId(UPDATED_NON_SPECIFIC_PAIN_ID)
            .exist(UPDATED_EXIST);
        NonSpecificPainsSessionDTO nonSpecificPainsSessionDTO = nonSpecificPainsSessionMapper.toDto(updatedNonSpecificPainsSession);

        restNonSpecificPainsSessionMockMvc.perform(put("/api/non-specific-pains-sessions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nonSpecificPainsSessionDTO)))
            .andExpect(status().isOk());

        // Validate the NonSpecificPainsSession in the database
        List<NonSpecificPainsSession> nonSpecificPainsSessionList = nonSpecificPainsSessionRepository.findAll();
        assertThat(nonSpecificPainsSessionList).hasSize(databaseSizeBeforeUpdate);
        NonSpecificPainsSession testNonSpecificPainsSession = nonSpecificPainsSessionList.get(nonSpecificPainsSessionList.size() - 1);
        assertThat(testNonSpecificPainsSession.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testNonSpecificPainsSession.getNonSpecificPainId()).isEqualTo(UPDATED_NON_SPECIFIC_PAIN_ID);
        assertThat(testNonSpecificPainsSession.isExist()).isEqualTo(UPDATED_EXIST);
    }

    @Test
    @Transactional
    public void updateNonExistingNonSpecificPainsSession() throws Exception {
        int databaseSizeBeforeUpdate = nonSpecificPainsSessionRepository.findAll().size();

        // Create the NonSpecificPainsSession
        NonSpecificPainsSessionDTO nonSpecificPainsSessionDTO = nonSpecificPainsSessionMapper.toDto(nonSpecificPainsSession);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNonSpecificPainsSessionMockMvc.perform(put("/api/non-specific-pains-sessions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nonSpecificPainsSessionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NonSpecificPainsSession in the database
        List<NonSpecificPainsSession> nonSpecificPainsSessionList = nonSpecificPainsSessionRepository.findAll();
        assertThat(nonSpecificPainsSessionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNonSpecificPainsSession() throws Exception {
        // Initialize the database
        nonSpecificPainsSessionRepository.saveAndFlush(nonSpecificPainsSession);

        int databaseSizeBeforeDelete = nonSpecificPainsSessionRepository.findAll().size();

        // Delete the nonSpecificPainsSession
        restNonSpecificPainsSessionMockMvc.perform(delete("/api/non-specific-pains-sessions/{id}", nonSpecificPainsSession.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NonSpecificPainsSession> nonSpecificPainsSessionList = nonSpecificPainsSessionRepository.findAll();
        assertThat(nonSpecificPainsSessionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
