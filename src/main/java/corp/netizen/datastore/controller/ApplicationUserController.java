package corp.netizen.datastore.controller;

import corp.netizen.datastore.model.ApplicationUser;
import corp.netizen.datastore.service.ApplicationUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class ApplicationUserController {

    public static Logger logger = LoggerFactory.getLogger(ApplicationUserController.class);

    @Autowired
    private ApplicationUserService applicationUserService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/sign-up")
        public ResponseEntity signUp(@RequestBody ApplicationUser applicationUser){
            logger.info("Got sign up request from: " + applicationUser.getUsername());
            applicationUser.setPassword(bCryptPasswordEncoder.encode(applicationUser.getPassword()));
            this.applicationUserService.saveOrUpdate(applicationUser);
            return new ResponseEntity(HttpStatus.CREATED);
        }

    @GetMapping("/test")
    public String test(){
        return "test";
    }


    }
