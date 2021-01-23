package corp.netizen.datastore.repository;

import corp.netizen.datastore.model.ApplicationUser;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    @Query("SELECT appUser FROM ApplicationUser appUser WHERE appUser.username LIKE :username")
    public Optional<ApplicationUser> findByUsername(@Param("username") String username);
}
