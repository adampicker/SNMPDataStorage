package corp.netizen.datastore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import corp.netizen.datastore.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
	@Query("SELECT c FROM Client c WHERE LOWER(c.macAddress) = LOWER(:macAddress)")
    public Optional<Client> findByMac(@Param("macAddress") String macAddress);

}
