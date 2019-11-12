package com.aditum.cardiorehabcr.repository;
import com.aditum.cardiorehabcr.domain.AppUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the AppUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    @Query("select appUser from AppUser appUser where appUser.user.login = ?#{principal.username}")
    List<Optional<AppUser>> findByUserIsCurrentUser();

}
