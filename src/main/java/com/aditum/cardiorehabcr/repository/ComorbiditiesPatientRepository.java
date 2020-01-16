package com.aditum.cardiorehabcr.repository;

import com.aditum.cardiorehabcr.domain.ComorbiditiesPatient;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ComorbiditiesPatient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComorbiditiesPatientRepository extends JpaRepository<ComorbiditiesPatient, Long> {

}
