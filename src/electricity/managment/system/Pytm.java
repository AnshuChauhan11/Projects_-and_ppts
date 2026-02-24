package electricity.managment.system;

import javax.swing.*;
import java.awt.*;
import java.net.URI;

public class Pytm extends JFrame {
    String meter;

    Pytm(String meter){
        this.meter = meter;

        setTitle("Paytm Payment");
        setSize(600,400);
        setLocation(400,200);
        setLayout(new BorderLayout());

        JLabel heading = new JLabel("Redirecting to Paytm Payment Gateway...", JLabel.CENTER);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        heading.setForeground(Color.BLUE);
        add(heading, BorderLayout.CENTER);

        JLabel note = new JLabel("Please complete payment in browser", JLabel.CENTER);
        note.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(note, BorderLayout.SOUTH);

        setVisible(true);

        // ⭐ Open Paytm in default browser
        try {
            Desktop.getDesktop().browse(
                new URI("https://paytm.com/electricity-bill-payment")
//                    new URL("https://paytm.com/online-payments")
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unable to open browser");
        }
    }

    public static void main(String[] args) {
        new Pytm("");
    }
}