package com.aditum.cardiorehabcr.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class DepressiveSymptomDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DepressiveSymptomDTO.class);
        DepressiveSymptomDTO depressiveSymptomDTO1 = new DepressiveSymptomDTO();
        depressiveSymptomDTO1.setId(1L);
        DepressiveSymptomDTO depressiveSymptomDTO2 = new DepressiveSymptomDTO();
        assertThat(depressiveSymptomDTO1).isNotEqualTo(depressiveSymptomDTO2);
        depressiveSymptomDTO2.setId(depressiveSymptomDTO1.getId());
        assertThat(depressiveSymptomDTO1).isEqualTo(depressiveSymptomDTO2);
        depressiveSymptomDTO2.setId(2L);
        assertThat(depressiveSymptomDTO1).isNotEqualTo(depressiveSymptomDTO2);
        depressiveSymptomDTO1.setId(null);
        assertThat(depressiveSymptomDTO1).isNotEqualTo(depressiveSymptomDTO2);
    }
}
