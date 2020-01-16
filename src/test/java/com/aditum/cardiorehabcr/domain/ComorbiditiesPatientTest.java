package com.aditum.cardiorehabcr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class ComorbiditiesPatientTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ComorbiditiesPatient.class);
        ComorbiditiesPatient comorbiditiesPatient1 = new ComorbiditiesPatient();
        comorbiditiesPatient1.setId(1L);
        ComorbiditiesPatient comorbiditiesPatient2 = new ComorbiditiesPatient();
        comorbiditiesPatient2.setId(comorbiditiesPatient1.getId());
        assertThat(comorbiditiesPatient1).isEqualTo(comorbiditiesPatient2);
        comorbiditiesPatient2.setId(2L);
        assertThat(comorbiditiesPatient1).isNotEqualTo(comorbiditiesPatient2);
        comorbiditiesPatient1.setId(null);
        assertThat(comorbiditiesPatient1).isNotEqualTo(comorbiditiesPatient2);
    }
}
