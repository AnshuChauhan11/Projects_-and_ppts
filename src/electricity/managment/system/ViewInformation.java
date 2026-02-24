
package electricity.managment.system;

import com.mysql.cj.xdevapi.PreparableStatement;
import java.awt.Color;
import java.awt.Font;
//import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class ViewInformation extends  JFrame implements  ActionListener{

    JButton cancel;
    JLabel nameInput,meterNumberInput,addressInput,cityInput,stateInput,emailInput,phoneInput;
//    String meter ;
     ViewInformation(String meter)  {
         
//         this.meter=meter;
         setBounds(350,150,850,650);
         getContentPane().setBackground(Color.white);
         setLayout(null);
         
        JLabel heading =new JLabel("View Customer Information");
        heading.setBounds(250,20,250,30);
        heading.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(heading);
       
        
        JLabel name =new JLabel("Name");
        name.setBounds(70,80,100,25);
        add(name);
        
        nameInput=new JLabel("");
        nameInput.setBounds(250,80,150,25);
         add(nameInput);
         
         
        JLabel meterNumber =new JLabel("Meter Numbers");
        meterNumber.setBounds(70,140,100,25);
        add(meterNumber);
        
        meterNumberInput=new JLabel("");
        meterNumberInput.setBounds(250,140,150,25);
        add(meterNumberInput);
        
         
        JLabel address =new JLabel("Address");
        address.setBounds(70,200,100,25);
        add(address);
        
        addressInput=new JLabel("");
        addressInput.setBounds(250,200,150,25);
        add(addressInput); 
        
        JLabel city =new JLabel("City");
        city.setBounds(70,260,100,25);
        add(city);
        
        cityInput=new JLabel("");
        cityInput.setBounds(250,260,150,25);
        add(cityInput); 
        
        JLabel state =new JLabel("State");
        state.setBounds(500,80,100,25);
        add(state);
        
        stateInput=new JLabel("");
        stateInput.setBounds(650,80,150,25);
        add(stateInput);
        
        
        JLabel email =new JLabel("E-mail id");
        email.setBounds(500,140,100,25);
        add(email);
        
        emailInput=new JLabel("");
        emailInput.setBounds(650,140,150,25);
        add(emailInput);
        
        
        
        JLabel phone =new JLabel("phone");
        phone.setBounds(500,200,100,25);
        add(phone);
        
        phoneInput=new JLabel("");
        phoneInput.setBounds(650,200,150,25);
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
        
        
        cancel=new JButton("Cancel");
        cancel.setBackground(Color.white);
        cancel.setForeground(Color.black);
        cancel.setFont(new Font("Arial",Font.BOLD,20));
        cancel.setBounds(300,350,150,30);
        cancel.addActionListener(this);
        add(cancel);
        
        
        // add image
         ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.jpg"));
         Image i2=i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
         ImageIcon i3=new ImageIcon(i2);
         JLabel image=new JLabel(i3);
         image.setBounds(20,350,600,300);
         add(image);
         
         
         
         setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==cancel) {
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new ViewInformation("");
    }

}
