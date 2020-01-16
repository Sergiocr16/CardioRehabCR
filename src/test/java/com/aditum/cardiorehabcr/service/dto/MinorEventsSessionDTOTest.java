package com.aditum.cardiorehabcr.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class MinorEventsSessionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MinorEventsSessionDTO.class);
        MinorEventsSessionDTO minorEventsSessionDTO1 = new MinorEventsSessionDTO();
        minorEventsSessionDTO1.setId(1L);
        MinorEventsSessionDTO minorEventsSessionDTO2 = new MinorEventsSessionDTO();
        assertThat(minorEventsSessionDTO1).isNotEqualTo(minorEventsSessionDTO2);
        minorEventsSessionDTO2.setId(minorEventsSessionDTO1.getId());
        assertThat(minorEventsSessionDTO1).isEqualTo(minorEventsSessionDTO2);
        minorEventsSessionDTO2.setId(2L);
        assertThat(minorEventsSessionDTO1).isNotEqualTo(minorEventsSessionDTO2);
        minorEventsSessionDTO1.setId(null);
        assertThat(minorEventsSessionDTO1).isNotEqualTo(minorEventsSessionDTO2);
    }
}
