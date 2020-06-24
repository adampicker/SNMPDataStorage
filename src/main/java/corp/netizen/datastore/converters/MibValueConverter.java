package corp.netizen.datastore.converters;

import corp.netizen.datastore.dto.MibValuesDTO;
import corp.netizen.datastore.model.Client;
import corp.netizen.datastore.model.Mib;
import corp.netizen.datastore.model.MibValue;

import java.util.LinkedList;
import java.util.function.Function;

public class MibValueConverter implements GenericConverter<MibValue, MibValuesDTO.MibValueDTO> {
    private final Function<String, Mib> mibResolver;
    private final Function<Long, Client> clientResolver;

    public MibValueConverter(Function<String, Mib> mibResolver, Function<Long, Client> clientResolver) {
        this.mibResolver = mibResolver;
        this.clientResolver = clientResolver;
    }

    @Override
    public MibValue createFromDto(MibValuesDTO.MibValueDTO dto) {
        MibValue mibValue = new MibValue();
        mibValue.setMib(this.mibResolver.apply("." + dto.getOid().toString()));
        mibValue.setClient(this.clientResolver.apply(dto.getClientId()));
        mibValue.setValue(dto.getValue());
        mibValue.setTimestamp((dto.getTimestamp()));
        return mibValue;
    }

    @Override
    public MibValuesDTO.MibValueDTO createFromEntity(MibValue entity) {
        MibValuesDTO.MibValueDTO mibValueDto = new MibValuesDTO.MibValueDTO();
        mibValueDto.setClientId(entity.getClient().getId());
        mibValueDto.setOid(entity.getMib().getOid());
        mibValueDto.setTimestamp(entity.getTimestamp());
        mibValueDto.setValue(entity.getValue());
        return mibValueDto;
    }

    @Override
    public MibValue updateEntity(MibValue entity, MibValuesDTO.MibValueDTO dto) {
        return null;
    }
}
