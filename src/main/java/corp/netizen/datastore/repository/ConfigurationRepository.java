package corp.netizen.datastore.repository;

import corp.netizen.datastore.model.Mib;
import org.springframework.data.jpa.repository.JpaRepository;

import corp.netizen.datastore.model.Configuration;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Configuration c SET c.defaultConfiguration = false WHERE c.id != :id")
    public int setNonDefaultExcept(@Param("id") long id);

    @Query("SELECT c FROM Configuration c WHERE c.defaultConfiguration = TRUE")
    public Configuration getDefaultConfiguration();

}
