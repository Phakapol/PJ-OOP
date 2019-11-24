import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.io.*;


public class Balance implements ActionListener {

    private JFrame fr;
    private JPanel p;
    private JLabel Day_lbl, Week_lbl, Month_lbl;
    private JTextField Money_txt, Day_txt, Week_txt, Month_txt, Cal_txt;
    private JButton Cal_btn, Home_btn;

    private int AllDay_val, Cal_val;

    private String user;
    private int id;

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
            if (Month_txt.getText().equals("") && Week_txt.getText().equals("") && Day_txt.getText().equals("")) {
                AllDay_val = 0;
            } else if (Month_txt.getText().equals("") && Week_txt.getText().equals("")) {
                AllDay_val = Integer.parseInt(Day_txt.getText());
            } else if (Month_txt.getText().equals("") && Day_txt.getText().equals("")) {
                AllDay_val = Integer.parseInt(Week_txt.getText()) * 7;
            } else if (Week_txt.getText().equals("") && Day_txt.getText().equals("")) {
                AllDay_val = Integer.parseInt(Month_txt.getText()) * 30;
            } else if (Month_txt.getText().equals("")) {
                AllDay_val = Integer.parseInt(Week_txt.getText()) * 7 + Integer.parseInt(Day_txt.getText());
            } else if (Week_txt.getText().equals("")) {
                AllDay_val = Integer.parseInt(Month_txt.getText()) * 30 + Integer.parseInt(Day_txt.getText());
            } else if (Day_txt.getText().equals("")) {
                AllDay_val = Integer.parseInt(Month_txt.getText()) * 30 + Integer.parseInt(Week_txt.getText()) * 7;
            } else {
                AllDay_val = Integer.parseInt(Month_txt.getText()) * 30 + Integer.parseInt(Week_txt.getText()) * 7 + Integer.parseInt(Day_txt.getText());
            }

            if (Money_txt.getText().equals("") || AllDay_val == 0) {
            } else {
                Cal_val = Integer.parseInt(Money_txt.getText()) / AllDay_val;
                Cal_txt.setText(String.valueOf((int) Cal_val));

                try {
                    FileInputStream fin;
                    DataInputStream din;
                    fin = new FileInputStream("data.dat");
                    din = new DataInputStream(fin);
                    id = din.readInt();
                    user = din.readUTF();
                    din.close();
                    fin.close();
                } catch (IOException ex) {
                    System.out.println(ex.toString());
                }

                Connection connect = null;
                PreparedStatement pre = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    connect = DriverManager.getConnection("jdbc:mysql://localhost/RecordIncome", "root","147258");

                    String sql = "UPDATE user SET user_balance=? WHERE user_id=? and user_name=?";
                    pre = connect.prepareStatement(sql);
                    pre.setInt(1, Cal_val);
                    pre.setInt(2, id);
                    pre.setString(3, user);

                    pre.executeUpdate();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } try {
                    pre.close();
                    connect.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (ae.getSource().equals(Home_btn)) {
            new Home();
            fr.dispose();
        }
    }
}
