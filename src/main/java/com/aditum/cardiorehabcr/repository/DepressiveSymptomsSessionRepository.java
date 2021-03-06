package com.aditum.cardiorehabcr.repository;
import com.aditum.cardiorehabcr.domain.DepressiveSymptomsSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DepressiveSymptomsSession entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DepressiveSymptomsSessionRepository extends JpaRepository<DepressiveSymptomsSession, Long> {

    Page<DepressiveSymptomsSession> findAllBySessionId(Pageable page, Long sessionId);

}
