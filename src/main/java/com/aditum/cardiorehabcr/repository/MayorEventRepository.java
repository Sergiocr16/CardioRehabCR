package com.aditum.cardiorehabcr.repository;
import com.aditum.cardiorehabcr.domain.MayorEvent;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MayorEvent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MayorEventRepository extends JpaRepository<MayorEvent, Long> {

}
