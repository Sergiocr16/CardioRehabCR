package com.aditum.cardiorehabcr.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class MinorEventMapperTest {

    private MinorEventMapper minorEventMapper;

    @BeforeEach
    public void setUp() {
        minorEventMapper = new MinorEventMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(minorEventMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(minorEventMapper.fromId(null)).isNull();
    }
}
