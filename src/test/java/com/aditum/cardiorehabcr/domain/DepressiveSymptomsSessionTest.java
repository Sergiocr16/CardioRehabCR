package com.aditum.cardiorehabcr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class DepressiveSymptomsSessionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DepressiveSymptomsSession.class);
        DepressiveSymptomsSession depressiveSymptomsSession1 = new DepressiveSymptomsSession();
        depressiveSymptomsSession1.setId(1L);
        DepressiveSymptomsSession depressiveSymptomsSession2 = new DepressiveSymptomsSession();
        depressiveSymptomsSession2.setId(depressiveSymptomsSession1.getId());
        assertThat(depressiveSymptomsSession1).isEqualTo(depressiveSymptomsSession2);
        depressiveSymptomsSession2.setId(2L);
        assertThat(depressiveSymptomsSession1).isNotEqualTo(depressiveSymptomsSession2);
        depressiveSymptomsSession1.setId(null);
        assertThat(depressiveSymptomsSession1).isNotEqualTo(depressiveSymptomsSession2);
    }
}
