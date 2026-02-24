
package electricity.managment.system;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class MeterInfo extends JFrame implements ActionListener{
    
//    JTextField custoNameInput,addressInput,cityInput,stateInput,emailInput,phNumInput;
    JButton submit;
     JLabel MeterLocation, MeterType,PhaseCode,BillType;
     Choice meterChoice,meterType,phaseCode,billtype;
     String meternumber;
    MeterInfo(String meternumber){
        this.meternumber=meternumber;
        
        // add the panel on frame 
        JPanel panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(173,216,230));
        add(panel);
        
        // add label's on the panel 
        JLabel heading =new JLabel("Meter Information");
        heading.setBounds(180,10,200,25);
        heading.setFont(new Font("Tahoma",Font.PLAIN,24));
        panel.add(heading);
        
        JLabel meterNumber =new JLabel("Meter Number :");
        meterNumber.setBounds(100,80,100,25);
        panel.add(meterNumber);
      
     
        
        // new Lable for Meter No.
        JLabel MeterNo =new JLabel(meternumber);
        MeterNo.setBounds(240,80,100,25);
        panel.add(MeterNo);
        
        // new Lable for autogenrate MeterNumber 
       MeterLocation =new JLabel("Meter Location");
        MeterLocation.setBounds(100,110,100,25);
        panel.add(MeterLocation);
        
           // Meter Choice 
        meterChoice =new Choice();
        meterChoice.add("OutSide");
        meterChoice.add("Inside");
        meterChoice.setBounds(240,110,170,35);
        panel.add(meterChoice);
        
         
        MeterType =new JLabel("Meter Type:");
        MeterType.setBounds(100,140,100,25);
        panel.add(MeterType);
        
           // Meter Choice 
        meterType =new Choice();
        meterType.add("Electric Meter");
        meterType.add("Solar Meter");
        meterType.add("Smart Meter");
        meterType.setBounds(240,140,170,35);
        panel.add(meterType);
        
        
          
        PhaseCode =new JLabel("Phase Code:");
        PhaseCode.setBounds(100,170,100,25);
        panel.add(PhaseCode);
        
           // Meter Choice 
        phaseCode =new Choice();
        phaseCode.add("011");
        phaseCode.add("022");
        phaseCode.add("033");
        phaseCode.add("044");
        phaseCode.add("055");
        phaseCode.add("066");
        phaseCode.add("077");
        phaseCode.add("088");
        phaseCode.add("099");
        phaseCode.setBounds(240,170,170,35);
        panel.add(phaseCode);
        
       
        
        // For next Lable and Text field 
       BillType =new JLabel("Bill Type:");
        BillType.setBounds(100,200,100,25);
        panel.add(BillType);
        
        billtype =new Choice();
        billtype.add("Normal");
        billtype.add("Industrial");
        billtype.setBounds(240,200,170,35);
        panel.add(billtype);
        
        
        // For next Lable and Text field 
        JLabel days =new JLabel("Days:");
        days.setBounds(100,230,100,25);
        panel.add(days);
        
        JLabel Days =new JLabel("30 days:");
        Days.setBounds(240,230,100,25);
        panel.add(Days);
        
        // For next Lable and Text field 
        JLabel Note =new JLabel("Note:");
        Note.setBounds(100,260,100,25);
        panel.add(Note);
        
        JLabel note =new JLabel("By Default Bill is calculated for 30 days only:");
        note.setBounds(240,260,250,25);
        panel.add(note);
        // Let's add button 
        submit=new JButton("Submit");
        submit.setFont(new Font("Verdana",Font.BOLD,14));
        submit.setBounds(220,300,100,30);
        submit.setBackground(Color.cyan);
        submit.setForeground(Color.BLACK);
        submit.addActionListener(this);
        panel.add(submit);
        
        
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

    if (e.getSource() == submit) {

        String meter = meternumber;
        String location = meterChoice.getSelectedItem();
        String type = meterType.getSelectedItem();
        String code = phaseCode.getSelectedItem();
        String typeBill = billtype.getSelectedItem();
        String days = "30";

        String query = "INSERT INTO meter_info (meter_no,meter_location,meter_type,phase_code,bill_type,days) VALUES (?,?,?,?,?,?)";

        try {
            Conn c = new Conn();
            PreparedStatement pst = c.connection.prepareStatement(query);

            pst.setString(1, meter);
            pst.setString(2, location);
            pst.setString(3, type);
            pst.setString(4, code);
            pst.setString(5, typeBill);
            pst.setString(6, days);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Meter Information Added Successfully");

            setVisible(false);   // window close after submit

        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }
}
    public static void main(String[] args) {
        new MeterInfo("");
    }
   
}


