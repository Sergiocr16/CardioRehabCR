package com.aditum.cardiorehabcr.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class RehabilitationCenterDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RehabilitationCenterDTO.class);
        RehabilitationCenterDTO rehabilitationCenterDTO1 = new RehabilitationCenterDTO();
        rehabilitationCenterDTO1.setId(1L);
        RehabilitationCenterDTO rehabilitationCenterDTO2 = new RehabilitationCenterDTO();
        assertThat(rehabilitationCenterDTO1).isNotEqualTo(rehabilitationCenterDTO2);
        rehabilitationCenterDTO2.setId(rehabilitationCenterDTO1.getId());
        assertThat(rehabilitationCenterDTO1).isEqualTo(rehabilitationCenterDTO2);
        rehabilitationCenterDTO2.setId(2L);
        assertThat(rehabilitationCenterDTO1).isNotEqualTo(rehabilitationCenterDTO2);
        rehabilitationCenterDTO1.setId(null);
        assertThat(rehabilitationCenterDTO1).isNotEqualTo(rehabilitationCenterDTO2);
    }
}
