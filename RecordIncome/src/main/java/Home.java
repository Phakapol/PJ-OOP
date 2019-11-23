import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Home implements ActionListener {

    private JFrame fr;
    private JPanel p;
    private JTextField Balance_txt;
    private JButton Balance_btn, Record_btn, Profile_btn;

    public Home() {
        fr = new JFrame("RecordIncome");
        p = new JPanel();
        Balance_txt = new JTextField("จำนวนที่ใช้ได้ : 0");
        Balance_btn = new JButton("จัดการเงิน");
        Record_btn = new JButton("บันทึก");
        Profile_btn = new JButton("โปรไฟล์");

        Balance_btn.addActionListener(this);
        Record_btn.addActionListener(this);
        Profile_btn.addActionListener(this);

        fr.setLayout(new GridLayout(1, 1));
        p.setLayout(null);

        Balance_txt.setBounds(40, 65, 220, 50);
        Balance_btn.setBounds(299, 2, 286, 179);
        Record_btn.setBounds(1, 181, 297, 181);
        Profile_btn.setBounds(299, 181, 286, 181);

        Balance_txt.setFont(new Font("ANGSANA NEW", Font.BOLD, 30));
        Balance_btn.setFont(new Font("ANGSANA NEW", Font.BOLD, 50));
        Record_btn.setFont(new Font("ANGSANA NEW", Font.BOLD, 50));
        Profile_btn.setFont(new Font("ANGSANA NEW", Font.BOLD, 50));

        Balance_txt.setHorizontalAlignment(JTextField.CENTER);
        Balance_txt.setEditable(false);
        Balance_txt.setBackground(new Color(255, 255, 255));

        p.add(Balance_txt);
        p.add(Balance_btn);
        p.add(Record_btn);
        p.add(Profile_btn);

        fr.add(p);

        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(600, 400);
        fr.setVisible(true);
        fr.setLocationRelativeTo(null);
        fr.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(Balance_btn)) {
            new Balance();
            fr.dispose();
        } else if (ae.getSource().equals(Record_btn)) {
            new Record();
            fr.dispose();
        } else if (ae.getSource().equals(Profile_btn)) {
            new Profile();
            fr.dispose();
        }
    }
}
