package corp.netizen.datastore.dto;

import corp.netizen.datastore.model.Client;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;


@Getter @Setter
public class MibValuesDTO {

	List<MibValueDTO> values;

	@Getter @Setter
	public static class MibValueDTO{
		Instant timestamp;
		Long clientId;
		String oid;
		String value;
	}
}
