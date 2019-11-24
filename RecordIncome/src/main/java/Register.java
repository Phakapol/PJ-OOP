import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Register implements ActionListener {
    private JFrame fr;
    private JPanel p;
    private JLabel Username_lbl, Password_lbl, Firstname_lbl, Lastname_lbl, Email_lbl, Error_lbl;
    private JTextField Username_txt, Password_txt, Firstname_txt, Lastname_txt, Email_txt;
    private JButton Ok_btn, Back_btn;

    public Register() {
        fr = new JFrame("RecordIncome");
        p = new JPanel();
        Username_lbl = new JLabel("Username :");
        Password_lbl = new JLabel("Password :");
        Firstname_lbl = new JLabel("Firstname :");
        Lastname_lbl = new JLabel("Lastname :");
        Email_lbl = new JLabel("Email :");
        Error_lbl = new JLabel();
        Username_txt = new JTextField();
        Password_txt = new JTextField();
        Firstname_txt = new JTextField();
        Lastname_txt = new JTextField();
        Email_txt = new JTextField();
        Ok_btn = new JButton("OK");
        Back_btn = new JButton("Back");

        Ok_btn.addActionListener(this);
        Back_btn.addActionListener(this);

        fr.setLayout(new GridLayout(1,1));
        p.setLayout(null);

        Username_lbl.setBounds(100, 60, 180, 30);
        Username_txt.setBounds(100, 90, 180, 30);
        Password_lbl.setBounds(100, 120, 180, 30);
        Password_txt.setBounds(100, 150, 180, 30);
        Firstname_lbl.setBounds(100, 180, 180, 30);
        Firstname_txt.setBounds(100, 210, 180, 30);
        Lastname_lbl.setBounds(100, 240, 180, 30);
        Lastname_txt.setBounds(100, 270, 180, 30);
        Email_lbl.setBounds(100, 300, 180, 30);
        Email_txt.setBounds(100, 330, 180, 30);
        Ok_btn.setBounds(100, 380, 80, 30);
        Back_btn.setBounds(200, 380, 80, 30);

        p.add(Username_lbl);
        p.add(Username_txt);
        p.add(Password_lbl);
        p.add(Password_txt);
        p.add(Firstname_lbl);
        p.add(Firstname_txt);
        p.add(Lastname_lbl);
        p.add(Lastname_txt);
        p.add(Email_lbl);
        p.add(Email_txt);
        p.add(Ok_btn);
        p.add(Back_btn);
        p.add(Error_lbl);

        fr.add(p);

        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(400, 550);
        fr.setVisible(true);
        fr.setLocationRelativeTo(null);
        fr.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(Ok_btn)) {
            if (Username_txt.getText().equals("")) {
                Error_lbl.setBounds(125, 430, 180, 30);
                Error_lbl.setText("Please Enter Username");
            } else if (Password_txt.getText().equals("")) {
                Error_lbl.setBounds(125, 430, 180, 30);
                Error_lbl.setText("Please Enter Password");
            }  else if (Firstname_txt.getText().equals("")) {
                Error_lbl.setBounds(125, 430, 180, 30);
                Error_lbl.setText("Please Enter Firstname");
            }  else if (Lastname_txt.getText().equals("")) {
                Error_lbl.setBounds(125, 430, 180, 30);
                Error_lbl.setText("Please Enter Lastname");
            }  else if (Email_txt.getText().equals("")) {
                Error_lbl.setBounds(140, 430, 180, 30);
                Error_lbl.setText("Please Enter Email");
            } else {
                Connection connect = null;
                PreparedStatement pre = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    connect = DriverManager.getConnection("jdbc:mysql://localhost/RecordIncome", "root","147258");

                    String sql = "INSERT INTO user (user_name, user_pass, user_firstname, user_lastname, user_email) VALUES (?, ?, ?, ?, ?)";
                    pre = connect.prepareStatement(sql);

                    pre.setString(1, Username_txt.getText());
                    pre.setString(2, Password_txt.getText());
                    pre.setString(3, Firstname_txt.getText());
                    pre.setString(4, Lastname_txt.getText());
                    pre.setString(5, Email_txt.getText());

                    pre.executeUpdate();

                } catch (Exception ex) {
                    ex.printStackTrace();
                } try {
                    pre.close();
                    connect.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                new Login();
                fr.dispose();
            }
        } else if (ae.getSource().equals(Back_btn)) {
            new Login();
            fr.dispose();
        }
    }
}