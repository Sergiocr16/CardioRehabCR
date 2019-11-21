package com.aditum.cardiorehabcr.repository;
import com.aditum.cardiorehabcr.domain.Comorbiditie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Comorbiditie entity.
 */

@Repository
public interface ComorbiditieRepository extends JpaRepository<Comorbiditie, Long> {

Page<Comorbiditie> findByRehabilitationCenterIdAndAndDeleted(Pageable pageable, Long rehabilitationId,boolean deleted);

}
