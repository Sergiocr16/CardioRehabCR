package com.aditum.cardiorehabcr.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class NonSpecificPainDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NonSpecificPainDTO.class);
        NonSpecificPainDTO nonSpecificPainDTO1 = new NonSpecificPainDTO();
        nonSpecificPainDTO1.setId(1L);
        NonSpecificPainDTO nonSpecificPainDTO2 = new NonSpecificPainDTO();
        assertThat(nonSpecificPainDTO1).isNotEqualTo(nonSpecificPainDTO2);
        nonSpecificPainDTO2.setId(nonSpecificPainDTO1.getId());
        assertThat(nonSpecificPainDTO1).isEqualTo(nonSpecificPainDTO2);
        nonSpecificPainDTO2.setId(2L);
        assertThat(nonSpecificPainDTO1).isNotEqualTo(nonSpecificPainDTO2);
        nonSpecificPainDTO1.setId(null);
        assertThat(nonSpecificPainDTO1).isNotEqualTo(nonSpecificPainDTO2);
    }
}
