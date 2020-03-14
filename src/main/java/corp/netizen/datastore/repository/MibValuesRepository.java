package corp.netizen.datastore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import corp.netizen.datastore.model.MibValue;

public interface MibValuesRepository extends JpaRepository<MibValue, Long> {

}
