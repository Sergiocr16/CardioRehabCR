package com.aditum.cardiorehabcr.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class MinorEventDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MinorEventDTO.class);
        MinorEventDTO minorEventDTO1 = new MinorEventDTO();
        minorEventDTO1.setId(1L);
        MinorEventDTO minorEventDTO2 = new MinorEventDTO();
        assertThat(minorEventDTO1).isNotEqualTo(minorEventDTO2);
        minorEventDTO2.setId(minorEventDTO1.getId());
        assertThat(minorEventDTO1).isEqualTo(minorEventDTO2);
        minorEventDTO2.setId(2L);
        assertThat(minorEventDTO1).isNotEqualTo(minorEventDTO2);
        minorEventDTO1.setId(null);
        assertThat(minorEventDTO1).isNotEqualTo(minorEventDTO2);
    }
}
