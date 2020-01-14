package com.aditum.cardiorehabcr.repository;
import com.aditum.cardiorehabcr.domain.RehabilitationGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the RehabilitationGroup entity.
 */
@Repository
public interface RehabilitationGroupRepository extends JpaRepository<RehabilitationGroup, Long> {

    @Query(value = "select distinct rehabilitationGroup from RehabilitationGroup rehabilitationGroup left join fetch rehabilitationGroup.patients",
        countQuery = "select count(distinct rehabilitationGroup) from RehabilitationGroup rehabilitationGroup")
    Page<RehabilitationGroup> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct rehabilitationGroup from RehabilitationGroup rehabilitationGroup left join fetch rehabilitationGroup.patients")
    List<RehabilitationGroup> findAllWithEagerRelationships();

    @Query("select rehabilitationGroup from RehabilitationGroup rehabilitationGroup left join fetch rehabilitationGroup.patients where rehabilitationGroup.id =:id")
    Optional<RehabilitationGroup> findOneWithEagerRelationships(@Param("id") Long id);

}
