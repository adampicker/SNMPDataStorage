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
import java.util.LinkedList;
import java.util.List;

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
        for (int i = 0; i < allValues.size(); i++) {
            dtoToSend.getValues().add(this.convert(allValues.get(i)));
        }
        return dtoToSend;
    }

    public void getLastDayDataOnQueue() {
        this.rabbitTemplate.convertAndSend(DatastoreApplication.QUEUE_NAME, this.listAllDto());
    }

}
