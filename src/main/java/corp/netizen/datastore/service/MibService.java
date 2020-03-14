package corp.netizen.datastore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corp.netizen.datastore.model.Mib;
import corp.netizen.datastore.repository.MibRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MibService {
	
	private MibRepository mibRepository;
	
	@Autowired
	public MibService(MibRepository mibRepository) {
		this.mibRepository = mibRepository;
	}
	
	public Mib getMibByOid(String oid) {
		return mibRepository.findByOid(oid).orElse(null);
	}

	public List<Mib>  saveAll(List<Mib> mibs){
		return this.mibRepository.saveAll(mibs);
	}

	public Mib save(Mib mib){
		return this.mibRepository.save(mib);
	}

}
