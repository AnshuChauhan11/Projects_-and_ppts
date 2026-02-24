package electricity.managment.system;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.io.IOException;

public class Project extends JFrame implements ActionListener{

    String actype,meter ;
    JMenuItem newCustomer,customerDetail,calculateBill,depositDetail,viewInformation,updateInformation;
    JMenuItem billDeatil,notepad,calculater,exit,payBill,genrateBill;
    Project(String actype,String meter) {
        this.actype=actype;
        this.meter=meter;
        // Frame maximize
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Add image in frame
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/elect1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1920, 1080, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);

        // Menu bar
        JMenuBar mb = new JMenuBar();
        setJMenuBar(mb);

        // Create first  menu
        JMenu master = new JMenu("Master");
        master.setForeground(Color.blue);
       

        // Add menu item
         newCustomer = new JMenuItem("New Customer");
        newCustomer.setFont(new Font("monospaced", Font.PLAIN, 12));
        newCustomer.setBackground(Color.lightGray);

        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("icon/icon1.png"));
        Image image1 = icon1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        newCustomer.setIcon(new ImageIcon(image1));

        // Mnemonic (Alt+N)
        newCustomer.setMnemonic('N');
        newCustomer.addActionListener(this);
        // Accelerator (Ctrl+N) ✅ Corrected
        newCustomer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        master.add(newCustomer);
        
        // Add second menu item
         customerDetail = new JMenuItem("Customer Details");
        customerDetail.setFont(new Font("monospaced", Font.PLAIN, 12));
        customerDetail.setBackground(Color.lightGray);

        ImageIcon icon2 = new ImageIcon(ClassLoader.getSystemResource("icon/icon2.png"));
        Image image2 = icon2.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        customerDetail.setIcon(new ImageIcon(image2));

        // Mnemonic (Alt+M)
        customerDetail.setMnemonic('M');
        customerDetail.addActionListener(this);
        // Accelerator (Ctrl+M) ✅ Corrected
        customerDetail.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK));
        master.add(customerDetail);
        
        // Add third menu item
       depositDetail = new JMenuItem("Deposit Details");
        depositDetail.setFont(new Font("monospaced", Font.PLAIN, 12));
        depositDetail.setBackground(Color.lightGray);

        ImageIcon icon3 = new ImageIcon(ClassLoader.getSystemResource("icon/icon3.png"));
        Image image3 = icon3.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        depositDetail.setIcon(new ImageIcon(image3));

        // Mnemonic (Alt+D)
        depositDetail.setMnemonic('D');
        depositDetail.addActionListener(this);
        // Accelerator (Ctrl+D) ✅ Corrected
        depositDetail.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
        master.add(depositDetail);
        
        
        // Add fourth menu item
        calculateBill = new JMenuItem("Calculate Bill");
        calculateBill.setFont(new Font("monospaced", Font.PLAIN, 12));
        calculateBill.setBackground(Color.lightGray);

        ImageIcon icon4 = new ImageIcon(ClassLoader.getSystemResource("icon/icon5.png"));
        Image image4 = icon4.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        calculateBill.setIcon(new ImageIcon(image4));

        // Mnemonic (Alt+F)
        calculateBill.setMnemonic('F');
        calculateBill.addActionListener(this);
        // Accelerator (Ctrl+F) ✅ Corrected
        calculateBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
        master.add(calculateBill);
        
        
        /*-----------------------------------------------------------------------------------------------*/
        // Create second  menu
        JMenu info = new JMenu("Information");
        info.setForeground(Color.red);
       
        
        // Add first menu item of information
         updateInformation = new JMenuItem("Update information");
        updateInformation.setFont(new Font("monospaced", Font.PLAIN | Font.BOLD, 12));
        updateInformation.setBackground(Color.lightGray);

        ImageIcon icon5 = new ImageIcon(ClassLoader.getSystemResource("icon/icon4.png"));
        Image image5 = icon5.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        updateInformation.setIcon(new ImageIcon(image5));
        updateInformation.addActionListener(this);

        // Mnemonic (Alt+U)
        updateInformation.setMnemonic('U');

        // Accelerator (Ctrl+U) ✅ Corrected
        updateInformation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK));
        info.add(updateInformation);
        
        // Add second menu item of information
         viewInformation = new JMenuItem("View information");
        viewInformation.setFont(new Font("monospaced", Font.PLAIN | Font.BOLD, 12));
        viewInformation.setBackground(Color.lightGray);

        ImageIcon icon6 = new ImageIcon(ClassLoader.getSystemResource("icon/icon6.png"));
        Image image6 = icon6.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        viewInformation.setIcon(new ImageIcon(image6));
        viewInformation.addActionListener(this);
        // Mnemonic (Alt+V)
        viewInformation.setMnemonic('V');

        // Accelerator (Ctrl+V) ✅ Corrected
        viewInformation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        info.add(viewInformation);
        
        /*-----------------------------------------------------------------------------------------------*/
        // Create third  menu
        JMenu user = new JMenu("User");
        user.setForeground(Color.ORANGE);
      
        
        // Add first menu item of User Menu
         payBill = new JMenuItem("PayBill");
        payBill.setFont(new Font("monospaced", Font.PLAIN | Font.BOLD, 12));
        payBill.setBackground(Color.lightGray);

        ImageIcon icon7 = new ImageIcon(ClassLoader.getSystemResource("icon/icon7.png"));
        Image image7 = icon7.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        payBill.setIcon(new ImageIcon(image7));

        // Mnemonic (Alt+U)
        payBill.setMnemonic('P');
        payBill.addActionListener(this);
        // Accelerator (Ctrl+P) ✅ Corrected
        payBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
        user.add(payBill);
        
        // Add second menu item of User menu
        billDeatil = new JMenuItem("Bill Details");
        billDeatil.setFont(new Font("monospaced", Font.PLAIN | Font.BOLD, 12));
        billDeatil.setBackground(Color.lightGray);

        ImageIcon icon8 = new ImageIcon(ClassLoader.getSystemResource("icon/icon8.png"));
        Image image8 = icon8.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        billDeatil.setIcon(new ImageIcon(image8));

        // Mnemonic (Alt+O)
        billDeatil.setMnemonic('O');
        billDeatil.addActionListener(this);
        // Accelerator (Ctrl+V) ✅ Corrected
        billDeatil.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        user.add(billDeatil);
        
        
        /*-----------------------------------------------------------------------------------------------*/
        // Create fourth  menu
        JMenu report = new JMenu("Report");
        user.setForeground(Color.blue);
        
        
        // Add first menu item of report Menu
         genrateBill = new JMenuItem("Genrate Bill");
        genrateBill.setFont(new Font("monospaced", Font.PLAIN | Font.BOLD, 12));
        genrateBill.setBackground(Color.lightGray);

        ImageIcon icon9 = new ImageIcon(ClassLoader.getSystemResource("icon/icon9.png"));
        Image image9 = icon9.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        genrateBill.setIcon(new ImageIcon(image9));

        // Mnemonic (Alt+U)
        genrateBill.setMnemonic('G');
        genrateBill.addActionListener(this);
        // Accelerator (Ctrl+P) ✅ Corrected
        genrateBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
        report.add(genrateBill);
        
        
        /*-----------------------------------------------------------------------------------------------*/
        // Create fifth  menu
        JMenu utility = new JMenu("Utility");
        utility.setForeground(Color.blue);
       
        
        // Add first menu item of Utility Menu
         notepad = new JMenuItem("NotePad");
        notepad.setFont(new Font("monospaced", Font.PLAIN | Font.BOLD, 12));
        notepad.setBackground(Color.lightGray);

        ImageIcon icon10 = new ImageIcon(ClassLoader.getSystemResource("icon/icon12.png"));
        Image image10 = icon10.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        notepad.setIcon(new ImageIcon(image10));

        // Mnemonic (Alt+H)
        notepad.setMnemonic('H');
        notepad.addActionListener(this);
        // Accelerator (Ctrl+H) ✅ Corrected
        notepad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));
        utility.add(notepad);
        
        
          // Add second menu item of Utility Menu
         calculater = new JMenuItem("Calculater");
        calculater.setFont(new Font("monospaced", Font.PLAIN | Font.BOLD, 12));
        calculater.setBackground(Color.lightGray);

        ImageIcon icon11 = new ImageIcon(ClassLoader.getSystemResource("icon/icon9.png"));
        Image image11 = icon11.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        calculater.setIcon(new ImageIcon(image11));

        // Mnemonic (Alt+C)             
        calculater.setMnemonic('C');
        calculater.addActionListener(this);

        // Accelerator (Ctrl+C) ✅ Corrected
        calculater.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        utility.add(calculater);
        
        
        /*-----------------------------------------------------------------------------------------------*/
        // Create six  menu
        JMenu Exit = new JMenu("Exit");
        Exit.setForeground(Color.red);
       
        
        // Add first menu item of Utility Menu
         exit = new JMenuItem("Exit");
        exit.setFont(new Font("monospaced", Font.PLAIN | Font.BOLD, 12));
        exit.setBackground(Color.lightGray);

        ImageIcon icon12 = new ImageIcon(ClassLoader.getSystemResource("icon/icon11.png"));
        Image image12 = icon12.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        exit.setIcon(new ImageIcon(image12));

        // Mnemonic (Alt+H)
        exit.setMnemonic('X');
        exit.addActionListener(this);
        // Accelerator (Ctrl+H) ✅ Corrected
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        Exit.add(exit);
        
        
        if(actype.equals("Admin")){
         mb.add(master);
        
        }else{
        mb.add(info);
         mb.add(user);
         mb.add(report);
        }
         mb.add(utility);
         mb.add(Exit);
        
        setLayout(new FlowLayout());
        setVisible(true);
    }
   @Override
public void actionPerformed(ActionEvent e) {

    if (e.getSource() == newCustomer) {
        new NewCustomer();

    } else if (e.getSource() == customerDetail) {

        new CustomerDetail(); 

    } else if (e.getSource() == depositDetail) {

       new DepositDetail();

    } else if (e.getSource() == calculateBill) {

        new CalculateBill();
    } else if (e.getSource()==viewInformation){
        new ViewInformation(meter);
    } else if(e.getSource()==updateInformation){
        new UpdateInformation(meter);
    } else if (e.getSource()==billDeatil) {
           new BillDetail(meter);
       
    } else if (e.getSource()==payBill){
            new PayBill(meter);
    }else if (e.getSource()==notepad) {
        try {
            Runtime.getRuntime().exec("notepad.exe");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
     } else if (e.getSource()==calculater){
         try {
            Runtime.getRuntime().exec("calc.exe");
        } catch (Exception ey) {
        ey.printStackTrace();
        }
     }  else if (e.getSource()==exit){
         setVisible(false);
         new Login();
     } else if(e.getSource()==genrateBill){
         new GenrateBIll(meter);
     }
}
    public static void main(String[] args) {
        new Project("","");
    }

   
}