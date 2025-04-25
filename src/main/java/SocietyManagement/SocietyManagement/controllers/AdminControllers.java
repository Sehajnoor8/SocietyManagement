package SocietyManagement.SocietyManagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminControllers {

    @GetMapping("/AdminLogin")
    public String adminlogin() {
        return "AdminLogin";
    }
    
    @GetMapping("/AdminManageFlats")
    public String flats(){
        return "AdminManageFlats";
    }
     @GetMapping("/AdminHome")
    public String home(){
        return "AdminHome";
    }
      @GetMapping("/AdminHelpers")
    public String helpers(){
        return "AdminHelpers";
    }
     @GetMapping("/UserSignupTable")
    public String usertable(){
        return "UserSignupTable";
    }
    
}
