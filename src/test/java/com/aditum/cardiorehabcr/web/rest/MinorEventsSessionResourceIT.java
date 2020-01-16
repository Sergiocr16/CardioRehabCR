package com.aditum.cardiorehabcr.web.rest;

import com.aditum.cardiorehabcr.CardioRehabCrApp;
import com.aditum.cardiorehabcr.domain.MinorEventsSession;
import com.aditum.cardiorehabcr.repository.MinorEventsSessionRepository;
import com.aditum.cardiorehabcr.service.MinorEventsSessionService;
import com.aditum.cardiorehabcr.service.dto.MinorEventsSessionDTO;
import com.aditum.cardiorehabcr.service.impl.MinorEventsSessionServiceImpl;
import com.aditum.cardiorehabcr.service.mapper.MinorEventsSessionMapper;
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
 * Integration tests for the {@link MinorEventsSessionResource} REST controller.
 */
@SpringBootTest(classes = CardioRehabCrApp.class)
public class MinorEventsSessionResourceIT {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Long DEFAULT_MINOR_EVENT_ID = 1L;
    private static final Long UPDATED_MINOR_EVENT_ID = 2L;

    private static final Boolean DEFAULT_EXIST = false;
    private static final Boolean UPDATED_EXIST = true;

    @Autowired
    private MinorEventsSessionRepository minorEventsSessionRepository;

    @Autowired
    private MinorEventsSessionMapper minorEventsSessionMapper;

    @Autowired
    private MinorEventsSessionServiceImpl minorEventsSessionService;

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

    private MockMvc restMinorEventsSessionMockMvc;

    private MinorEventsSession minorEventsSession;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MinorEventsSessionResource minorEventsSessionResource = new MinorEventsSessionResource(minorEventsSessionService);
        this.restMinorEventsSessionMockMvc = MockMvcBuilders.standaloneSetup(minorEventsSessionResource)
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
    public static MinorEventsSession createEntity(EntityManager em) {
        MinorEventsSession minorEventsSession = new MinorEventsSession()
            .description(DEFAULT_DESCRIPTION)
            .minorEventId(DEFAULT_MINOR_EVENT_ID)
            .exist(DEFAULT_EXIST);
        return minorEventsSession;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MinorEventsSession createUpdatedEntity(EntityManager em) {
        MinorEventsSession minorEventsSession = new MinorEventsSession()
            .description(UPDATED_DESCRIPTION)
            .minorEventId(UPDATED_MINOR_EVENT_ID)
            .exist(UPDATED_EXIST);
        return minorEventsSession;
    }

    @BeforeEach
    public void initTest() {
        minorEventsSession = createEntity(em);
    }

    @Test
    @Transactional
    public void createMinorEventsSession() throws Exception {
        int databaseSizeBeforeCreate = minorEventsSessionRepository.findAll().size();

        // Create the MinorEventsSession
        MinorEventsSessionDTO minorEventsSessionDTO = minorEventsSessionMapper.toDto(minorEventsSession);
        restMinorEventsSessionMockMvc.perform(post("/api/minor-events-sessions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(minorEventsSessionDTO)))
            .andExpect(status().isCreated());

        // Validate the MinorEventsSession in the database
        List<MinorEventsSession> minorEventsSessionList = minorEventsSessionRepository.findAll();
        assertThat(minorEventsSessionList).hasSize(databaseSizeBeforeCreate + 1);
        MinorEventsSession testMinorEventsSession = minorEventsSessionList.get(minorEventsSessionList.size() - 1);
        assertThat(testMinorEventsSession.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMinorEventsSession.getMinorEventId()).isEqualTo(DEFAULT_MINOR_EVENT_ID);
        assertThat(testMinorEventsSession.isExist()).isEqualTo(DEFAULT_EXIST);
    }

    @Test
    @Transactional
    public void createMinorEventsSessionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = minorEventsSessionRepository.findAll().size();

        // Create the MinorEventsSession with an existing ID
        minorEventsSession.setId(1L);
        MinorEventsSessionDTO minorEventsSessionDTO = minorEventsSessionMapper.toDto(minorEventsSession);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMinorEventsSessionMockMvc.perform(post("/api/minor-events-sessions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(minorEventsSessionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MinorEventsSession in the database
        List<MinorEventsSession> minorEventsSessionList = minorEventsSessionRepository.findAll();
        assertThat(minorEventsSessionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMinorEventIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = minorEventsSessionRepository.findAll().size();
        // set the field null
        minorEventsSession.setMinorEventId(null);

        // Create the MinorEventsSession, which fails.
        MinorEventsSessionDTO minorEventsSessionDTO = minorEventsSessionMapper.toDto(minorEventsSession);

        restMinorEventsSessionMockMvc.perform(post("/api/minor-events-sessions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(minorEventsSessionDTO)))
            .andExpect(status().isBadRequest());

        List<MinorEventsSession> minorEventsSessionList = minorEventsSessionRepository.findAll();
        assertThat(minorEventsSessionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExistIsRequired() throws Exception {
        int databaseSizeBeforeTest = minorEventsSessionRepository.findAll().size();
        // set the field null
        minorEventsSession.setExist(null);

        // Create the MinorEventsSession, which fails.
        MinorEventsSessionDTO minorEventsSessionDTO = minorEventsSessionMapper.toDto(minorEventsSession);

        restMinorEventsSessionMockMvc.perform(post("/api/minor-events-sessions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(minorEventsSessionDTO)))
            .andExpect(status().isBadRequest());

        List<MinorEventsSession> minorEventsSessionList = minorEventsSessionRepository.findAll();
        assertThat(minorEventsSessionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMinorEventsSessions() throws Exception {
        // Initialize the database
        minorEventsSessionRepository.saveAndFlush(minorEventsSession);

        // Get all the minorEventsSessionList
        restMinorEventsSessionMockMvc.perform(get("/api/minor-events-sessions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(minorEventsSession.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].minorEventId").value(hasItem(DEFAULT_MINOR_EVENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].exist").value(hasItem(DEFAULT_EXIST.booleanValue())));
    }

    @Test
    @Transactional
    public void getMinorEventsSession() throws Exception {
        // Initialize the database
        minorEventsSessionRepository.saveAndFlush(minorEventsSession);

        // Get the minorEventsSession
        restMinorEventsSessionMockMvc.perform(get("/api/minor-events-sessions/{id}", minorEventsSession.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(minorEventsSession.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.minorEventId").value(DEFAULT_MINOR_EVENT_ID.intValue()))
            .andExpect(jsonPath("$.exist").value(DEFAULT_EXIST.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingMinorEventsSession() throws Exception {
        // Get the minorEventsSession
        restMinorEventsSessionMockMvc.perform(get("/api/minor-events-sessions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMinorEventsSession() throws Exception {
        // Initialize the database
        minorEventsSessionRepository.saveAndFlush(minorEventsSession);

        int databaseSizeBeforeUpdate = minorEventsSessionRepository.findAll().size();

        // Update the minorEventsSession
        MinorEventsSession updatedMinorEventsSession = minorEventsSessionRepository.findById(minorEventsSession.getId()).get();
        // Disconnect from session so that the updates on updatedMinorEventsSession are not directly saved in db
        em.detach(updatedMinorEventsSession);
        updatedMinorEventsSession
            .description(UPDATED_DESCRIPTION)
            .minorEventId(UPDATED_MINOR_EVENT_ID)
            .exist(UPDATED_EXIST);
        MinorEventsSessionDTO minorEventsSessionDTO = minorEventsSessionMapper.toDto(updatedMinorEventsSession);

        restMinorEventsSessionMockMvc.perform(put("/api/minor-events-sessions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(minorEventsSessionDTO)))
            .andExpect(status().isOk());

        // Validate the MinorEventsSession in the database
        List<MinorEventsSession> minorEventsSessionList = minorEventsSessionRepository.findAll();
        assertThat(minorEventsSessionList).hasSize(databaseSizeBeforeUpdate);
        MinorEventsSession testMinorEventsSession = minorEventsSessionList.get(minorEventsSessionList.size() - 1);
        assertThat(testMinorEventsSession.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMinorEventsSession.getMinorEventId()).isEqualTo(UPDATED_MINOR_EVENT_ID);
        assertThat(testMinorEventsSession.isExist()).isEqualTo(UPDATED_EXIST);
    }

    @Test
    @Transactional
    public void updateNonExistingMinorEventsSession() throws Exception {
        int databaseSizeBeforeUpdate = minorEventsSessionRepository.findAll().size();

        // Create the MinorEventsSession
        MinorEventsSessionDTO minorEventsSessionDTO = minorEventsSessionMapper.toDto(minorEventsSession);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMinorEventsSessionMockMvc.perform(put("/api/minor-events-sessions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(minorEventsSessionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MinorEventsSession in the database
        List<MinorEventsSession> minorEventsSessionList = minorEventsSessionRepository.findAll();
        assertThat(minorEventsSessionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMinorEventsSession() throws Exception {
        // Initialize the database
        minorEventsSessionRepository.saveAndFlush(minorEventsSession);

        int databaseSizeBeforeDelete = minorEventsSessionRepository.findAll().size();

        // Delete the minorEventsSession
        restMinorEventsSessionMockMvc.perform(delete("/api/minor-events-sessions/{id}", minorEventsSession.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MinorEventsSession> minorEventsSessionList = minorEventsSessionRepository.findAll();
        assertThat(minorEventsSessionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
