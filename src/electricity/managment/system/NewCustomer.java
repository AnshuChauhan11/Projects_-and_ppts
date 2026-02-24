
package electricity.managment.system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;


public class NewCustomer extends JFrame implements ActionListener{
    
    JTextField custoNameInput,addressInput,cityInput,stateInput,emailInput,phNumInput;
    JButton next,cancel;
     JLabel MeterNoI;
    NewCustomer(){
        
        // add the panel on frame 
        JPanel panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(173,216,230));
        add(panel);
        
        // add label's on the panel 
        JLabel heading =new JLabel("New Customer");
        heading.setBounds(180,10,200,25);
        heading.setFont(new Font("Tahoma",Font.PLAIN,24));
        panel.add(heading);
        
        JLabel custoName =new JLabel("Customer Name :");
        custoName.setBounds(100,80,100,25);
        panel.add(custoName);
        
        // Text Field for Customer Name 
        custoNameInput=new JTextField();
        custoNameInput.setBounds(240,80,170,20);
        panel.add(custoNameInput);
        
        // new Lable for Meter No.
         JLabel MeterNo =new JLabel("Meter No.");
        MeterNo.setBounds(100,110,100,25);
        panel.add(MeterNo);
        
        // new Lable for autogenrate MeterNumber 
       MeterNoI =new JLabel("");
        MeterNoI.setBounds(240,110,170,25);
        panel.add(MeterNoI);
        
        // For check Lable Input 
        Random random=new Random();
        long num=random.nextLong()%1000000;
        MeterNoI.setText(""+Math.abs(num));
        
        
        // For next Lable and Text field 
        JLabel address =new JLabel("Address :");
        address.setBounds(100,140,100,25);
        panel.add(address);
        
        // Text Field for Address
        addressInput=new JTextField();
        addressInput.setBounds(240,140,170,20);
        panel.add(addressInput);
        
        
        // For next Lable and Text field 
        JLabel city =new JLabel("City :");
        city.setBounds(100,170,100,25);
        panel.add(city);
        
        // Text Field for City
        cityInput=new JTextField();
        cityInput.setBounds(240,170,170,20);
        panel.add(cityInput);
        
        // For next Lable and Text field 
        JLabel state =new JLabel("State:");
        state.setBounds(100,200,100,25);
        panel.add(state);
        
        // Text Field for State
        stateInput=new JTextField();
        stateInput.setBounds(240,200,170,20);
        panel.add(stateInput);
        
        
        // For next Lable and Text field 
        JLabel email =new JLabel("Email-id:");
        email.setBounds(100,230,100,25);
        panel.add(email);
        
        // Text Field for Email-id
        emailInput=new JTextField();
        emailInput.setBounds(240,230,170,20);
        panel.add(emailInput);
        
        // For next Lable and Text field 
        JLabel PhNum =new JLabel("Phone No.:");
        PhNum.setBounds(100,260,100,25);
        panel.add(PhNum);
        
        // Text Field for Phone Number 
        phNumInput=new JTextField();
        phNumInput.setBounds(240,260,170,20);
        panel.add(phNumInput);
        
        // Let's add button 
        next=new JButton("Next");
        next.setFont(new Font("Verdana",Font.BOLD,14));
        next.setBounds(120,300,100,30);
        next.setBackground(Color.cyan);
        next.setForeground(Color.BLACK);
        next.addActionListener(this);
        panel.add(next);
        
        
        cancel=new JButton("Cancel");
        cancel.setFont(new Font("Verdana",Font.BOLD,14));
        cancel.setBounds(250,300,100,30);
        cancel.setBackground(Color.cyan);
        cancel.setForeground(Color.red);
        cancel.addActionListener(this);
        panel.add(cancel);
        
        
        setSize(700,400);
        setLocation(400,200);
        
        
        setLayout(new BorderLayout());
        add(panel,"Center");
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i2=i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        
        JLabel image=new JLabel(i3);
        add(image,"West");

        getContentPane().setBackground(Color.white);
            
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
         Conn c=new Conn();
        if (e.getSource()==next) {
            String sname=custoNameInput.getText();
            String smeter=MeterNoI.getText();
            String saddress=addressInput.getText();
            String scity=cityInput.getText();
            String sState=stateInput.getText();
            String semail=emailInput.getText();
            String sphone=phNumInput.getText(); 
            
            // ⭐ Customer  table insert
            String query ="INSERT INTO customer (name,meter_no,address,city,state,email,phone_no) VALUES (?,?,?,?,?,?,?)";
          // ⭐ Login table insert
            String query2 = "INSERT INTO login (meter_no,user_name,name,password,user)VALUES (?,?,?,?,?)";
            try{
            // Let's write the query 
            PreparedStatement pst=c.connection.prepareStatement(query);
            
            
            pst.setString(1, sname);
            pst.setString(2, smeter);
            pst.setString(3, saddress);
            pst.setString(4, scity);
            pst.setString(5, sState);
            pst.setString(6, semail);
            pst.setString(7, sphone);
            pst.executeUpdate();
            
            
  
        // ⭐ Login table insert
        
        PreparedStatement pst2 = c.connection.prepareStatement(query2);

        pst2.setString(1, smeter);
        pst2.setString(2, "");
        pst2.setString(3, sname);
        pst2.setString(4, "");
        pst2.setString(5, "");
        pst2.executeUpdate();
            
            
            
        JOptionPane.showMessageDialog(null,"Customer Added Successfully ");
        setVisible(false);
        new MeterInfo(smeter);
            }catch(Exception ee){
               ee.printStackTrace();
            }
            
        }else {
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new NewCustomer();
    }
   
}
