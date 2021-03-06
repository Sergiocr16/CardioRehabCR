package com.aditum.cardiorehabcr.repository;
import com.aditum.cardiorehabcr.domain.MinorEventsSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;



/**
 * Spring Data  repository for the MinorEventsSession entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MinorEventsSessionRepository extends JpaRepository<MinorEventsSession, Long> {

    Page<MinorEventsSession> findAllBySessionId(Pageable page, Long sessionId);

}
