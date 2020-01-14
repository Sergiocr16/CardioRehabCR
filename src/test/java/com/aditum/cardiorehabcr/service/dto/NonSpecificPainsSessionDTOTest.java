package com.aditum.cardiorehabcr.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class NonSpecificPainsSessionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NonSpecificPainsSessionDTO.class);
        NonSpecificPainsSessionDTO nonSpecificPainsSessionDTO1 = new NonSpecificPainsSessionDTO();
        nonSpecificPainsSessionDTO1.setId(1L);
        NonSpecificPainsSessionDTO nonSpecificPainsSessionDTO2 = new NonSpecificPainsSessionDTO();
        assertThat(nonSpecificPainsSessionDTO1).isNotEqualTo(nonSpecificPainsSessionDTO2);
        nonSpecificPainsSessionDTO2.setId(nonSpecificPainsSessionDTO1.getId());
        assertThat(nonSpecificPainsSessionDTO1).isEqualTo(nonSpecificPainsSessionDTO2);
        nonSpecificPainsSessionDTO2.setId(2L);
        assertThat(nonSpecificPainsSessionDTO1).isNotEqualTo(nonSpecificPainsSessionDTO2);
        nonSpecificPainsSessionDTO1.setId(null);
        assertThat(nonSpecificPainsSessionDTO1).isNotEqualTo(nonSpecificPainsSessionDTO2);
    }
}
