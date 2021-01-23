package corp.netizen.datastore.repository;

import java.util.Optional;

import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import corp.netizen.datastore.model.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	@Query("SELECT c FROM Client c WHERE LOWER(c.macAddress) = LOWER(:macAddress)")
    public Optional<Client> findByMac(@Param("macAddress") String macAddress);

    @Query("SELECT COUNT(c) FROM Client c WHERE c.status = :status")
    public Integer getAmountOfClientsWithStatus(@Param("status") Client.Status status);

}
