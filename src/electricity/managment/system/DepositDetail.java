
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

public class DepositDetail extends  JFrame implements ActionListener{

    Choice metreNumber ,cmonth;
    JButton search,print;
    JTable table;
     DepositDetail() {
         super("Deposit Detail");
         setSize(700,700);
         setLocation(400,100);
         setLayout(null);
         getContentPane().setBackground(Color.white);
         
         JLabel lblmeterNumber =new JLabel("Search By Meter Number ");
         lblmeterNumber.setBounds(20,20,150,30);
         add(lblmeterNumber);
         
        // add choice for search by meter num 
        metreNumber=new  Choice();
        metreNumber.setBounds(170,25,150,30);
         add(metreNumber);
        
         try {
             Conn c=new Conn();
              String query = "SELECT meter_no FROM customer";
            PreparedStatement pst = c.connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                metreNumber.add(rs.getString("meter_no"));
            }
             
         } catch (Exception e) {
             e.printStackTrace();
             }
 
         JLabel lblMonth =new JLabel("Search By Month ");
         lblMonth.setBounds(380,20,100,30);
         add(lblMonth);
         
        // add choice for search by meter num 
        cmonth=new  Choice();
        cmonth.setBounds(500,25,150,30);
        cmonth.add("January");
        cmonth.add("February");
        cmonth.add("March");
        cmonth.add("April");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("July");
        cmonth.add("August");
        cmonth.add("September");
        cmonth.add("October");
        cmonth.add("November");
        cmonth.add("December");
         add(cmonth);
         
        table =new JTable();
        
         try {
             Conn c=new Conn();
             
             ResultSet rs=c.stm.executeQuery("SELECT * FROM bill");
             table.setModel(DbUtils.resultSetToTableModel(rs));
         } catch (Exception e) {
             e.printStackTrace();
         }
        
        // add Scroll bar 
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0,100,700,600);
        add(sp);
        
        search =new JButton("Search");
        search.setBounds(20,70,80,21);
        search.addActionListener(this);
         add(search);
       
         
        print =new JButton("Print");
        print.setBounds(120,70,80,21);
        print.addActionListener(this);
        add(print);
        
        
         setVisible(true);
    }
     
  public void actionPerformed(ActionEvent e){
      
      if (e.getSource()==search) {
          Conn c=new Conn();
          String query="SELECT * FROM bill WHERE meter_no=? AND month=?";
          try {
              PreparedStatement pst=c.connection.prepareStatement(query);
              
              pst.setString(1,metreNumber.getSelectedItem());
              pst.setString(2,cmonth.getSelectedItem());
              ResultSet rs=pst.executeQuery();
              
              table.setModel(DbUtils.resultSetToTableModel(rs));
              
          } catch (Exception e1) {
          e1.printStackTrace();
          }
      }else{
          try {
              table.print();
          } catch (Exception ee) {
          ee.printStackTrace();
          }
      }
  }   
    
        public static void main(String[] args) {
        new DepositDetail();
    }
}
