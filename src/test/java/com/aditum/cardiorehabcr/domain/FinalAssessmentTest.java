package com.aditum.cardiorehabcr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class FinalAssessmentTest {

    @Test
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
}
