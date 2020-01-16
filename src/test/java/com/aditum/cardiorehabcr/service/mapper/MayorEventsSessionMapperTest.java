package com.aditum.cardiorehabcr.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class MayorEventsSessionMapperTest {

    private MayorEventsSessionMapper mayorEventsSessionMapper;

    @BeforeEach
    public void setUp() {
        mayorEventsSessionMapper = new MayorEventsSessionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(mayorEventsSessionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(mayorEventsSessionMapper.fromId(null)).isNull();
    }
}
