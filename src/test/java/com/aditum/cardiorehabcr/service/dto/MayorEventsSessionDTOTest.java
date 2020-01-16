package com.aditum.cardiorehabcr.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class MayorEventsSessionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MayorEventsSessionDTO.class);
        MayorEventsSessionDTO mayorEventsSessionDTO1 = new MayorEventsSessionDTO();
        mayorEventsSessionDTO1.setId(1L);
        MayorEventsSessionDTO mayorEventsSessionDTO2 = new MayorEventsSessionDTO();
        assertThat(mayorEventsSessionDTO1).isNotEqualTo(mayorEventsSessionDTO2);
        mayorEventsSessionDTO2.setId(mayorEventsSessionDTO1.getId());
        assertThat(mayorEventsSessionDTO1).isEqualTo(mayorEventsSessionDTO2);
        mayorEventsSessionDTO2.setId(2L);
        assertThat(mayorEventsSessionDTO1).isNotEqualTo(mayorEventsSessionDTO2);
        mayorEventsSessionDTO1.setId(null);
        assertThat(mayorEventsSessionDTO1).isNotEqualTo(mayorEventsSessionDTO2);
    }
}
