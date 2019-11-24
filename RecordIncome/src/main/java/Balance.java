import java.awt.*;
import java.awt.event.*;
import java.time.Month;
import javax.swing.*;

public class Balance implements ActionListener {

    private JFrame fr;
    private JPanel p;
    private JLabel Day_lbl, Week_lbl, Month_lbl;
    private JTextField Money_txt, Day_txt, Week_txt, Month_txt, Cal_txt;
    private JButton Cal_btn, Home_btn;
    int Month_val, Week_val, Day_val, AllDay_val;

    public Balance () {
        fr = new JFrame("RecordIncome");
        p = new JPanel();
        Day_lbl = new JLabel("วัน : ");
        Week_lbl = new JLabel("อาทิตย์ : ");
        Month_lbl = new JLabel("เดือน : ");
        Money_txt = new JTextField();
        Day_txt = new JTextField();
        Week_txt = new JTextField();
        Month_txt = new JTextField();
        Cal_txt = new JTextField();
        Cal_btn = new JButton("Calculate");
        Home_btn = new JButton("Home");

        Cal_btn.addActionListener(this);
        Home_btn.addActionListener(this);

        fr.setLayout(new GridLayout(1, 1));
        p.setLayout(null);

        Money_txt.setBounds(35, 30, 200, 50);
        Month_lbl.setBounds(270, 30, 280, 30);
        Week_lbl.setBounds(270, 80, 280, 30);
        Day_lbl.setBounds(270, 130, 280, 30);
        Month_txt.setBounds(350, 30, 200, 40);
        Week_txt.setBounds(350, 80, 200, 40);
        Day_txt.setBounds(350, 130, 200, 40);
        Cal_txt.setBounds(270, 230, 280, 50);
        Cal_btn.setBounds(35, 230, 200, 48);
        Home_btn.setBounds(190, 300, 200, 40);

        Day_lbl.setFont(new Font("ANGSANA NEW", Font.BOLD, 30));
        Week_lbl.setFont(new Font("ANGSANA NEW", Font.BOLD, 30));
        Month_lbl.setFont(new Font("ANGSANA NEW", Font.BOLD, 30));

        p.add(Day_lbl);
        p.add(Week_lbl);
        p.add(Month_lbl);
        p.add(Money_txt);
        p.add(Day_txt);
        p.add(Week_txt);
        p.add(Month_txt);
        p.add(Cal_txt);
        p.add(Cal_btn);
        p.add(Home_btn);

        fr.add(p);

        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(600, 400);
        fr.setVisible(true);
        fr.setLocationRelativeTo(null);
        fr.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(Cal_btn)) {
            if (Month_txt.getText().equals("")) {
            } else {
                Month_val = Integer.parseInt(Month_txt.getText()) * 30;
            }
            if (Week_txt.getText().equals("")) {
            } else {
                Week_val = Integer.parseInt(Week_txt.getText()) * 7;
            }
            if (Day_txt.getText().equals("")) {
            } else {
                Day_val = Integer.parseInt(Day_txt.getText());
            }
            AllDay_val = Month_val + Week_val + Day_val;
        } else if (ae.getSource().equals(Home_btn)) {
            new Home();
            fr.dispose();
        }
    }
}
