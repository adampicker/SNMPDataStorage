package corp.netizen.datastore.service;

import corp.netizen.datastore.model.ApplicationUser;
import corp.netizen.datastore.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService {

    ApplicationUserRepository applicationUserRepository;

    @Autowired
    public ApplicationUserService(ApplicationUserRepository applicationUserRepository){
        this.applicationUserRepository = applicationUserRepository;
    }

    public ApplicationUser getById(Long id){
        return this.applicationUserRepository.findById(id).orElse(null);
    }

    public ApplicationUser saveOrUpdate(ApplicationUser applicationUser){
        return this.applicationUserRepository.save(applicationUser);
    }

    public void delete(Long id){
        this.applicationUserRepository.deleteById(id);
    }

     public void convert(ApplicationUser applicationUser){}

     public ApplicationUser findByName(String username){
        return this.applicationUserRepository.findByUsername(username).orElse(null);
     }
}
