package com.aditum.cardiorehabcr.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class DepressiveSymptomsSessionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DepressiveSymptomsSessionDTO.class);
        DepressiveSymptomsSessionDTO depressiveSymptomsSessionDTO1 = new DepressiveSymptomsSessionDTO();
        depressiveSymptomsSessionDTO1.setId(1L);
        DepressiveSymptomsSessionDTO depressiveSymptomsSessionDTO2 = new DepressiveSymptomsSessionDTO();
        assertThat(depressiveSymptomsSessionDTO1).isNotEqualTo(depressiveSymptomsSessionDTO2);
        depressiveSymptomsSessionDTO2.setId(depressiveSymptomsSessionDTO1.getId());
        assertThat(depressiveSymptomsSessionDTO1).isEqualTo(depressiveSymptomsSessionDTO2);
        depressiveSymptomsSessionDTO2.setId(2L);
        assertThat(depressiveSymptomsSessionDTO1).isNotEqualTo(depressiveSymptomsSessionDTO2);
        depressiveSymptomsSessionDTO1.setId(null);
        assertThat(depressiveSymptomsSessionDTO1).isNotEqualTo(depressiveSymptomsSessionDTO2);
    }
}
