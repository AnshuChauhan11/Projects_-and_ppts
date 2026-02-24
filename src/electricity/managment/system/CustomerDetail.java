
package electricity.managment.system;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

public class CustomerDetail extends  JFrame implements ActionListener{

    Choice metreNumber ,cmonth;
    JButton search,print;
    JTable table;
     CustomerDetail() {
         super("Customer Detail");
         setSize(1200,650);
         setLocation(200,100);
         getContentPane().setBackground(Color.cyan);
         
        table =new JTable();
        
         try {
             Conn c=new Conn();
             
             ResultSet rs=c.stm.executeQuery("SELECT * FROM customer");
             table.setModel(DbUtils.resultSetToTableModel(rs));
         } catch (Exception e) {
             e.printStackTrace();
         }
        
        // add Scroll bar 
        JScrollPane sp = new JScrollPane(table);
        //sp.setBounds(0,100,700,600);
        sp.getViewport().setBackground(Color.cyan);
        add(sp);
        
       
         
        print =new JButton("Print");
       // print.setBounds(120,70,80,21);
        print.addActionListener(this);
        add(print,"South");
        
        
         setVisible(true);
    }
     
  public void actionPerformed(ActionEvent e){
     
          try {
              table.print();
          } catch (Exception ee) {
          ee.printStackTrace();
          }
      }
     
    
        public static void main(String[] args) {
        new CustomerDetail();
    }
}
