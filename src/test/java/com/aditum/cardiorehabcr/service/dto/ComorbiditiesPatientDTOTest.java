package com.aditum.cardiorehabcr.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class ComorbiditiesPatientDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ComorbiditiesPatientDTO.class);
        ComorbiditiesPatientDTO comorbiditiesPatientDTO1 = new ComorbiditiesPatientDTO();
        comorbiditiesPatientDTO1.setId(1L);
        ComorbiditiesPatientDTO comorbiditiesPatientDTO2 = new ComorbiditiesPatientDTO();
        assertThat(comorbiditiesPatientDTO1).isNotEqualTo(comorbiditiesPatientDTO2);
        comorbiditiesPatientDTO2.setId(comorbiditiesPatientDTO1.getId());
        assertThat(comorbiditiesPatientDTO1).isEqualTo(comorbiditiesPatientDTO2);
        comorbiditiesPatientDTO2.setId(2L);
        assertThat(comorbiditiesPatientDTO1).isNotEqualTo(comorbiditiesPatientDTO2);
        comorbiditiesPatientDTO1.setId(null);
        assertThat(comorbiditiesPatientDTO1).isNotEqualTo(comorbiditiesPatientDTO2);
    }
}
