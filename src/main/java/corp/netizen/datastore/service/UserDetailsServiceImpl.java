package corp.netizen.datastore.service;

import corp.netizen.datastore.model.ApplicationUser;
import corp.netizen.datastore.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        ApplicationUser appUser = this.applicationUserRepository.findByUsername(username).orElse(null);
        if (appUser == null) throw new UsernameNotFoundException(username);
        return new User(appUser.getUsername(), appUser.getPassword(), Collections.emptyList());    }


}
