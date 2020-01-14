package com.aditum.cardiorehabcr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class DepressiveSymptomTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DepressiveSymptom.class);
        DepressiveSymptom depressiveSymptom1 = new DepressiveSymptom();
        depressiveSymptom1.setId(1L);
        DepressiveSymptom depressiveSymptom2 = new DepressiveSymptom();
        depressiveSymptom2.setId(depressiveSymptom1.getId());
        assertThat(depressiveSymptom1).isEqualTo(depressiveSymptom2);
        depressiveSymptom2.setId(2L);
        assertThat(depressiveSymptom1).isNotEqualTo(depressiveSymptom2);
        depressiveSymptom1.setId(null);
        assertThat(depressiveSymptom1).isNotEqualTo(depressiveSymptom2);
    }
}
