package com.aditum.cardiorehabcr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class MayorEventsSessionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MayorEventsSession.class);
        MayorEventsSession mayorEventsSession1 = new MayorEventsSession();
        mayorEventsSession1.setId(1L);
        MayorEventsSession mayorEventsSession2 = new MayorEventsSession();
        mayorEventsSession2.setId(mayorEventsSession1.getId());
        assertThat(mayorEventsSession1).isEqualTo(mayorEventsSession2);
        mayorEventsSession2.setId(2L);
        assertThat(mayorEventsSession1).isNotEqualTo(mayorEventsSession2);
        mayorEventsSession1.setId(null);
        assertThat(mayorEventsSession1).isNotEqualTo(mayorEventsSession2);
    }
}
