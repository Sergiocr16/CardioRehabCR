package com.aditum.cardiorehabcr.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class RehabilitationCenterMapperTest {

    private RehabilitationCenterMapper rehabilitationCenterMapper;

    @BeforeEach
    public void setUp() {
        rehabilitationCenterMapper = new RehabilitationCenterMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(rehabilitationCenterMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(rehabilitationCenterMapper.fromId(null)).isNull();
    }
}
