package com.aditum.cardiorehabcr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aditum.cardiorehabcr.web.rest.TestUtil;

public class NonSpecificPainTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NonSpecificPain.class);
        NonSpecificPain nonSpecificPain1 = new NonSpecificPain();
        nonSpecificPain1.setId(1L);
        NonSpecificPain nonSpecificPain2 = new NonSpecificPain();
        nonSpecificPain2.setId(nonSpecificPain1.getId());
        assertThat(nonSpecificPain1).isEqualTo(nonSpecificPain2);
        nonSpecificPain2.setId(2L);
        assertThat(nonSpecificPain1).isNotEqualTo(nonSpecificPain2);
        nonSpecificPain1.setId(null);
        assertThat(nonSpecificPain1).isNotEqualTo(nonSpecificPain2);
    }
}
