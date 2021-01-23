package corp.netizen.datastore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import corp.netizen.datastore.model.Mib;
import org.springframework.stereotype.Repository;

@Repository
public interface MibRepository extends JpaRepository<Mib, Long> {
	
	@Query("SELECT m FROM Mib m WHERE m.oid LIKE :oid")
	public Optional<Mib> findByOid(@Param("oid") String oid);

}
