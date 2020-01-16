package com.aditum.cardiorehabcr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class RehabilitationGroupTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RehabilitationGroup.class);
        RehabilitationGroup rehabilitationGroup1 = new RehabilitationGroup();
        rehabilitationGroup1.setId(1L);
        RehabilitationGroup rehabilitationGroup2 = new RehabilitationGroup();
        rehabilitationGroup2.setId(rehabilitationGroup1.getId());
        assertThat(rehabilitationGroup1).isEqualTo(rehabilitationGroup2);
        rehabilitationGroup2.setId(2L);
        assertThat(rehabilitationGroup1).isNotEqualTo(rehabilitationGroup2);
        rehabilitationGroup1.setId(null);
        assertThat(rehabilitationGroup1).isNotEqualTo(rehabilitationGroup2);
    }
}
