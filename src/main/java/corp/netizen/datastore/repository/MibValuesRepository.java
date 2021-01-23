package corp.netizen.datastore.repository;

import corp.netizen.datastore.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import corp.netizen.datastore.model.MibValue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MibValuesRepository extends JpaRepository<MibValue, Long> {

    @Query("SELECT mibValue" +
            " FROM MibValue mibValue" +
            " WHERE mibValue.timestamp >= :date" +
            " AND mibValue.client.id = :clientId")
    public Optional<List<MibValue>> getLastDayClientsDataNotOlderThanGivenDate(@Param("clientId") long clientId,
                                                                               @Param("date") Instant date);

}
