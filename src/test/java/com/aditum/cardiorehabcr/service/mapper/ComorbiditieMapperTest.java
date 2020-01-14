package com.aditum.cardiorehabcr.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ComorbiditieMapperTest {

    private ComorbiditieMapper comorbiditieMapper;

    @BeforeEach
    public void setUp() {
        comorbiditieMapper = new ComorbiditieMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(comorbiditieMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(comorbiditieMapper.fromId(null)).isNull();
    }
}
