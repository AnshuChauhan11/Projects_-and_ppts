package electricity.managment.system;

import com.mysql.cj.jdbc.PreparedStatementWrapper;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import  java.sql.PreparedStatement;
import  java.sql.ResultSet;

public class Login extends  JFrame implements  ActionListener{
    
    JButton loginButton,cancelButton,signButton;
    JPasswordField p;
    JTextField username;
    JComboBox comboBox;
    
    Login(){
        super("Login Page : ");
        
        // For get all access of the frame 
        getContentPane().setBackground(Color.cyan);
        setLayout(null);
        
        // Lable for the User name 
        JLabel lbluserName =new JLabel("Username:");
        lbluserName.setBounds(300,20,100,30);
        add(lbluserName);
        
        // Text Feild for the user Name 
         username=new JTextField();
        username.setBounds(400,20,150,25);
        add(username);
        
        
        
         // Lable for the Password  
        JLabel lblPassword =new JLabel("Password :");
        lblPassword.setBounds(300,60,100,30); 
        add(lblPassword);
        
        // for the Pass WOrd 
         p=new JPasswordField();
        p.setBounds(400,60,150,25);
        add(p);
        
        
        
         // Lable for Login as
        JLabel LoginAs =new JLabel("Login As :");
        LoginAs.setBounds(300,100,100,30);
        add(LoginAs);
        
        // For the LOgin as use J
         comboBox=new JComboBox();
//        comboBox.addItem("");
        comboBox.addItem("Admin");
        comboBox.addItem("Customer");
        comboBox.setBounds(400,100,150,25);
        add(comboBox);
        
        // Button For signUp LOgin , Cancel 
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image i2=i1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
         loginButton=new JButton("Login",new ImageIcon(i2));
        loginButton.setBounds(330,160,100,20);
        loginButton.addActionListener(this);
        add(loginButton);
        
        // cancel button 
        ImageIcon i11=new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image i22=i11.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
         cancelButton=new JButton("Cancel",new ImageIcon(i22));
        cancelButton.setBounds(440,160,100,20);
        cancelButton.addActionListener(this);
        add(cancelButton);

        // sign up button
        ImageIcon i111=new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image i222=i111.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
         signButton=new JButton("SignUp",new ImageIcon(i222));
        signButton.setBounds(380,200,100,20);   // ✔ FIXED
        signButton.addActionListener(this);
        add(signButton);
        
        // for add big image in left side 
        ImageIcon i1111=new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image i2222=i1111.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i33=new ImageIcon(i2222);
        JLabel imageJLabel=new JLabel(i33);
        imageJLabel.setBounds(0,0,300,300);
        add(imageJLabel);
        
        
        setSize(640,300);
        setLocation(500,300);
        setVisible(true);
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==loginButton) {
            String susername =username.getText();
            String spassword= new String(p.getPassword());
            String scombobox=(String) comboBox.getSelectedItem();
            
            try {
                 Conn c=new Conn();
                 String query ="SELECT * FROM login WHERE user_name=? AND password=? AND user=?";
                 PreparedStatement pst=c.connection.prepareStatement(query);
                 
                 pst.setString(1,susername);
                 pst.setString(2,spassword);
                 pst.setString(3,scombobox);
                 
                 ResultSet rs=pst.executeQuery();
                 
                 if (rs.next()) {
                     String meter=rs.getString("meter_no");
                    JOptionPane.showMessageDialog(null,"Login Successfully ✌️");
                     setVisible(false);
                     new Project(scombobox,meter);
                } else{
                     JOptionPane.showMessageDialog(null,"Invalid Username and Password 😂");
                     username.setText("");
                     p.setText("");
                 }
            } catch (Exception ee) {
                ee.printStackTrace();
            }
            
        }else if (e.getSource()==cancelButton) {
            setVisible(false);
            
        }else if (e.getSource()==signButton) {
            setVisible(false);
            new sign();
        }
    }
  
    public static void main(String[] args) {
        new Login();
    }

    
}
