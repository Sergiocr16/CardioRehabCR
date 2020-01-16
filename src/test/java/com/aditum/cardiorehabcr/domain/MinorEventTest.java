package com.aditum.cardiorehabcr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class MinorEventTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MinorEvent.class);
        MinorEvent minorEvent1 = new MinorEvent();
        minorEvent1.setId(1L);
        MinorEvent minorEvent2 = new MinorEvent();
        minorEvent2.setId(minorEvent1.getId());
        assertThat(minorEvent1).isEqualTo(minorEvent2);
        minorEvent2.setId(2L);
        assertThat(minorEvent1).isNotEqualTo(minorEvent2);
        minorEvent1.setId(null);
        assertThat(minorEvent1).isNotEqualTo(minorEvent2);
    }
}
