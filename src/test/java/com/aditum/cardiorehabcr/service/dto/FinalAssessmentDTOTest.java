package com.aditum.cardiorehabcr.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class FinalAssessmentDTOTest {

    @Test
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
}
