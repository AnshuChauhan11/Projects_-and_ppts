
package electricity.managment.system;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import  java.sql.ResultSet;
import  java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class PayBill extends JFrame implements  ActionListener{

    JLabel nameInput,meterNumberInput,unitInput,totalBillInput,statusInput;
    Choice cmonth;
    JButton payBill,back;
    String meter;
    PayBill(String meter) {
        super("Pay Bil;");
        this.meter=meter;
         setBounds(300,150,900,600);
         getContentPane().setBackground(Color.white);
         setLayout(null);
         
         JLabel heading =new JLabel("Electricity Bill");
        heading.setBounds(120,5,400,30);
        heading.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(heading);
       
        JLabel meterNumber =new JLabel("Meter Numbers");
        meterNumber.setBounds(30,80,200,25);
        add(meterNumber);
        
        meterNumberInput=new JLabel("");
        meterNumberInput.setBounds(300,80,200,25);
        add(meterNumberInput);
        
        JLabel name =new JLabel("Name");
        name.setBounds(30,140,200,25);
        add(name);
        
        nameInput=new JLabel("");
        nameInput.setBounds(300,140,200,25);
         add(nameInput);
         
         
        JLabel month =new JLabel("Month");
        month.setBounds(30,200,200,25);
        add(month); 
         
        cmonth=new Choice();
        cmonth.setBounds(300,200,200,30);
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
         
        
        JLabel unit =new JLabel("Units");
        unit.setBounds(30,260,200,25);
        add(unit);
        
        unitInput=new JLabel("");
        unitInput.setBounds(300,260,200,25);
         add(unitInput);
         
        JLabel totoalBill =new JLabel("Total Bill");
        totoalBill.setBounds(30,310,200,25);
        add(totoalBill);
        
        totalBillInput=new JLabel("");
        totalBillInput.setBounds(300,310,200,25);
         add(totalBillInput);
         
        JLabel status =new JLabel("Status");
        status.setBounds(30,370,200,25);
        add(status);
        
        statusInput=new JLabel("");
        statusInput.setBounds(300,370,200,25);
        statusInput.setForeground(Color.red);
        add(statusInput);
         
        
        payBill=new JButton("PayBill");
        payBill.setFont(new Font("Tahoma",Font.BOLD,25));
        payBill.setBounds(150,480,150,30);
        payBill.setBackground(Color.cyan);
        payBill.setForeground(Color.black);
      payBill.addActionListener(this);
        add(payBill);
        
        back=new JButton("Back");
        back.setFont(new Font("Tahoma",Font.BOLD,25));
        back.setBounds(350,480,150,30);
        back.setBackground(Color.black);
        back.setForeground(Color.red);
        back.addActionListener(this);
        add(back);
        
        
        
        try {
            Conn c=new Conn();
            String query="SELECT * FROM customer WHERE meter_no=?";
            PreparedStatement  psts=c.connection.prepareStatement(query);
            psts.setString(1, meter);
            
           ResultSet rs=psts.executeQuery();
            while (rs.next()) {
                meterNumberInput.setText(meter);
                nameInput.setText(rs.getString("name"));
            }
            
            String query1="SELECT * FROM bill WHERE meter_no=? AND month=?";
             psts=c.connection.prepareStatement(query1);
            psts.setString(1, meter);
            psts.setString(2, cmonth.getSelectedItem());
            rs=psts.executeQuery();
            while (rs.next()) {
             unitInput.setText(rs.getString("units"));
             totalBillInput.setText(rs.getString("totalbill"));
             statusInput.setText(rs.getString("status"));
            
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        
        }
        
        cmonth.addItemListener(new ItemListener(){
             @Override
             public void itemStateChanged(ItemEvent e) {

                 try {
            Conn c=new Conn();
          
            String query1="SELECT * FROM bill WHERE meter_no=? AND month=?";
            PreparedStatement psts=c.connection.prepareStatement(query1);
            psts.setString(1, meter);
            psts.setString(2, cmonth.getSelectedItem());
            ResultSet rs=psts.executeQuery();
            while (rs.next()) {
             unitInput.setText(rs.getString("units"));
             totalBillInput.setText(rs.getString("totalbill"));
             statusInput.setText(rs.getString("status"));
            
            }
            
        } catch (Exception ee) {
            ee.printStackTrace();
        
        }
             }
          
        });
        
        getContentPane().setBackground(Color.white);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image i2=i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(400,120,600,300);
        add(image);
        
         setVisible(true);
    }

@Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==payBill){
            Conn c=new Conn();
            
            String query ="UPDATE bill SET status=? WHERE meter_no=? AND month=?" ;
            
            try {
                PreparedStatement pst =c.connection.prepareStatement(query);
                pst.setString(1, "Paid");
                pst.setString(2, meter);
                pst.setString(3, cmonth.getSelectedItem());
                
            } catch (Exception ex) {
              ex.printStackTrace();
            }
            setVisible(false);
//            new Paytm(meter);
            new Pytm(meter);
            
        }else {
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new PayBill("");
    }

    
}
