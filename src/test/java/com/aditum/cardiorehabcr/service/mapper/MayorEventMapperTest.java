package com.aditum.cardiorehabcr.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class MayorEventMapperTest {

    private MayorEventMapper mayorEventMapper;

    @BeforeEach
    public void setUp() {
        mayorEventMapper = new MayorEventMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(mayorEventMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(mayorEventMapper.fromId(null)).isNull();
    }
}
