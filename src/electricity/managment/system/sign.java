
package electricity.managment.system;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.ComboBoxUI;
import java.sql.PreparedStatement;
import  java.sql.ResultSet;
import java.sql.SQLException;


public class sign extends JFrame implements ActionListener{
    JButton create,back;
    JTextField mInput,userNameInput,NameInput;
    JPasswordField  passwordInput;
    Choice choice;
     PreparedStatement pstmt;
    sign(){
        
        setBounds(450,150,700,400);
        getContentPane().setBackground(Color.white);
        setLayout(null);


        JPanel panel=new JPanel();
        panel.setBounds(30,30,650,300);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(173,216,230),2),"Create-Account"));
        panel.setBackground(Color.white);
        panel.setLayout(null);
        panel.setForeground(Color.black);
        add(panel);
        
        // add labale 
        JLabel heading =new JLabel("Create Account As: ");
        heading.setBounds(100,50,150,26);
        heading.setBackground(Color.cyan);
        heading.setForeground(Color.black);
        heading.setFont(new Font("Tahoma",Font.BOLD,15));
        panel.add(heading);
        
        // add choice 
        choice=new Choice();
        choice.add("Admin");
        choice.add("Customer");
        choice.setBounds(260,50,150,26);
        panel.add(choice);
//        choice.addFocusListener(l);
        
        // meter no 
        JLabel meter =new JLabel("Meter No : ");
        meter.setBounds(100,80,150,26);
        meter.setBackground(Color.cyan);
        meter.setForeground(Color.black);
        meter.setFont(new Font("Tahoma",Font.BOLD,15));
        meter.setVisible(false);
        panel.add(meter);
        
        // text field for the meter no 
        mInput=new JTextField();
        mInput.setBounds(260,80,150,20 );
        mInput.setVisible(false);
        panel.add(mInput);
       
         // user Name input  
        JLabel userName =new JLabel("UserName : ");
        userName.setBounds(100,110,150,26);
        userName.setBackground(Color.cyan);
        userName.setForeground(Color.black);
        userName.setFont(new Font("Tahoma",Font.BOLD,15));
        panel.add(userName);
        
        // text field for the meter no 
         userNameInput=new JTextField();
        userNameInput.setBounds(260,110,150,20 );
        panel.add(userNameInput);
        
           // Name 
        JLabel Name =new JLabel("Name : ");
        Name.setBounds(100,140,150,26);
        Name.setBackground(Color.cyan);
        Name.setForeground(Color.black);
        Name.setFont(new Font("Tahoma",Font.BOLD,15));
        panel.add(Name);
        
        // text field for the meter no 
       NameInput=new JTextField();
        NameInput.setBounds(260,140,150,20 );
        panel.add(NameInput);
        
         mInput.addFocusListener(new FocusListener() {

    @Override
    public void focusGained(FocusEvent e) {}

    @Override
    public void focusLost(FocusEvent e) {

        String meter = mInput.getText();

        if(meter.equals("")) return;

        try {
            Conn c = new Conn();
            String query = "SELECT * FROM login WHERE meter_no=?";
            PreparedStatement pst = c.connection.prepareStatement(query);
            pst.setString(1, meter);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                NameInput.setText(rs.getString("name"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
});
        
           // meter no 
        JLabel password =new JLabel("Password : ");
        password.setBounds(100,180,150,26);
        password.setBackground(Color.cyan);
        password.setForeground(Color.black);
        password.setFont(new Font("Tahoma",Font.BOLD,15));
        panel.add(password);
        
        // text field for the meter no 
         passwordInput=new JPasswordField();
        passwordInput.setBounds(260,180,150,20 );
        panel.add(passwordInput);
        
        choice.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                String user=choice.getSelectedItem();
                if (user=="Customer") {
                    meter.setVisible(true);
                    mInput.setVisible(true);
                    NameInput.setEditable(false);
                }else{
                    meter.setVisible(false);
                    mInput.setVisible(false);
                }
            }
        
            
        });
                
                
                
                
        // create button 
         create =new JButton("Create ");
        create.setBackground(Color.BLACK);
        create.setForeground(Color.white );
        create.setBounds(100,240,100,30);
        create.addActionListener(this);
        panel.add(create);
        
          // create button 
         back =new JButton("Back");
        back.setBackground(Color.BLACK);
        back .setForeground(Color.white );
        back.setBounds(220,240,100,30);
        back.addActionListener(this);
        panel.add(back);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
        Image i2=i1.getImage().getScaledInstance(260, 260, Image.SCALE_AREA_AVERAGING);
        ImageIcon i3=new ImageIcon(i2);
        JLabel imageLable=new JLabel(i3);
        imageLable.setBounds(415,30,250,250);
        panel.add(imageLable);
        
        
//        setSize(700,400);
        setVisible(true);
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==create) {
            String acType =choice.getSelectedItem();
            String sUsername=userNameInput.getText();
            String sName=NameInput.getText();
           String spassword = new String(passwordInput.getPassword());
           String smeter=mInput.getText();
           
       try {
    Conn c = new Conn();

if(acType.equals("Admin")) {

    // 🔎 check duplicate admin
    String check = "SELECT * FROM login WHERE meter_no=?";
    PreparedStatement checkPst = c.connection.prepareStatement(check);
    checkPst.setString(1, smeter);
    ResultSet rs = checkPst.executeQuery();

    if(rs.next()){
        JOptionPane.showMessageDialog(null,"Admin already exists for this meter!");
        return;
    }

    // ✅ INSERT
    String query = "INSERT INTO login(meter_no, user_name, name, password, user) VALUES (?, ?, ?, ?, ?)";
    pstmt = c.connection.prepareStatement(query);

    pstmt.setString(1, smeter);
    pstmt.setString(2, sUsername);
    pstmt.setString(3, sName);
    pstmt.setString(4, spassword);
    pstmt.setString(5, acType);

}
else {

    // ✅ UPDATE for Customer
    String query = "UPDATE login SET user_name=?, password=?, user=? WHERE meter_no=?";
    pstmt = c.connection.prepareStatement(query);

    pstmt.setString(1, sUsername);
    pstmt.setString(2, spassword);
    pstmt.setString(3, acType);
    pstmt.setString(4, smeter);
}

   int rowaffected= pstmt.executeUpdate();

   if(rowaffected>0){
    JOptionPane.showMessageDialog(null,"Account Created / Updated Successfully!");
    setVisible(false);
        new Login();
   }else{
       JOptionPane.showMessageDialog(null, "Meter NO. not found ! Update Failed ");
   }
} catch (Exception ex) {
    ex.printStackTrace();
}
            
        }else if (e.getSource()==back) {
            setVisible(false);
            new Login();
        }
    }
    
    public static void main(String[] args) {
        new sign();
    }
}
