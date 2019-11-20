/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author acer
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login implements ActionListener {

    private JFrame fr;
    private JPanel p1, p2, p3, p4;
    private JLabel Username, Password, lbl3;
    private JTextField Username_txt, Password_txt;
    private JButton SignIn, SignUp;

    public Login() {
        fr = new JFrame("RecordIncome");
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        Username_txt = new JTextField();
        Password_txt = new JTextField();
        Username = new JLabel("Username");
        Password = new JLabel("Password");
        lbl3 = new JLabel();
        SignIn = new JButton("Sign In");
        SignUp = new JButton("Sign Up");

        SignIn.addActionListener(this);
        SignUp.addActionListener(this);

        fr.setLayout(new GridLayout(4, 1));
        p1.setLayout(new GridLayout(1, 2));
        p2.setLayout(new GridLayout(1, 2));
        p3.setLayout(new FlowLayout());

        p1.add(Username);
        p1.add(Username_txt);

        p2.add(Password);
        p2.add(Password_txt);

        p3.add(SignIn);
        p3.add(SignUp);

        p4.add(lbl3);

        fr.add(p1);
        fr.add(p2);
        fr.add(p3);
        fr.add(p4);

        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(400, 200);
        fr.setVisible(true);
        fr.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(SignIn)) {
            Connection connect = null;
            PreparedStatement pre = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connect = DriverManager.getConnection("jdbc:mysql://localhost/RecordIncome", "root", "147258");

                String sql = "SELECT * FROM user WHERE user_name=? and user_pass=?";
                pre = connect.prepareStatement(sql);

                pre.setString(1, Username_txt.getText());
                pre.setString(2, Password_txt.getText());

                ResultSet rec = pre.executeQuery();

                System.out.println("Done It");

                if (rec.next()) {
                    new Home();
                    fr.dispose();
                } else {
                    lbl3.setText("Username and Password not Correct");
                    Username_txt.setText("");
                    Password_txt.setText("");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                pre.close();
                connect.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (ae.getSource().equals(SignUp)) {
            new Register();
            fr.dispose();
        }
    }
}
