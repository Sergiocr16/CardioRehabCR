package com.aditum.cardiorehabcr.repository;

import com.aditum.cardiorehabcr.domain.FinalAssessment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FinalAssessment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FinalAssessmentRepository extends JpaRepository<FinalAssessment, Long> {

}
