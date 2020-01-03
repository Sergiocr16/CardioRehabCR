package com.aditum.cardiorehabcr.repository;
import com.aditum.cardiorehabcr.domain.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;



/**
 * Spring Data  repository for the Session entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    Page<Session> findAllByPatientId(Pageable page, Long patientId);

}
