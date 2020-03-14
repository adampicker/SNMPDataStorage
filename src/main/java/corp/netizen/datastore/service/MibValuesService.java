package corp.netizen.datastore.service;

import com.netizen.datastore.converters.ClientConverter;
import com.netizen.datastore.converters.MibValueConverter;
import corp.netizen.datastore.model.Client;
import corp.netizen.datastore.repository.MibRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netizen.datastore.dto.MibValuesDTO;

import corp.netizen.datastore.model.Mib;
import corp.netizen.datastore.model.MibValue;
import corp.netizen.datastore.repository.MibValuesRepository;

import java.time.Instant;

@Service
public class MibValuesService {

	public static Logger logger = LoggerFactory.getLogger(MibValuesService.class);

	private RabbitTemplate rabbitTemplate;
	private MibValuesRepository mibValuesRepository;
	private MibService mibService;
	private ClientService clientService;
	private MibValueConverter mibValueConverter;
	
	@Autowired
	public MibValuesService(RabbitTemplate rabbitTemplate,
			MibValuesRepository mibValuesRepository,
			MibService mibService,
			ClientService clientService) {
		this.rabbitTemplate = rabbitTemplate;
		this.mibValuesRepository = mibValuesRepository;
		this.mibService = mibService;
		this.clientService = clientService;
		this.mibValueConverter = new MibValueConverter(this.mibService::getMibByOid, this.clientService::getById);
	}
	
	public void saveData(MibValuesDTO values) {
		logger.debug("Saving mib values data. Data records: " + values.getValues().size());
		for (int i=0; i<values.getValues().size(); i++){
			this.saveMibValue(this.convert(values.getValues().get(i)));
		}
		logger.debug("Saved mib values");

	}

	private void saveMibValue(MibValue valueEntity) {
		if (valueEntity.getMib() == null) {
			logger.error("Mib values mib field is empty. Refuse to save");
			return;
		}
		if (valueEntity.getClient() == null) {
			logger.error("Mib values client field is empty. Refuse to save");
			return;
		}
		this.mibValuesRepository.save(valueEntity);
	}

	private MibValue convert(MibValuesDTO.MibValueDTO mibValueDTO){
		return this.mibValueConverter.createFromDto(mibValueDTO);
	}
	
}
