package com.aditum.cardiorehabcr.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class ComorbiditieDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ComorbiditieDTO.class);
        ComorbiditieDTO comorbiditieDTO1 = new ComorbiditieDTO();
        comorbiditieDTO1.setId(1L);
        ComorbiditieDTO comorbiditieDTO2 = new ComorbiditieDTO();
        assertThat(comorbiditieDTO1).isNotEqualTo(comorbiditieDTO2);
        comorbiditieDTO2.setId(comorbiditieDTO1.getId());
        assertThat(comorbiditieDTO1).isEqualTo(comorbiditieDTO2);
        comorbiditieDTO2.setId(2L);
        assertThat(comorbiditieDTO1).isNotEqualTo(comorbiditieDTO2);
        comorbiditieDTO1.setId(null);
        assertThat(comorbiditieDTO1).isNotEqualTo(comorbiditieDTO2);
    }
}
