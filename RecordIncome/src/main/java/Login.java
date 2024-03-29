import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;

public class Login implements ActionListener, FocusListener {

    private JFrame fr;
    private JPanel p;
    private JLabel Username_lbl, Password_lbl, Error_lbl;
    private JTextField Username_txt, Password_txt;
    private JButton SignIn_btn, SignUp_btn;

    public Login () {
        fr = new JFrame("RecordIncome");
        p = new JPanel();
        Username_lbl = new JLabel("Username :");
        Password_lbl = new JLabel("Password :");
        Error_lbl = new JLabel();
        Username_txt = new JTextField("Username");
        Password_txt = new JTextField("Password");
        SignIn_btn = new JButton("Sign In");
        SignUp_btn = new JButton("Sign Up");

        SignIn_btn.addActionListener(this);
        SignUp_btn.addActionListener(this);

        Username_txt.addFocusListener(this);
        Password_txt.addFocusListener(this);

        fr.setLayout(new GridLayout(1, 1));
        p.setLayout(null);

        Username_lbl.setBounds(100, 50, 180, 30);
        Username_txt.setBounds(100, 90, 180, 30);
        Password_lbl.setBounds(100, 120, 180, 30);
        Password_txt.setBounds(100, 160, 180, 30);
        SignIn_btn.setBounds(100, 205, 180, 35);
        SignUp_btn.setBounds(100, 250, 180, 35);
        Error_lbl.setBounds(65, 300, 400, 30);

        Username_lbl.setFont(new Font("ANGSANA NEW", Font.BOLD, 30));
        Username_txt.setFont(new Font("ANGSANA NEW", Font.PLAIN, 25));
        Password_lbl.setFont(new Font("ANGSANA NEW", Font.BOLD, 30));
        Password_txt.setFont(new Font("ANGSANA NEW", Font.PLAIN, 25));
        SignIn_btn.setFont(new Font("ANGSANA NEW", Font.BOLD, 30));
        SignUp_btn.setFont(new Font("ANGSANA NEW", Font.BOLD, 30));
        Error_lbl.setFont(new Font("ANGSANA NEW", Font.BOLD, 25));

        p.add(Username_lbl);
        p.add(Username_txt);
        p.add(Password_lbl);
        p.add(Password_txt);
        p.add(SignIn_btn);
        p.add(SignUp_btn);
        p.add(Error_lbl);

        fr.add(p);

        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(400, 400);
        fr.setVisible(true);
        fr.setLocationRelativeTo(null);
        fr.setResizable(false);
    }

    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(SignIn_btn)) {
            Connection connect = null;
            PreparedStatement pre = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connect = DriverManager.getConnection("jdbc:mysql://localhost/RecordIncome", "root" ,"147258");
                String sql = "SELECT * FROM user WHERE user_name=? and user_pass=?";
                pre = connect.prepareStatement(sql);
                pre.setString(1, Username_txt.getText());
                pre.setString(2, Password_txt.getText());
                ResultSet rec = pre.executeQuery();
                if (rec.next()) {
                    try {
                        FileOutputStream fout;
                        DataOutputStream dout;
                        fout = new FileOutputStream("data.dat");
                        dout = new DataOutputStream(fout);
                        dout.writeInt(rec.getInt("user_id"));
                        dout.writeUTF(rec.getString("user_name"));
                        dout.close();
                        fout.close();
                    } catch (IOException ex) {
                        System.out.println(ex.toString());
                    }
                    if (rec.getFloat("user_balance") == 0) {
                        new Balance();
                        fr.dispose();
                    } else {
                        new Home();
                        fr.dispose();
                    }
                } else {
                    Error_lbl.setText("Username or Password is not correct");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } try {
                pre.close();
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (ae.getSource().equals(SignUp_btn)) {
            new Register();
            fr.dispose();
        }
    }

    @Override
    public void focusGained(FocusEvent fe) {
        if (fe.getSource().equals(Username_txt)) {
            if (Username_txt.getText().equals("Username")) {
                Username_txt.setText("");
            }
        } else if (fe.getSource().equals(Password_txt)) {
            if (Password_txt.getText().equals("Password")) {
                Password_txt.setText("");
            }
        }
    }

    @Override
    public void focusLost(FocusEvent fe) {
        if (fe.getSource().equals(Username_txt)) {
            if (Username_txt.getText().trim().equals("")) {
                Username_txt.setText("Username");
            }
        } else if (fe.getSource().equals(Password_txt)) {
            if (Password_txt.getText().trim().equals("")) {
                Password_txt.setText("Password");
            }
        }
    }
}