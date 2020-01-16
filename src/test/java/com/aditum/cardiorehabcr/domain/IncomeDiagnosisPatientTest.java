package com.aditum.cardiorehabcr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class IncomeDiagnosisPatientTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(IncomeDiagnosisPatient.class);
        IncomeDiagnosisPatient incomeDiagnosisPatient1 = new IncomeDiagnosisPatient();
        incomeDiagnosisPatient1.setId(1L);
        IncomeDiagnosisPatient incomeDiagnosisPatient2 = new IncomeDiagnosisPatient();
        incomeDiagnosisPatient2.setId(incomeDiagnosisPatient1.getId());
        assertThat(incomeDiagnosisPatient1).isEqualTo(incomeDiagnosisPatient2);
        incomeDiagnosisPatient2.setId(2L);
        assertThat(incomeDiagnosisPatient1).isNotEqualTo(incomeDiagnosisPatient2);
        incomeDiagnosisPatient1.setId(null);
        assertThat(incomeDiagnosisPatient1).isNotEqualTo(incomeDiagnosisPatient2);
    }
}
