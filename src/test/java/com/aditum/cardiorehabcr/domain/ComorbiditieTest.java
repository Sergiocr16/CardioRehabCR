package com.aditum.cardiorehabcr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class ComorbiditieTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Comorbiditie.class);
        Comorbiditie comorbiditie1 = new Comorbiditie();
        comorbiditie1.setId(1L);
        Comorbiditie comorbiditie2 = new Comorbiditie();
        comorbiditie2.setId(comorbiditie1.getId());
        assertThat(comorbiditie1).isEqualTo(comorbiditie2);
        comorbiditie2.setId(2L);
        assertThat(comorbiditie1).isNotEqualTo(comorbiditie2);
        comorbiditie1.setId(null);
        assertThat(comorbiditie1).isNotEqualTo(comorbiditie2);
    }
}
