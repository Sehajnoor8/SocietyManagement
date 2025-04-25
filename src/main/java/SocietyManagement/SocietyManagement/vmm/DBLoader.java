package SocietyManagement.SocietyManagement.vmm;

import java.sql.*;

public class DBLoader {


  public static ResultSet executeQuery(String stm) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully!!");

            //create connection to the mysql databases
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/society", "root", "system");
            System.out.println("Connection build");

            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println("Statement created");

            ResultSet rs = stmt.executeQuery(stm);

            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
}
}
