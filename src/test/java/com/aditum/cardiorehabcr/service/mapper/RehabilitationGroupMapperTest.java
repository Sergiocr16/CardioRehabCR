package com.aditum.cardiorehabcr.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class RehabilitationGroupMapperTest {

    private RehabilitationGroupMapper rehabilitationGroupMapper;

    @BeforeEach
    public void setUp() {
        rehabilitationGroupMapper = new RehabilitationGroupMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(rehabilitationGroupMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(rehabilitationGroupMapper.fromId(null)).isNull();
    }
}
