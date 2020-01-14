package com.aditum.cardiorehabcr.repository;

import com.aditum.cardiorehabcr.domain.IncomeDiagnosisPatient;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the IncomeDiagnosisPatient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IncomeDiagnosisPatientRepository extends JpaRepository<IncomeDiagnosisPatient, Long> {

}
