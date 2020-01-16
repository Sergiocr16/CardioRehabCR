package com.aditum.cardiorehabcr.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DepressiveSymptomsSessionMapperTest {

    private DepressiveSymptomsSessionMapper depressiveSymptomsSessionMapper;

    @BeforeEach
    public void setUp() {
        depressiveSymptomsSessionMapper = new DepressiveSymptomsSessionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(depressiveSymptomsSessionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(depressiveSymptomsSessionMapper.fromId(null)).isNull();
    }
}
