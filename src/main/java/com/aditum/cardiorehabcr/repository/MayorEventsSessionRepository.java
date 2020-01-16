package com.aditum.cardiorehabcr.repository;

import com.aditum.cardiorehabcr.domain.MayorEventsSession;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MayorEventsSession entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MayorEventsSessionRepository extends JpaRepository<MayorEventsSession, Long> {

}
