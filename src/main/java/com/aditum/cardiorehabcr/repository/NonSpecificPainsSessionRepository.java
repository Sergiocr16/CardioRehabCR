package com.aditum.cardiorehabcr.repository;

import com.aditum.cardiorehabcr.domain.NonSpecificPainsSession;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NonSpecificPainsSession entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NonSpecificPainsSessionRepository extends JpaRepository<NonSpecificPainsSession, Long> {

}
