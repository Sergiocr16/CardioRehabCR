package com.aditum.cardiorehabcr.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class InitialAssessmentMapperTest {

    private InitialAssessmentMapper initialAssessmentMapper;

    @BeforeEach
    public void setUp() {
        initialAssessmentMapper = new InitialAssessmentMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(initialAssessmentMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(initialAssessmentMapper.fromId(null)).isNull();
    }
}
