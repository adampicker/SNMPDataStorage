package com.netizen.datastore.converters;

import com.netizen.datastore.dto.MibDTO;
import corp.netizen.datastore.model.Mib;

public class MibConverter implements GenericConverter<Mib, MibDTO> {


    @Override
    public Mib createFromDto(MibDTO dto) {
        return null;
    }

    @Override
    public MibDTO createFromEntity(Mib entity) {
        MibDTO dto = new MibDTO();
        dto.setOid(entity.getOid());
        dto.setDescription(entity.getDescription());
        dto.setTelnetShortcut(entity.getTelnetShortcut());
        dto.setUnit(entity.getUnit());

        return dto;
    }

    @Override
    public Mib updateEntity(Mib entity, MibDTO dto) {
        return null;
    }
}
