package com.aditum.cardiorehabcr.repository;

import com.aditum.cardiorehabcr.domain.MinorEvent;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MinorEvent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MinorEventRepository extends JpaRepository<MinorEvent, Long> {

}
