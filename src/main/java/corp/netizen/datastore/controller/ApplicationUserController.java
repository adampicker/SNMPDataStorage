package corp.netizen.datastore.controller;

import corp.netizen.datastore.model.ApplicationUser;
import corp.netizen.datastore.service.ApplicationUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/users")
public class ApplicationUserController {

    public static Logger logger = LoggerFactory.getLogger(ApplicationUserController.class);

    @Autowired
    private ApplicationUserService applicationUserService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/sign-up")
        public String signUp(@RequestBody ApplicationUser applicationUser){
            logger.info("Got sign up request from: " + applicationUser.getUsername());
            ApplicationUser appUsr = this.applicationUserService.findByName(applicationUser.getUsername());
            if (appUsr != null) return "ELO idz stont";
            applicationUser.setPassword(bCryptPasswordEncoder.encode(applicationUser.getPassword()));
            this.applicationUserService.saveOrUpdate(applicationUser);
            return "siemanko witam w mojej kuchni";
        }

    @GetMapping("/test")
    public String test(){
        System.out.println("Jakim prawem");
        return "test";
    }


    }
