package com.aditum.cardiorehabcr.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class IncomeDiagnosisMapperTest {

    private IncomeDiagnosisMapper incomeDiagnosisMapper;

    @BeforeEach
    public void setUp() {
        incomeDiagnosisMapper = new IncomeDiagnosisMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(incomeDiagnosisMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(incomeDiagnosisMapper.fromId(null)).isNull();
    }
}
