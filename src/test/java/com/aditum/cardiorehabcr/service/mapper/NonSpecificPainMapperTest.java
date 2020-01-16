package com.aditum.cardiorehabcr.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class NonSpecificPainMapperTest {

    private NonSpecificPainMapper nonSpecificPainMapper;

    @BeforeEach
    public void setUp() {
        nonSpecificPainMapper = new NonSpecificPainMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(nonSpecificPainMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(nonSpecificPainMapper.fromId(null)).isNull();
    }
}
