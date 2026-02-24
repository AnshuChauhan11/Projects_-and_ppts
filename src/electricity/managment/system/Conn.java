//
//package electricity.managment.system;
//
////import com.sun.jdi.connect.spi.Connection;
////import java.beans.Statement;
//import  java.sql.DriverManager;
//import java.sql.Connection;
//import  java.sql.Statement;
//import javax.swing.JOptionPane;
//
//
//public class Conn {
//    Connection connection;
//    Statement stm;
//     Conn() {
//         try{
//         Class.forName("com.mysql.cj.jdbc.Driver");
//         }catch(Exception e){
//             e.printStackTrace();
//         }
//         // Load the Driver
//         try {
//         DriverManager.getConnection("jdbc:mysql://localhost:3306/EBS","root","Anshu.@12");
//         stm=connection.createStatement();
//            
//         } catch (Exception e) {
//             e.printStackTrace();
//           }
//    }
//    
//}
package electricity.managment.system;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;

public class Conn {

    public Connection connection;
    public Statement stm;

    Conn() {
        try {
            // 1️⃣ Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2️⃣ Create Connection  ⭐ MOST IMPORTANT LINE
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/EBS",
                    "root",
                    "Anshu.@12"
            );

            // 3️⃣ Create Statement
            stm = connection.createStatement();

            System.out.println("DB Connected Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}