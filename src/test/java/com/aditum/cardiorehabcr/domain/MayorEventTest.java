package com.aditum.cardiorehabcr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class MayorEventTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MayorEvent.class);
        MayorEvent mayorEvent1 = new MayorEvent();
        mayorEvent1.setId(1L);
        MayorEvent mayorEvent2 = new MayorEvent();
        mayorEvent2.setId(mayorEvent1.getId());
        assertThat(mayorEvent1).isEqualTo(mayorEvent2);
        mayorEvent2.setId(2L);
        assertThat(mayorEvent1).isNotEqualTo(mayorEvent2);
        mayorEvent1.setId(null);
        assertThat(mayorEvent1).isNotEqualTo(mayorEvent2);
    }
}
