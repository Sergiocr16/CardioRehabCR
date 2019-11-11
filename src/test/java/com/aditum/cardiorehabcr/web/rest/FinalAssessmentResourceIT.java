package com.aditum.cardiorehabcr.web.rest;

import com.aditum.cardiorehabcr.CardioRehabCrApp;
import com.aditum.cardiorehabcr.domain.FinalAssessment;
import com.aditum.cardiorehabcr.repository.FinalAssessmentRepository;
import com.aditum.cardiorehabcr.service.FinalAssessmentService;
import com.aditum.cardiorehabcr.service.dto.FinalAssessmentDTO;
import com.aditum.cardiorehabcr.service.mapper.FinalAssessmentMapper;
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
 * Integration tests for the {@link FinalAssessmentResource} REST controller.
 */
@SpringBootTest(classes = CardioRehabCrApp.class)
public class FinalAssessmentResourceIT {

    private static final String DEFAULT_SMOKING = "AAAAAAAAAA";
    private static final String UPDATED_SMOKING = "BBBBBBBBBB";

    private static final String DEFAULT_WEIGHT = "AAAAAAAAAA";
    private static final String UPDATED_WEIGHT = "BBBBBBBBBB";

    private static final String DEFAULT_SIZE = "AAAAAAAAAA";
    private static final String UPDATED_SIZE = "BBBBBBBBBB";

    private static final String DEFAULT_I_MC = "AAAAAAAAAA";
    private static final String UPDATED_I_MC = "BBBBBBBBBB";

    private static final String DEFAULT_HBIAC = "AAAAAAAAAA";
    private static final String UPDATED_HBIAC = "BBBBBBBBBB";

    private static final String DEFAULT_BASELINE_FUNCTIONAL_CAPACITY = "AAAAAAAAAA";
    private static final String UPDATED_BASELINE_FUNCTIONAL_CAPACITY = "BBBBBBBBBB";

    private static final String DEFAULT_L_DL = "AAAAAAAAAA";
    private static final String UPDATED_L_DL = "BBBBBBBBBB";

    private static final String DEFAULT_H_DL = "AAAAAAAAAA";
    private static final String UPDATED_H_DL = "BBBBBBBBBB";

    private static final String DEFAULT_CARDIOVASCULAR_RISK = "AAAAAAAAAA";
    private static final String UPDATED_CARDIOVASCULAR_RISK = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_WORKING = false;
    private static final Boolean UPDATED_IS_WORKING = true;

    private static final Boolean DEFAULT_DECEASED = false;
    private static final Boolean UPDATED_DECEASED = true;

    private static final Boolean DEFAULT_ABANDONMENT = false;
    private static final Boolean UPDATED_ABANDONMENT = true;

    private static final Boolean DEFAULT_ABANDONMENT_MEDIC_CAUSE = false;
    private static final Boolean UPDATED_ABANDONMENT_MEDIC_CAUSE = true;

    private static final Boolean DEFAULT_HOSPITALIZED = false;
    private static final Boolean UPDATED_HOSPITALIZED = true;

    private static final Boolean DEFAULT_DELETED = false;
    private static final Boolean UPDATED_DELETED = true;

    @Autowired
    private FinalAssessmentRepository finalAssessmentRepository;

    @Autowired
    private FinalAssessmentMapper finalAssessmentMapper;

    @Autowired
    private FinalAssessmentService finalAssessmentService;

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

    private MockMvc restFinalAssessmentMockMvc;

    private FinalAssessment finalAssessment;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FinalAssessmentResource finalAssessmentResource = new FinalAssessmentResource(finalAssessmentService);
        this.restFinalAssessmentMockMvc = MockMvcBuilders.standaloneSetup(finalAssessmentResource)
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
    public static FinalAssessment createEntity(EntityManager em) {
        FinalAssessment finalAssessment = new FinalAssessment()
            .smoking(DEFAULT_SMOKING)
            .weight(DEFAULT_WEIGHT)
            .size(DEFAULT_SIZE)
            .iMC(DEFAULT_I_MC)
            .hbiac(DEFAULT_HBIAC)
            .baselineFunctionalCapacity(DEFAULT_BASELINE_FUNCTIONAL_CAPACITY)
            .lDL(DEFAULT_L_DL)
            .hDL(DEFAULT_H_DL)
            .cardiovascularRisk(DEFAULT_CARDIOVASCULAR_RISK)
            .isWorking(DEFAULT_IS_WORKING)
            .deceased(DEFAULT_DECEASED)
            .abandonment(DEFAULT_ABANDONMENT)
            .abandonmentMedicCause(DEFAULT_ABANDONMENT_MEDIC_CAUSE)
            .hospitalized(DEFAULT_HOSPITALIZED)
            .deleted(DEFAULT_DELETED);
        return finalAssessment;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FinalAssessment createUpdatedEntity(EntityManager em) {
        FinalAssessment finalAssessment = new FinalAssessment()
            .smoking(UPDATED_SMOKING)
            .weight(UPDATED_WEIGHT)
            .size(UPDATED_SIZE)
            .iMC(UPDATED_I_MC)
            .hbiac(UPDATED_HBIAC)
            .baselineFunctionalCapacity(UPDATED_BASELINE_FUNCTIONAL_CAPACITY)
            .lDL(UPDATED_L_DL)
            .hDL(UPDATED_H_DL)
            .cardiovascularRisk(UPDATED_CARDIOVASCULAR_RISK)
            .isWorking(UPDATED_IS_WORKING)
            .deceased(UPDATED_DECEASED)
            .abandonment(UPDATED_ABANDONMENT)
            .abandonmentMedicCause(UPDATED_ABANDONMENT_MEDIC_CAUSE)
            .hospitalized(UPDATED_HOSPITALIZED)
            .deleted(UPDATED_DELETED);
        return finalAssessment;
    }

    @BeforeEach
    public void initTest() {
        finalAssessment = createEntity(em);
    }

    @Test
    @Transactional
    public void createFinalAssessment() throws Exception {
        int databaseSizeBeforeCreate = finalAssessmentRepository.findAll().size();

        // Create the FinalAssessment
        FinalAssessmentDTO finalAssessmentDTO = finalAssessmentMapper.toDto(finalAssessment);
        restFinalAssessmentMockMvc.perform(post("/api/final-assessments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(finalAssessmentDTO)))
            .andExpect(status().isCreated());

        // Validate the FinalAssessment in the database
        List<FinalAssessment> finalAssessmentList = finalAssessmentRepository.findAll();
        assertThat(finalAssessmentList).hasSize(databaseSizeBeforeCreate + 1);
        FinalAssessment testFinalAssessment = finalAssessmentList.get(finalAssessmentList.size() - 1);
        assertThat(testFinalAssessment.getSmoking()).isEqualTo(DEFAULT_SMOKING);
        assertThat(testFinalAssessment.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testFinalAssessment.getSize()).isEqualTo(DEFAULT_SIZE);
        assertThat(testFinalAssessment.getiMC()).isEqualTo(DEFAULT_I_MC);
        assertThat(testFinalAssessment.getHbiac()).isEqualTo(DEFAULT_HBIAC);
        assertThat(testFinalAssessment.getBaselineFunctionalCapacity()).isEqualTo(DEFAULT_BASELINE_FUNCTIONAL_CAPACITY);
        assertThat(testFinalAssessment.getlDL()).isEqualTo(DEFAULT_L_DL);
        assertThat(testFinalAssessment.gethDL()).isEqualTo(DEFAULT_H_DL);
        assertThat(testFinalAssessment.getCardiovascularRisk()).isEqualTo(DEFAULT_CARDIOVASCULAR_RISK);
        assertThat(testFinalAssessment.isIsWorking()).isEqualTo(DEFAULT_IS_WORKING);
        assertThat(testFinalAssessment.isDeceased()).isEqualTo(DEFAULT_DECEASED);
        assertThat(testFinalAssessment.isAbandonment()).isEqualTo(DEFAULT_ABANDONMENT);
        assertThat(testFinalAssessment.isAbandonmentMedicCause()).isEqualTo(DEFAULT_ABANDONMENT_MEDIC_CAUSE);
        assertThat(testFinalAssessment.isHospitalized()).isEqualTo(DEFAULT_HOSPITALIZED);
        assertThat(testFinalAssessment.isDeleted()).isEqualTo(DEFAULT_DELETED);
    }

    @Test
    @Transactional
    public void createFinalAssessmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = finalAssessmentRepository.findAll().size();

        // Create the FinalAssessment with an existing ID
        finalAssessment.setId(1L);
        FinalAssessmentDTO finalAssessmentDTO = finalAssessmentMapper.toDto(finalAssessment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFinalAssessmentMockMvc.perform(post("/api/final-assessments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(finalAssessmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FinalAssessment in the database
        List<FinalAssessment> finalAssessmentList = finalAssessmentRepository.findAll();
        assertThat(finalAssessmentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFinalAssessments() throws Exception {
        // Initialize the database
        finalAssessmentRepository.saveAndFlush(finalAssessment);

        // Get all the finalAssessmentList
        restFinalAssessmentMockMvc.perform(get("/api/final-assessments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(finalAssessment.getId().intValue())))
            .andExpect(jsonPath("$.[*].smoking").value(hasItem(DEFAULT_SMOKING)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE)))
            .andExpect(jsonPath("$.[*].iMC").value(hasItem(DEFAULT_I_MC)))
            .andExpect(jsonPath("$.[*].hbiac").value(hasItem(DEFAULT_HBIAC)))
            .andExpect(jsonPath("$.[*].baselineFunctionalCapacity").value(hasItem(DEFAULT_BASELINE_FUNCTIONAL_CAPACITY)))
            .andExpect(jsonPath("$.[*].lDL").value(hasItem(DEFAULT_L_DL)))
            .andExpect(jsonPath("$.[*].hDL").value(hasItem(DEFAULT_H_DL)))
            .andExpect(jsonPath("$.[*].cardiovascularRisk").value(hasItem(DEFAULT_CARDIOVASCULAR_RISK)))
            .andExpect(jsonPath("$.[*].isWorking").value(hasItem(DEFAULT_IS_WORKING.booleanValue())))
            .andExpect(jsonPath("$.[*].deceased").value(hasItem(DEFAULT_DECEASED.booleanValue())))
            .andExpect(jsonPath("$.[*].abandonment").value(hasItem(DEFAULT_ABANDONMENT.booleanValue())))
            .andExpect(jsonPath("$.[*].abandonmentMedicCause").value(hasItem(DEFAULT_ABANDONMENT_MEDIC_CAUSE.booleanValue())))
            .andExpect(jsonPath("$.[*].hospitalized").value(hasItem(DEFAULT_HOSPITALIZED.booleanValue())))
            .andExpect(jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getFinalAssessment() throws Exception {
        // Initialize the database
        finalAssessmentRepository.saveAndFlush(finalAssessment);

        // Get the finalAssessment
        restFinalAssessmentMockMvc.perform(get("/api/final-assessments/{id}", finalAssessment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(finalAssessment.getId().intValue()))
            .andExpect(jsonPath("$.smoking").value(DEFAULT_SMOKING))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT))
            .andExpect(jsonPath("$.size").value(DEFAULT_SIZE))
            .andExpect(jsonPath("$.iMC").value(DEFAULT_I_MC))
            .andExpect(jsonPath("$.hbiac").value(DEFAULT_HBIAC))
            .andExpect(jsonPath("$.baselineFunctionalCapacity").value(DEFAULT_BASELINE_FUNCTIONAL_CAPACITY))
            .andExpect(jsonPath("$.lDL").value(DEFAULT_L_DL))
            .andExpect(jsonPath("$.hDL").value(DEFAULT_H_DL))
            .andExpect(jsonPath("$.cardiovascularRisk").value(DEFAULT_CARDIOVASCULAR_RISK))
            .andExpect(jsonPath("$.isWorking").value(DEFAULT_IS_WORKING.booleanValue()))
            .andExpect(jsonPath("$.deceased").value(DEFAULT_DECEASED.booleanValue()))
            .andExpect(jsonPath("$.abandonment").value(DEFAULT_ABANDONMENT.booleanValue()))
            .andExpect(jsonPath("$.abandonmentMedicCause").value(DEFAULT_ABANDONMENT_MEDIC_CAUSE.booleanValue()))
            .andExpect(jsonPath("$.hospitalized").value(DEFAULT_HOSPITALIZED.booleanValue()))
            .andExpect(jsonPath("$.deleted").value(DEFAULT_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingFinalAssessment() throws Exception {
        // Get the finalAssessment
        restFinalAssessmentMockMvc.perform(get("/api/final-assessments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFinalAssessment() throws Exception {
        // Initialize the database
        finalAssessmentRepository.saveAndFlush(finalAssessment);

        int databaseSizeBeforeUpdate = finalAssessmentRepository.findAll().size();

        // Update the finalAssessment
        FinalAssessment updatedFinalAssessment = finalAssessmentRepository.findById(finalAssessment.getId()).get();
        // Disconnect from session so that the updates on updatedFinalAssessment are not directly saved in db
        em.detach(updatedFinalAssessment);
        updatedFinalAssessment
            .smoking(UPDATED_SMOKING)
            .weight(UPDATED_WEIGHT)
            .size(UPDATED_SIZE)
            .iMC(UPDATED_I_MC)
            .hbiac(UPDATED_HBIAC)
            .baselineFunctionalCapacity(UPDATED_BASELINE_FUNCTIONAL_CAPACITY)
            .lDL(UPDATED_L_DL)
            .hDL(UPDATED_H_DL)
            .cardiovascularRisk(UPDATED_CARDIOVASCULAR_RISK)
            .isWorking(UPDATED_IS_WORKING)
            .deceased(UPDATED_DECEASED)
            .abandonment(UPDATED_ABANDONMENT)
            .abandonmentMedicCause(UPDATED_ABANDONMENT_MEDIC_CAUSE)
            .hospitalized(UPDATED_HOSPITALIZED)
            .deleted(UPDATED_DELETED);
        FinalAssessmentDTO finalAssessmentDTO = finalAssessmentMapper.toDto(updatedFinalAssessment);

        restFinalAssessmentMockMvc.perform(put("/api/final-assessments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(finalAssessmentDTO)))
            .andExpect(status().isOk());

        // Validate the FinalAssessment in the database
        List<FinalAssessment> finalAssessmentList = finalAssessmentRepository.findAll();
        assertThat(finalAssessmentList).hasSize(databaseSizeBeforeUpdate);
        FinalAssessment testFinalAssessment = finalAssessmentList.get(finalAssessmentList.size() - 1);
        assertThat(testFinalAssessment.getSmoking()).isEqualTo(UPDATED_SMOKING);
        assertThat(testFinalAssessment.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testFinalAssessment.getSize()).isEqualTo(UPDATED_SIZE);
        assertThat(testFinalAssessment.getiMC()).isEqualTo(UPDATED_I_MC);
        assertThat(testFinalAssessment.getHbiac()).isEqualTo(UPDATED_HBIAC);
        assertThat(testFinalAssessment.getBaselineFunctionalCapacity()).isEqualTo(UPDATED_BASELINE_FUNCTIONAL_CAPACITY);
        assertThat(testFinalAssessment.getlDL()).isEqualTo(UPDATED_L_DL);
        assertThat(testFinalAssessment.gethDL()).isEqualTo(UPDATED_H_DL);
        assertThat(testFinalAssessment.getCardiovascularRisk()).isEqualTo(UPDATED_CARDIOVASCULAR_RISK);
        assertThat(testFinalAssessment.isIsWorking()).isEqualTo(UPDATED_IS_WORKING);
        assertThat(testFinalAssessment.isDeceased()).isEqualTo(UPDATED_DECEASED);
        assertThat(testFinalAssessment.isAbandonment()).isEqualTo(UPDATED_ABANDONMENT);
        assertThat(testFinalAssessment.isAbandonmentMedicCause()).isEqualTo(UPDATED_ABANDONMENT_MEDIC_CAUSE);
        assertThat(testFinalAssessment.isHospitalized()).isEqualTo(UPDATED_HOSPITALIZED);
        assertThat(testFinalAssessment.isDeleted()).isEqualTo(UPDATED_DELETED);
    }

    @Test
    @Transactional
    public void updateNonExistingFinalAssessment() throws Exception {
        int databaseSizeBeforeUpdate = finalAssessmentRepository.findAll().size();

        // Create the FinalAssessment
        FinalAssessmentDTO finalAssessmentDTO = finalAssessmentMapper.toDto(finalAssessment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFinalAssessmentMockMvc.perform(put("/api/final-assessments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(finalAssessmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FinalAssessment in the database
        List<FinalAssessment> finalAssessmentList = finalAssessmentRepository.findAll();
        assertThat(finalAssessmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFinalAssessment() throws Exception {
        // Initialize the database
        finalAssessmentRepository.saveAndFlush(finalAssessment);

        int databaseSizeBeforeDelete = finalAssessmentRepository.findAll().size();

        // Delete the finalAssessment
        restFinalAssessmentMockMvc.perform(delete("/api/final-assessments/{id}", finalAssessment.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FinalAssessment> finalAssessmentList = finalAssessmentRepository.findAll();
        assertThat(finalAssessmentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FinalAssessment.class);
        FinalAssessment finalAssessment1 = new FinalAssessment();
        finalAssessment1.setId(1L);
        FinalAssessment finalAssessment2 = new FinalAssessment();
        finalAssessment2.setId(finalAssessment1.getId());
        assertThat(finalAssessment1).isEqualTo(finalAssessment2);
        finalAssessment2.setId(2L);
        assertThat(finalAssessment1).isNotEqualTo(finalAssessment2);
        finalAssessment1.setId(null);
        assertThat(finalAssessment1).isNotEqualTo(finalAssessment2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FinalAssessmentDTO.class);
        FinalAssessmentDTO finalAssessmentDTO1 = new FinalAssessmentDTO();
        finalAssessmentDTO1.setId(1L);
        FinalAssessmentDTO finalAssessmentDTO2 = new FinalAssessmentDTO();
        assertThat(finalAssessmentDTO1).isNotEqualTo(finalAssessmentDTO2);
        finalAssessmentDTO2.setId(finalAssessmentDTO1.getId());
        assertThat(finalAssessmentDTO1).isEqualTo(finalAssessmentDTO2);
        finalAssessmentDTO2.setId(2L);
        assertThat(finalAssessmentDTO1).isNotEqualTo(finalAssessmentDTO2);
        finalAssessmentDTO1.setId(null);
        assertThat(finalAssessmentDTO1).isNotEqualTo(finalAssessmentDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(finalAssessmentMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(finalAssessmentMapper.fromId(null)).isNull();
    }
}
