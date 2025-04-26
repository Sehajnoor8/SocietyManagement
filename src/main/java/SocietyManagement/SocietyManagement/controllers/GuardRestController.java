package SocietyManagement.SocietyManagement.controllers;

import SocietyManagement.SocietyManagement.vmm.DBLoader;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@RestController
public class GuardRestController {
    
     @PostMapping("/addGuard")
    public String addGuard(
            @RequestParam String name,
            @RequestParam MultipartFile file,
            @RequestParam String email,
            @RequestParam String phone, 
            @RequestParam String password
    ) 
    {
        
        try {
            ResultSet rs = DBLoader.executeQuery("SELECT * FROM guardsignup WHERE Name = '" + name + "'");
            String photo = file.getOriginalFilename();
           
            if (rs.next()) {
                return "fail"; 
            } else {
               
                byte[] b = file.getBytes();
                String abspath = "src/main/resources/static/myuploads/";
                FileOutputStream fos = new FileOutputStream(abspath + photo);
                fos.write(b);
                fos.close();

               
                rs.moveToInsertRow();
                rs.updateString("Name", name);
                rs.updateString("Photo", photo);
                rs.updateString("Email", email);
                rs.updateString("Password", password);
                rs.updateString("PhoneNumber", phone);
                rs.updateString("Status", "pending");
                rs.insertRow();

                return "success";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.toString();
        }
    }

    
}


