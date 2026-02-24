
package electricity.managment.system;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JTable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JScrollPane;
import net.proteanit.sql.DbUtils;

public class BillDetail extends JFrame{

    String meter ;
     BillDetail(String meter) {
          super("Bill Detail ");
         setBounds(350,100,700,650);
         getContentPane().setBackground(Color.white);
         setLayout(null);
         
         JTable table=new JTable();
         
         try {
             Conn c=new Conn();
             String query ="SELECT * FROM bill WHERE meter_no=?";
             PreparedStatement pst =c.connection.prepareStatement(query);
             pst.setString(1, meter);
            ResultSet rs= pst.executeQuery();
             
            table.setModel(DbUtils.resultSetToTableModel(rs));
         } catch (Exception e) {
             e.printStackTrace();
         }
         
         JScrollPane sp=new JScrollPane(table);
         sp.setBounds(0,0,700,650);
         add(sp);
         
         
         setVisible(true);
    }
    
    
    
    public static void main(String[] args) {
        new BillDetail("");
    }
}
