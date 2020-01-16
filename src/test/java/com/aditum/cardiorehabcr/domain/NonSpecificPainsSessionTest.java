package com.aditum.cardiorehabcr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class NonSpecificPainsSessionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NonSpecificPainsSession.class);
        NonSpecificPainsSession nonSpecificPainsSession1 = new NonSpecificPainsSession();
        nonSpecificPainsSession1.setId(1L);
        NonSpecificPainsSession nonSpecificPainsSession2 = new NonSpecificPainsSession();
        nonSpecificPainsSession2.setId(nonSpecificPainsSession1.getId());
        assertThat(nonSpecificPainsSession1).isEqualTo(nonSpecificPainsSession2);
        nonSpecificPainsSession2.setId(2L);
        assertThat(nonSpecificPainsSession1).isNotEqualTo(nonSpecificPainsSession2);
        nonSpecificPainsSession1.setId(null);
        assertThat(nonSpecificPainsSession1).isNotEqualTo(nonSpecificPainsSession2);
    }
}
