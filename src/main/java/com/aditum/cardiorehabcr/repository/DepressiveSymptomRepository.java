package com.aditum.cardiorehabcr.repository;
import com.aditum.cardiorehabcr.domain.DepressiveSymptom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DepressiveSymptom entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DepressiveSymptomRepository extends JpaRepository<DepressiveSymptom, Long> {

}
