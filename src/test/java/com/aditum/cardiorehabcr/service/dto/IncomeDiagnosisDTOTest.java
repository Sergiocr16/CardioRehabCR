package com.aditum.cardiorehabcr.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class IncomeDiagnosisDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(IncomeDiagnosisDTO.class);
        IncomeDiagnosisDTO incomeDiagnosisDTO1 = new IncomeDiagnosisDTO();
        incomeDiagnosisDTO1.setId(1L);
        IncomeDiagnosisDTO incomeDiagnosisDTO2 = new IncomeDiagnosisDTO();
        assertThat(incomeDiagnosisDTO1).isNotEqualTo(incomeDiagnosisDTO2);
        incomeDiagnosisDTO2.setId(incomeDiagnosisDTO1.getId());
        assertThat(incomeDiagnosisDTO1).isEqualTo(incomeDiagnosisDTO2);
        incomeDiagnosisDTO2.setId(2L);
        assertThat(incomeDiagnosisDTO1).isNotEqualTo(incomeDiagnosisDTO2);
        incomeDiagnosisDTO1.setId(null);
        assertThat(incomeDiagnosisDTO1).isNotEqualTo(incomeDiagnosisDTO2);
    }
}
