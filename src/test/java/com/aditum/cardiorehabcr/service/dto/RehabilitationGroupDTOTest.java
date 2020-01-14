package com.aditum.cardiorehabcr.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class RehabilitationGroupDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RehabilitationGroupDTO.class);
        RehabilitationGroupDTO rehabilitationGroupDTO1 = new RehabilitationGroupDTO();
        rehabilitationGroupDTO1.setId(1L);
        RehabilitationGroupDTO rehabilitationGroupDTO2 = new RehabilitationGroupDTO();
        assertThat(rehabilitationGroupDTO1).isNotEqualTo(rehabilitationGroupDTO2);
        rehabilitationGroupDTO2.setId(rehabilitationGroupDTO1.getId());
        assertThat(rehabilitationGroupDTO1).isEqualTo(rehabilitationGroupDTO2);
        rehabilitationGroupDTO2.setId(2L);
        assertThat(rehabilitationGroupDTO1).isNotEqualTo(rehabilitationGroupDTO2);
        rehabilitationGroupDTO1.setId(null);
        assertThat(rehabilitationGroupDTO1).isNotEqualTo(rehabilitationGroupDTO2);
    }
}
