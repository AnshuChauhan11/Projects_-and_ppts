
package electricity.managment.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



public class UpdateInformation extends JFrame implements ActionListener{
    JButton cancel,update;
    JLabel nameInput,meterNumberInput;
    JTextField addressInput,cityInput,stateInput,emailInput,phoneInput;
    String meter ;
     UpdateInformation(String meter )  {
         this.meter=meter;
        setBounds(300,100,1050,450);
        getContentPane().setBackground(Color.white);
         setLayout(null);
         
        JLabel heading =new JLabel("Update Customer Information");
        heading.setBounds(110,0,400,30);
        heading.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(heading);
       
        
        JLabel name =new JLabel("Name");
        name.setBounds(30,70,100,25);
        add(name);
        
        nameInput=new JLabel("");
        nameInput.setBounds(230,70,200,25);
         add(nameInput);
         
         
        JLabel meterNumber =new JLabel("Meter Numbers");
        meterNumber.setBounds(30,110,100,25);
        add(meterNumber);
        
        meterNumberInput=new JLabel("");
        meterNumberInput.setBounds(230,110,200,25);
        add(meterNumberInput);
        
         
        JLabel address =new JLabel("Address");
        address.setBounds(30,150,100,25);
        add(address);
        
        addressInput=new JTextField();
        addressInput.setBounds(230,150,200,25);
        add(addressInput); 
        
        JLabel city =new JLabel("City");
        city.setBounds(30,190,100,25);
        add(city);
        
        cityInput=new JTextField();
        cityInput.setBounds(230,190,200,25);
        add(cityInput); 
        
        JLabel state =new JLabel("State");
        state.setBounds(30,230,200,25);
        add(state);
        
        stateInput=new JTextField();
        stateInput.setBounds(230,230,200,25);
        add(stateInput);
        
        
        JLabel email =new JLabel("E-mail id");
        email.setBounds(30,270,200,25);
        add(email);
        
        emailInput=new JTextField();
        emailInput.setBounds(230,270,200,25);
        add(emailInput);
        
        
        
        JLabel phone =new JLabel("phone");
        phone.setBounds(30,310,200,25);
        add(phone);
        
        phoneInput=new JTextField();
        phoneInput.setBounds(230,310,200,25);
        add(phoneInput);
        
        
        // fetch the information using the meter no 
         try {
             Conn c=new Conn();
             String query="SELECT * FROM customer WHERE meter_no=?";
             PreparedStatement pst=c.connection.prepareStatement(query);
             pst.setString(1, meter);
             
             ResultSet rs=pst.executeQuery();
             
             while (rs.next()) {
                 nameInput.setText(rs.getString("name"));
                 meterNumberInput.setText(rs.getString("meter_no"));
                 addressInput.setText(rs.getString("address"));
                 cityInput.setText(rs.getString("city"));
                 stateInput.setText(rs.getString("state"));
                 emailInput.setText(rs.getString("email"));
                 phoneInput.setText(rs.getString("phone_no"));
                 
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
        
        update=new JButton("Update");
        update.setBackground(Color.pink);
        update.setForeground(Color.black);
        update.setFont(new Font("Arial",Font.BOLD,20));
        update.setBounds(200,350,150,30);
        update.addActionListener(this);
        add(update);
        
        cancel=new JButton("Cancel");
        cancel.setBackground(Color.cyan);
        cancel.setForeground(Color.black);
        cancel.setFont(new Font("Arial",Font.BOLD,20));
        cancel.setBounds(400,350,150,30);
        cancel.addActionListener(this);
        add(cancel);
        
        
        
        
        // add image
         ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
         Image i2=i1.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
         ImageIcon i3=new ImageIcon(i2);
         JLabel image=new JLabel(i3);
         image.setBounds(500,50,600,300);
         add(image);
         
         
         
         
         setVisible(true);
    
    
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==update) {
            String saddress=addressInput.getText();
            String scity=cityInput.getText();
            String Sstate=stateInput.getText();
            String semail=emailInput.getText();
            String sphone=phoneInput.getText();
            
            try {
                
                Conn c=new Conn();
                String query ="UPDATE customer SET address=?,city=?,state=?,email=?,phone_no=? WHERE meter_no=?"; 
               PreparedStatement pst = c.connection.prepareStatement(query);

                pst.setString(1, saddress);
                pst.setString(2, scity);
                pst.setString(3, Sstate);
                pst.setString(4, semail);
                pst.setString(5, sphone);
                pst.setString(6, meter);   // WHERE wala last
                
               pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Customer Detail Updated Successfully");
                setVisible(false);
            } catch (Exception ee) {
                ee.printStackTrace();
            }
            
            
            
        }else{
            setVisible(false);
        }

    }
    
    public static void main(String[] args) {
        new UpdateInformation("");
    }

}
