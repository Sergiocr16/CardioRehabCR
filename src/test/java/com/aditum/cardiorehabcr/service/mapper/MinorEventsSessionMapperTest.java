package com.aditum.cardiorehabcr.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class MinorEventsSessionMapperTest {

    private MinorEventsSessionMapper minorEventsSessionMapper;

    @BeforeEach
    public void setUp() {
        minorEventsSessionMapper = new MinorEventsSessionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(minorEventsSessionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(minorEventsSessionMapper.fromId(null)).isNull();
    }
}
