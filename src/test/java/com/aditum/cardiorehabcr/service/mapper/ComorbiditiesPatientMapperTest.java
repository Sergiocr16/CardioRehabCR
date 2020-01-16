package com.aditum.cardiorehabcr.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ComorbiditiesPatientMapperTest {

    private ComorbiditiesPatientMapper comorbiditiesPatientMapper;

    @BeforeEach
    public void setUp() {
        comorbiditiesPatientMapper = new ComorbiditiesPatientMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(comorbiditiesPatientMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(comorbiditiesPatientMapper.fromId(null)).isNull();
    }
}
