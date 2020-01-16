package com.aditum.cardiorehabcr.repository;

import com.aditum.cardiorehabcr.domain.IncomeDiagnosis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the IncomeDiagnosis entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IncomeDiagnosisRepository extends JpaRepository<IncomeDiagnosis, Long> {


    Page<IncomeDiagnosis> findByRehabilitationCenterIdAndAndDeleted(Pageable pageable, Long rehabilitationId, boolean deleted);


}
