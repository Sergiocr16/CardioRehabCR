package com.aditum.cardiorehabcr.repository;

import com.aditum.cardiorehabcr.domain.IncomeDiagnosisPatient;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the IncomeDiagnosisPatient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IncomeDiagnosisPatientRepository extends JpaRepository<IncomeDiagnosisPatient, Long> {
    List<IncomeDiagnosisPatient> findByInitialAssessmentId(Long initialAssessmentId);
    Optional<IncomeDiagnosisPatient> findFirstByInitialAssessmentIdAndIncomeDiagnosisId(Long initialAssessmentId,Long incomeDiagnosisId);

}
