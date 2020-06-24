package corp.netizen.datastore.service;

import corp.netizen.datastore.converters.MibConverter;
import corp.netizen.datastore.converters.MibValueConverter;
import corp.netizen.datastore.dto.MibDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corp.netizen.datastore.model.Mib;
import corp.netizen.datastore.repository.MibRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class MibService {

    private MibRepository mibRepository;
    public MibConverter mibConverter;

    @Autowired
    public MibService(MibRepository mibRepository, MibConverter mibConverter) {
        this.mibRepository = mibRepository;
        this.mibConverter = mibConverter;
    }

    public Mib getMibByOid(String oid) {
        return mibRepository.findByOid(oid).orElse(null);
    }


    public List<MibDTO> listAllDTO() {
        List<MibDTO> mibs = new ArrayList<>();
        mibRepository.findAll().forEach(mib -> {
            mibs.add(mibConverter.createFromEntity(mib));
        });
        return mibs;
    }

    public List<Mib> listAll() {
        return mibRepository.findAll();
    }

    public List<Mib> saveAll(List<Mib> mibs) {
        return this.mibRepository.saveAll(mibs);
    }

    public Mib save(Mib mib) {
        return this.mibRepository.save(mib);
    }


    public List<MibDTO> convert(Set<Mib> mibs) {
        List<MibDTO> dtos = new ArrayList<>();
        mibs.forEach(mib -> {
            dtos.add(mibConverter.createFromEntity(mib));
        });
        return dtos;
    }

}
