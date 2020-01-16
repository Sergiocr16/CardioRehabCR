package com.aditum.cardiorehabcr.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class NonSpecificPainsSessionMapperTest {

    private NonSpecificPainsSessionMapper nonSpecificPainsSessionMapper;

    @BeforeEach
    public void setUp() {
        nonSpecificPainsSessionMapper = new NonSpecificPainsSessionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(nonSpecificPainsSessionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(nonSpecificPainsSessionMapper.fromId(null)).isNull();
    }
}
