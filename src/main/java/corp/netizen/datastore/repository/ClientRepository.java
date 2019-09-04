package corp.netizen.datastore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import corp.netizen.datastore.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
