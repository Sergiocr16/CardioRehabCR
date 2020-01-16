package com.aditum.cardiorehabcr.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class InitialAssessmentDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InitialAssessmentDTO.class);
        InitialAssessmentDTO initialAssessmentDTO1 = new InitialAssessmentDTO();
        initialAssessmentDTO1.setId(1L);
        InitialAssessmentDTO initialAssessmentDTO2 = new InitialAssessmentDTO();
        assertThat(initialAssessmentDTO1).isNotEqualTo(initialAssessmentDTO2);
        initialAssessmentDTO2.setId(initialAssessmentDTO1.getId());
        assertThat(initialAssessmentDTO1).isEqualTo(initialAssessmentDTO2);
        initialAssessmentDTO2.setId(2L);
        assertThat(initialAssessmentDTO1).isNotEqualTo(initialAssessmentDTO2);
        initialAssessmentDTO1.setId(null);
        assertThat(initialAssessmentDTO1).isNotEqualTo(initialAssessmentDTO2);
    }
}
