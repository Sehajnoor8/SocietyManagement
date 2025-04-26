package SocietyManagement.SocietyManagement.controllers;

import SocietyManagement.SocietyManagement.vmm.DBLoader;
import SocietyManagement.SocietyManagement.vmm.RDBMS_TO_JSON;
import java.sql.SQLException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class AdminRestControllers {

    @PostMapping("/alogin")
    public String alogin(@RequestParam String email, @RequestParam String pass) {
        ResultSet rs = DBLoader.executeQuery("select *  from adminlogin where email = '" + email + "' and password = '" + pass + "'");
        try {

            if (rs.next()) {
                return "success";
            } else {
                return "fail";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ("exception occured");
        }
    }

    @PostMapping("/ahome")
    public String ahome(@RequestParam String flatName) {
        System.out.println(flatName);
        ResultSet rs = DBLoader.executeQuery("SELECT * FROM flats WHERE FlatId = '" + flatName + "'");
        try {
            if (rs.next()) {
                return "fail";
            } else {
                rs.moveToInsertRow();
                rs.updateString("FlatId", flatName);
                rs.updateString("Status", "pending");
                rs.insertRow();
                return "success";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "exception";
        }
    }

    @GetMapping("/getflats")
    public String getFlats() {
        String ans = new RDBMS_TO_JSON().generateJSON("select * from flats");

        return ans;
    }

    @GetMapping("/delflat")
    public String delflat(@RequestParam String id) {
        ResultSet rs = DBLoader.executeQuery("select * from flats where FlatId='" + id + "'");
        try {
            if (rs.next()) {
                rs.deleteRow();
                return "success";
            } else {
                return "fail";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "exception";
        }
    }

     @PostMapping("/addHelper")
    public String addHelper(
            @RequestParam String name,
            @RequestParam MultipartFile f1,
            @RequestParam String aadhar,
            @RequestParam String experience,
            @RequestParam String price,
            @RequestParam String description
    ) 
    {
        
        try {
            ResultSet rs = DBLoader.executeQuery("SELECT * FROM helpers WHERE AdharCard = '" + aadhar + "'");
            String photo = f1.getOriginalFilename();
           
            if (rs.next()) {
                return "fail"; 
            } else {
               
                byte[] b = f1.getBytes();
                String abspath = "src/main/resources/static/myuploads/";
                FileOutputStream fos = new FileOutputStream(abspath + photo);
                fos.write(b);
                fos.close();

               
                rs.moveToInsertRow();
                rs.updateString("Name", name);
                rs.updateString("Photo", photo);
                rs.updateString("AdharCard", aadhar);
                rs.updateString("Experience", experience);
                rs.updateString("PricePerHour", price);
                rs.updateString("Description", description);
                rs.insertRow();

                return "success";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.toString();
        }
    }

    @GetMapping("/getHelpers")
    public String getHelpers() {
        try {
            return new RDBMS_TO_JSON().generateJSON("SELECT * FROM helpers");
        } catch (Exception e) {
            e.printStackTrace();
            return "exception";
        }
    }

    @GetMapping("/deleteHelper")
    public String deleteHelper(@RequestParam String id) {
        try {
            ResultSet rs = DBLoader.executeQuery("SELECT * FROM helpers WHERE AdharCard = '" + id + "'");
            if (rs.next()) {
                rs.deleteRow();
                return "deleted";
            } else {
                return "not found";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "exception";
        }
    }
    
   @GetMapping("/getUsers")
public String getUsers() {
    try {
        return new RDBMS_TO_JSON().generateJSON("SELECT * FROM usersignup");
    } catch (Exception e) {
        e.printStackTrace();
        return "exception";
    }
}

  @GetMapping("/approve")
public String approveUser(@RequestParam String id) {
    try {
        ResultSet rs = DBLoader.executeQuery("SELECT * FROM usersignup WHERE Id = '" + id + "'");
        if (rs.next()) {
            rs.updateString("Status", "approved");
            rs.updateRow();
            return "success";
        } else {
            return "fail";
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        return "exception";
    }
}
 
 @GetMapping("/block")
public String blockUser(@RequestParam String id) {
    try {
        ResultSet rs = DBLoader.executeQuery("SELECT * FROM usersignup WHERE Id = '" + id + "'");
        if (rs.next()) {
            rs.updateString("Status", "pending");
            rs.updateRow();
            return "success";
        } else {
            return "fail";
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        return "exception";
    }
}


      @GetMapping("/getGuards")
public String getGuards() {
    try {
        return new RDBMS_TO_JSON().generateJSON("SELECT * FROM guardsignup");
    } catch (Exception e) {
        e.printStackTrace();
        return "exception";
    }
}

  @GetMapping("/approveGuard")
public String approveGuard(@RequestParam String id) {
    try {
        ResultSet rs = DBLoader.executeQuery("SELECT * FROM guardsignup WHERE id = '" + id + "'");
        if (rs.next()) {
            rs.updateString("Status", "approved");
            rs.updateRow();
            return "success";
        } else {
            return "fail";
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        return "exception";
    }
}
 
 @GetMapping("/blockGuard")
public String blockGuard(@RequestParam String id) {
    try {
        ResultSet rs = DBLoader.executeQuery("SELECT * FROM guardsignup WHERE id = '" + id + "'");
        if (rs.next()) {
            rs.updateString("Status", "pending");
            rs.updateRow();
            return "success";
        } else {
            return "fail";
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        return "exception";
    }
}   
}
