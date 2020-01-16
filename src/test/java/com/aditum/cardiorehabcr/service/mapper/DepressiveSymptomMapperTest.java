package com.aditum.cardiorehabcr.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DepressiveSymptomMapperTest {

    private DepressiveSymptomMapper depressiveSymptomMapper;

    @BeforeEach
    public void setUp() {
        depressiveSymptomMapper = new DepressiveSymptomMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(depressiveSymptomMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(depressiveSymptomMapper.fromId(null)).isNull();
    }
}
