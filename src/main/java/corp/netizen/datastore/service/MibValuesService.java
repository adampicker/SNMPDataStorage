package corp.netizen.datastore.service;

import corp.netizen.datastore.DatastoreApplication;
import corp.netizen.datastore.converters.MibValueConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corp.netizen.datastore.dto.MibValuesDTO;

import corp.netizen.datastore.model.MibValue;
import corp.netizen.datastore.repository.MibValuesRepository;

import javax.xml.crypto.Data;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class MibValuesService {

    public static Logger logger = LoggerFactory.getLogger(MibValuesService.class);

    private MibValuesRepository mibValuesRepository;
    private MibService mibService;
    private ClientService clientService;
    private MibValueConverter mibValueConverter;
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public MibValuesService(MibValuesRepository mibValuesRepository,
                            MibService mibService,
                            ClientService clientService,
                            RabbitTemplate rabbitTemplate) {
        this.mibValuesRepository = mibValuesRepository;
        this.mibService = mibService;
        this.clientService = clientService;
        this.mibValueConverter = new MibValueConverter(this.mibService::getMibByOid, this.clientService::getById);
        this.rabbitTemplate = rabbitTemplate;
    }

    public void saveData(MibValuesDTO values) {

        logger.debug("Saving mib values data. Data records: " + values.getValues().size());
        for (int i = 0; i < values.getValues().size(); i++) {
            this.saveMibValue(this.convert(values.getValues().get(i)));
        }
        logger.info("Send message on queue");
        this.sendValuesOnQueue(values);
        logger.info("Saved mib values");

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

    private MibValue convert(MibValuesDTO.MibValueDTO mibValueDTO) {
        return this.mibValueConverter.createFromDto(mibValueDTO);
    }

    private MibValuesDTO.MibValueDTO convert(MibValue mibValue) {
        return this.mibValueConverter.createFromEntity(mibValue);
    }

    public void sendValuesOnQueue(MibValuesDTO values) {
        this.rabbitTemplate.convertAndSend(DatastoreApplication.QUEUE_NAME, values);
    }

    public MibValuesDTO listAllDto() {
        MibValuesDTO dtoToSend = new MibValuesDTO();
        List<MibValue> allValues = this.mibValuesRepository.findAll();
        for (MibValue val : allValues) {
            dtoToSend.getValues().add(this.convert(val));
        }
        return dtoToSend;
    }

    public void getLastDayDataOnQueue() {
        this.rabbitTemplate.convertAndSend(DatastoreApplication.QUEUE_NAME, this.listAllDto());
    }

    public MibValuesDTO getLastDayDataDto(long clientId) {
        Instant date = Instant.now().minusSeconds(24 * 60 * 60 * 60); //day
        Optional<List<MibValue>> values = this.mibValuesRepository.getLastDayClientsDataNotOlderThanGivenDate(clientId, date);
        List<MibValue> unPackedValues = values.isPresent() ? values.get() : new LinkedList<>();

        MibValuesDTO dtoToSend = new MibValuesDTO();
        for (MibValue val : unPackedValues) {
            dtoToSend.getValues().add(this.convert(val));
        }
        return dtoToSend;

    }

}
