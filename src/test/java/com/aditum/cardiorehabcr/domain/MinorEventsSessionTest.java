package com.aditum.cardiorehabcr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class MinorEventsSessionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MinorEventsSession.class);
        MinorEventsSession minorEventsSession1 = new MinorEventsSession();
        minorEventsSession1.setId(1L);
        MinorEventsSession minorEventsSession2 = new MinorEventsSession();
        minorEventsSession2.setId(minorEventsSession1.getId());
        assertThat(minorEventsSession1).isEqualTo(minorEventsSession2);
        minorEventsSession2.setId(2L);
        assertThat(minorEventsSession1).isNotEqualTo(minorEventsSession2);
        minorEventsSession1.setId(null);
        assertThat(minorEventsSession1).isNotEqualTo(minorEventsSession2);
    }
}
