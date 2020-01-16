package com.aditum.cardiorehabcr.repository;

import com.aditum.cardiorehabcr.domain.ComorbiditiesPatient;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the ComorbiditiesPatient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComorbiditiesPatientRepository extends JpaRepository<ComorbiditiesPatient, Long> {

    List<ComorbiditiesPatient> findByInitialAssessmentId(Long initialAssessmentId);

    Optional<ComorbiditiesPatient> findFirstByInitialAssessmentIdAndComorbiditietId(Long initialAssessmentId,Long comorbiditietId);

}
