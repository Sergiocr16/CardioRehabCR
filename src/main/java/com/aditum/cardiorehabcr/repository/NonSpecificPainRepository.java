package com.aditum.cardiorehabcr.repository;

import com.aditum.cardiorehabcr.domain.NonSpecificPain;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NonSpecificPain entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NonSpecificPainRepository extends JpaRepository<NonSpecificPain, Long> {

}
