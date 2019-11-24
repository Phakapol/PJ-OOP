import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;

public class Home implements ActionListener {

    private JFrame fr;
    private JPanel p;
    private  JLabel Balance_lbl;
    private JTextField Balance_txt;
    private JButton Balance_btn, Record_btn, Profile_btn;

    private String user;
    private int id;

    public Home() {
        fr = new JFrame("RecordIncome");
        p = new JPanel();
        Balance_lbl = new JLabel("จำนวนที่ใช้ได้");
        Balance_txt = new JTextField("0");
        Balance_btn = new JButton("จัดการเงิน");
        Record_btn = new JButton("บันทึก");
        Profile_btn = new JButton("โปรไฟล์");

        Balance_btn.addActionListener(this);
        Record_btn.addActionListener(this);
        Profile_btn.addActionListener(this);

        fr.setLayout(new GridLayout(1, 1));
        p.setLayout(null);

        Balance_lbl.setBounds(90, 35, 220, 50);
        Balance_txt.setBounds(40, 85, 220, 50);
        Balance_btn.setBounds(299, 2, 286, 179);
        Record_btn.setBounds(1, 181, 297, 181);
        Profile_btn.setBounds(299, 181, 286, 181);

        Balance_lbl.setFont(new Font("ANGSANA NEW", Font.BOLD, 30));
        Balance_txt.setFont(new Font("ANGSANA NEW", Font.BOLD, 40));
        Balance_btn.setFont(new Font("ANGSANA NEW", Font.BOLD, 50));
        Record_btn.setFont(new Font("ANGSANA NEW", Font.BOLD, 50));
        Profile_btn.setFont(new Font("ANGSANA NEW", Font.BOLD, 50));

        Balance_txt.setHorizontalAlignment(JTextField.CENTER);
        Balance_txt.setEditable(false);
        Balance_txt.setBackground(new Color(255, 255, 255));

        p.add(Balance_lbl);
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
            connect = DriverManager.getConnection("jdbc:mysql://localhost/RecordIncome", "root" ,"147258");
            String sql = "SELECT * FROM user WHERE user_id=? and user_name=?";
            pre = connect.prepareStatement(sql);
            pre.setInt(1, id);
            pre.setString(2, user);
            ResultSet rec = pre.executeQuery();
            if (rec.next()) {
                Balance_txt.setText(rec.getString("user_balance"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } try {
            pre.close();
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
