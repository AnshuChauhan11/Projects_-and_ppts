
package electricity.managment.system;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GenrateBIll extends  JFrame implements ActionListener{

    String meter ;
    Choice cmonth ;
    JScrollPane pane;
    JTextArea area;
    JButton bill;
    JLabel meterNumber;
    GenrateBIll(String meter )  {
    
        this.meter=meter ;
        setSize(500,800);
        setLocation(550,30);
        setLayout(new BorderLayout());
        
        
        JPanel panel=new JPanel();
        
        
        JLabel heading =new JLabel("Genrate Bill");
//        heading.setFont(new Font("Tahoma",Font.PLAIN,20));
//        heading.setBounds(150,0,200,30);
//        panel.add(heading);
//        

        meterNumber =new JLabel(meter);

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
        
         area=new JTextArea();
         area.setText("\n\n\t-------Click on the----\n\t-----Genrate Bill Button---\n\t---for the selected month---\n\t");
         area.setFont(new Font("Senserif",Font.ITALIC,17));
         
         pane=new JScrollPane(area);
         
         
         
         bill =new JButton("Genrate Bill");
         bill.addActionListener(this);
         
         panel.add(heading);
         panel.add(meterNumber);
         panel.add(cmonth);
//         panel.add(area);
         
         add(panel,"North");
         add(pane,"Center");
         add(bill,"South");
         
        setVisible(true);
    }
 
   
   

        @Override
public void actionPerformed(ActionEvent e) {

    try {
        Conn c=new Conn();
        String month =cmonth.getSelectedItem();

        area.setText("\tReliance Power Limited\n");
        area.append("\nELECTRICITY BILL GENERATED FOR THE MONTH OF "+month+" 2026\n");
        area.append("\n====================================================\n");

        // ---------------- CUSTOMER ----------------
        String query ="SELECT * FROM customer WHERE meter_no=?";
        PreparedStatement pst =c.connection.prepareStatement(query);
        pst.setString(1, meter);
        ResultSet rs =pst.executeQuery();

        while (rs.next()) {
            area.append("\nCustomer Name : "+rs.getString("name"));
            area.append("\nMeter Number  : "+rs.getString("meter_no"));
            area.append("\nAddress       : "+rs.getString("address"));
            area.append("\nCity          : "+rs.getString("city"));
            area.append("\nState         : "+rs.getString("state"));
            area.append("\nEmail         : "+rs.getString("email"));
            area.append("\nPhone         : "+rs.getString("phone_no"));
        }

        area.append("\n\n------------- METER INFO -------------\n");

        // ---------------- METER INFO ----------------
        String query1 ="SELECT * FROM meter_info WHERE meter_no=?";
        pst =c.connection.prepareStatement(query1);
        pst.setString(1, meter);
        rs =pst.executeQuery();

        while (rs.next()) {
            area.append("\nMeter Location : "+rs.getString("meter_location"));
            area.append("\nMeter Type     : "+rs.getString("meter_type"));
            area.append("\nPhase Code     : "+rs.getString("phase_code"));
            area.append("\nBill Type      : "+rs.getString("bill_type"));
            area.append("\nDays           : "+rs.getString("days"));
        }

        area.append("\n\n------------- TAX DETAILS -------------\n");

        // ---------------- TAX TABLE (NEW) ----------------
        String query2 ="SELECT * FROM tax";
        pst =c.connection.prepareStatement(query2);
        rs =pst.executeQuery();

        while (rs.next()) {
            area.append("\nCost Per Unit        : "+rs.getString("cost_per_unit"));
            area.append("\nMeter Rent           : "+rs.getString("meter_rent"));
            area.append("\nService Charge       : "+rs.getString("service_charge"));
            area.append("\nService Tax          : "+rs.getString("service_tax"));
            area.append("\nSwachh Bharat Cess   : "+rs.getString("swacch_bharat_cess"));
            area.append("\nFixed Tax            : "+rs.getString("fixed_tax"));
        }

        area.append("\n\n=========== THANK YOU ==========");

    } catch (Exception ee) {
        ee.printStackTrace();
    }
}
    
    public static void main(String[] args) {
        new GenrateBIll("");
    }

}
