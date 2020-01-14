package com.aditum.cardiorehabcr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class RehabilitationCenterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RehabilitationCenter.class);
        RehabilitationCenter rehabilitationCenter1 = new RehabilitationCenter();
        rehabilitationCenter1.setId(1L);
        RehabilitationCenter rehabilitationCenter2 = new RehabilitationCenter();
        rehabilitationCenter2.setId(rehabilitationCenter1.getId());
        assertThat(rehabilitationCenter1).isEqualTo(rehabilitationCenter2);
        rehabilitationCenter2.setId(2L);
        assertThat(rehabilitationCenter1).isNotEqualTo(rehabilitationCenter2);
        rehabilitationCenter1.setId(null);
        assertThat(rehabilitationCenter1).isNotEqualTo(rehabilitationCenter2);
    }
}
