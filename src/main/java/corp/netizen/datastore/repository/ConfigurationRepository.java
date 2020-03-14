package corp.netizen.datastore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import corp.netizen.datastore.model.Configuration;

public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {

}
