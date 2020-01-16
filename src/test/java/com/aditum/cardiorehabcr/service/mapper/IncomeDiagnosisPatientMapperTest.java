package com.aditum.cardiorehabcr.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class IncomeDiagnosisPatientMapperTest {

    private IncomeDiagnosisPatientMapper incomeDiagnosisPatientMapper;

    @BeforeEach
    public void setUp() {
        incomeDiagnosisPatientMapper = new IncomeDiagnosisPatientMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(incomeDiagnosisPatientMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(incomeDiagnosisPatientMapper.fromId(null)).isNull();
    }
}
