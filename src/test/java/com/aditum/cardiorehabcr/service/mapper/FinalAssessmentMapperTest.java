package com.aditum.cardiorehabcr.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class FinalAssessmentMapperTest {

    private FinalAssessmentMapper finalAssessmentMapper;

    @BeforeEach
    public void setUp() {
        finalAssessmentMapper = new FinalAssessmentMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(finalAssessmentMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(finalAssessmentMapper.fromId(null)).isNull();
    }
}
