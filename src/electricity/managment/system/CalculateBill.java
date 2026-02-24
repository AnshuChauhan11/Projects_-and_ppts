package electricity.managment.system;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CalculateBill extends JFrame implements ActionListener, ItemListener{

    JTextField  unitInput, stateInput;
    JButton next, cancel;
    JLabel name,addressInput;
    Choice meterNumber,cmonth;

    CalculateBill(){

        JPanel panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(173,216,230));
        add(panel);

        JLabel heading =new JLabel("Calculate Electricity Bill");
        heading.setBounds(150,10,300,25);
        heading.setFont(new Font("Tahoma",Font.PLAIN,24));
        panel.add(heading);

        JLabel custoName =new JLabel("Meter No.:");
        custoName.setBounds(100,80,100,25);
        panel.add(custoName);

        meterNumber=new Choice();

        // ⭐ Load meter numbers
        try {
            Conn c=new Conn();
            String query = "SELECT meter_no FROM customer";
            PreparedStatement pst = c.connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                meterNumber.add(rs.getString("meter_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        meterNumber.setBounds(240,80,170,20);
        meterNumber.addItemListener(this);   // ⭐ VERY IMPORTANT
        panel.add(meterNumber);

        JLabel MeterNo =new JLabel("Name:");
        MeterNo.setBounds(100,110,100,25);
        panel.add(MeterNo);

        name =new JLabel("");
        name.setBounds(240,110,170,25);
        panel.add(name);

        JLabel address =new JLabel("Address :");
        address.setBounds(100,140,100,25);
        panel.add(address);

        addressInput=new JLabel();
        addressInput.setBounds(240,140,170,20);
        panel.add(addressInput);

        
        
        JLabel city =new JLabel("Unit Consumed :");
        city.setBounds(100,170,100,25);
        panel.add(city);

        unitInput=new JTextField();
        unitInput.setBounds(240,170,170,20);
        panel.add(unitInput);

        JLabel state =new JLabel("Month:");
        state.setBounds(100,200,100,25);
        panel.add(state);

        cmonth =new Choice();
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
        cmonth.setBounds(240,200,170,20);
        panel.add(cmonth);

        next=new JButton("Submit");
        next.setFont(new Font("Verdana",Font.BOLD,14));
        next.setBounds(120,270,100,30);
        next.setBackground(Color.cyan);
        next.setForeground(Color.BLACK);
        next.addActionListener(this);
        panel.add(next);

        cancel=new JButton("Cancel");
        cancel.setFont(new Font("Verdana",Font.BOLD,14));
        cancel.setBounds(250,270,100,30);
        cancel.setBackground(Color.cyan);
        cancel.setForeground(Color.red);
//        cancel.setBounds(250,270,100,30);
        cancel.addActionListener(this);
        panel.add(cancel);

        setSize(700,400);
        setLocation(400,200);
        setLayout(new BorderLayout());
        add(panel,"Center");
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/hicon2.jpg"));
        Image i2=i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        
        JLabel image=new JLabel(i3);
        add(image,"West");

        getContentPane().setBackground(Color.white);

        setVisible(true);
    }

    // ⭐ Auto fill name + address when meter changes
    @Override
    public void itemStateChanged(ItemEvent ie) {
        try {
            Conn c = new Conn();
            String query = "SELECT * FROM customer WHERE meter_no=?";
            PreparedStatement pst = c.connection.prepareStatement(query);
            pst.setString(1, meterNumber.getSelectedItem());
            ResultSet rs = pst.executeQuery();

            if(rs.next()){
                name.setText(rs.getString("name"));
                addressInput.setText(rs.getString("address"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int totalbill=0;
        
        if (e.getSource()==next) {
            String meter=meterNumber.getSelectedItem();
            String units=unitInput.getText();
            String months=cmonth.getSelectedItem();
            
            int unit_consumed=Integer.parseInt(units);
         Conn c=new Conn();
            try {
                String query="SELECT * FROM tax";
                PreparedStatement pst=c.connection.prepareStatement(query);
                ResultSet rs=pst.executeQuery();
                
                while (rs.next()) {
                   totalbill+=unit_consumed*Integer.parseInt(rs.getString("cost_per_unit"));
                   totalbill+=unit_consumed*Integer.parseInt(rs.getString("meter_rent"));
                   totalbill+=unit_consumed*Integer.parseInt(rs.getString("service_charge"));
                   totalbill+=unit_consumed*Integer.parseInt(rs.getString("service_tax"));
                   totalbill+=unit_consumed*Integer.parseInt(rs.getString("swacch_bharat_cess"));
                   totalbill+=unit_consumed*Integer.parseInt(rs.getString("fixed_tax"));
                }
            } catch (Exception ee) {
                ee.printStackTrace();
            }
            
            String query1="INSERT INTO bill(meter_no,month,units,totalbill,status)VALUES(?,?,?,?,?)";
            try {
                PreparedStatement pst=c.connection.prepareStatement(query1);
                pst.setString(1, meter);
                pst.setString(2, months);
                pst.setString(3, units);
                pst.setInt(4, totalbill);
                pst.setString(5, "Not Paid");
                
               pst.executeUpdate();
               
               JOptionPane.showMessageDialog(null, "Customer Bill Updated Successfully");
                setVisible(false);
            } catch (SQLException ex) {
              ex.printStackTrace();
            }
            
            
        }else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new CalculateBill();
    }
}