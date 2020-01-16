package com.aditum.cardiorehabcr.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class MayorEventDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MayorEventDTO.class);
        MayorEventDTO mayorEventDTO1 = new MayorEventDTO();
        mayorEventDTO1.setId(1L);
        MayorEventDTO mayorEventDTO2 = new MayorEventDTO();
        assertThat(mayorEventDTO1).isNotEqualTo(mayorEventDTO2);
        mayorEventDTO2.setId(mayorEventDTO1.getId());
        assertThat(mayorEventDTO1).isEqualTo(mayorEventDTO2);
        mayorEventDTO2.setId(2L);
        assertThat(mayorEventDTO1).isNotEqualTo(mayorEventDTO2);
        mayorEventDTO1.setId(null);
        assertThat(mayorEventDTO1).isNotEqualTo(mayorEventDTO2);
    }
}
