
package SocietyManagement.SocietyManagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GuardController {
     @GetMapping("/GuardSignup")
    public String guard(){
        return "GuardSignup";
    }
}
