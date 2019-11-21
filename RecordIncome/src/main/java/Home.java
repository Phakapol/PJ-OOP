import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Home implements ActionListener {

    private JFrame fr;
    private JPanel p;
    //private JTextField Balance_txt;
    private JButton Balance, Record, Profile;

    public Home() {
        fr = new JFrame("RecordIncome");
        p = new JPanel();
        //Balance_txt = new JTextField("0");
        Balance = new JButton("จัดการเงิน");
        Record = new JButton("บันทึก");
        Profile = new JButton("โปรไฟล์");

        Balance.addActionListener(this);
        Record.addActionListener(this);
        Profile.addActionListener(this);

        fr.setLayout(new GridLayout(1, 1));
        p.setLayout(null);

        //Balance_txt.setBounds(10, 10, 100, 100);
        Balance.setBounds(299, 2, 286, 179);
        Record.setBounds(1, 181, 297, 181);
        Profile.setBounds(299, 181, 286, 181);

        //Balance_txt.setFont(new Font("ANGSANA NEW", Font.BOLD, 50));
        Balance.setFont(new Font("ANGSANA NEW", Font.BOLD, 50));
        Record.setFont(new Font("ANGSANA NEW", Font.BOLD, 50));
        Profile.setFont(new Font("ANGSANA NEW", Font.BOLD, 50));

        //Balance_txt.setHorizontalAlignment(JTextField.CENTER);

        //p.add(Balance_txt);
        p.add(Balance);
        p.add(Record);
        p.add(Profile);

        fr.add(p);

        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(600, 400);
        fr.setVisible(true);
        fr.setLocationRelativeTo(null);
        fr.setResizable(false);
    }

    public static void main(String[] args) {
        new Home();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
