package com.aditum.cardiorehabcr.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class IncomeDiagnosisPatientDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(IncomeDiagnosisPatientDTO.class);
        IncomeDiagnosisPatientDTO incomeDiagnosisPatientDTO1 = new IncomeDiagnosisPatientDTO();
        incomeDiagnosisPatientDTO1.setId(1L);
        IncomeDiagnosisPatientDTO incomeDiagnosisPatientDTO2 = new IncomeDiagnosisPatientDTO();
        assertThat(incomeDiagnosisPatientDTO1).isNotEqualTo(incomeDiagnosisPatientDTO2);
        incomeDiagnosisPatientDTO2.setId(incomeDiagnosisPatientDTO1.getId());
        assertThat(incomeDiagnosisPatientDTO1).isEqualTo(incomeDiagnosisPatientDTO2);
        incomeDiagnosisPatientDTO2.setId(2L);
        assertThat(incomeDiagnosisPatientDTO1).isNotEqualTo(incomeDiagnosisPatientDTO2);
        incomeDiagnosisPatientDTO1.setId(null);
        assertThat(incomeDiagnosisPatientDTO1).isNotEqualTo(incomeDiagnosisPatientDTO2);
    }
}
