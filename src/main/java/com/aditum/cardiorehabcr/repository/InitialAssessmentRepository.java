package com.aditum.cardiorehabcr.repository;
import com.aditum.cardiorehabcr.domain.InitialAssessment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the InitialAssessment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InitialAssessmentRepository extends JpaRepository<InitialAssessment, Long> {

}
