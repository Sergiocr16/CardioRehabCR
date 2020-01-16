package com.aditum.cardiorehabcr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class IncomeDiagnosisTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(IncomeDiagnosis.class);
        IncomeDiagnosis incomeDiagnosis1 = new IncomeDiagnosis();
        incomeDiagnosis1.setId(1L);
        IncomeDiagnosis incomeDiagnosis2 = new IncomeDiagnosis();
        incomeDiagnosis2.setId(incomeDiagnosis1.getId());
        assertThat(incomeDiagnosis1).isEqualTo(incomeDiagnosis2);
        incomeDiagnosis2.setId(2L);
        assertThat(incomeDiagnosis1).isNotEqualTo(incomeDiagnosis2);
        incomeDiagnosis1.setId(null);
        assertThat(incomeDiagnosis1).isNotEqualTo(incomeDiagnosis2);
    }
}
