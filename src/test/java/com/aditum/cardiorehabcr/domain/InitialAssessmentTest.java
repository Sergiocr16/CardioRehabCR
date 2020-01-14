package com.aditum.cardiorehabcr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class InitialAssessmentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InitialAssessment.class);
        InitialAssessment initialAssessment1 = new InitialAssessment();
        initialAssessment1.setId(1L);
        InitialAssessment initialAssessment2 = new InitialAssessment();
        initialAssessment2.setId(initialAssessment1.getId());
        assertThat(initialAssessment1).isEqualTo(initialAssessment2);
        initialAssessment2.setId(2L);
        assertThat(initialAssessment1).isNotEqualTo(initialAssessment2);
        initialAssessment1.setId(null);
        assertThat(initialAssessment1).isNotEqualTo(initialAssessment2);
    }
}
