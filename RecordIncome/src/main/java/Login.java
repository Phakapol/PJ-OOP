import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login implements ActionListener, FocusListener {

    private JFrame fr;
    private JPanel p;
    private JLabel lbl;
    private JTextField Username_txt, Password_txt;
    private JButton SignIn, SignUp;

    public Login() {
        fr = new JFrame("RecordIncome");
        p = new JPanel();
        Username_txt = new JTextField("Username");
        Password_txt = new JTextField("Password");
        lbl = new JLabel();
        SignIn = new JButton("Sign In");
        SignUp = new JButton("Sign Up");

        SignIn.addActionListener(this);
        SignUp.addActionListener(this);

        Username_txt.addFocusListener(this);
        Password_txt.addFocusListener(this);

        fr.setLayout(new GridLayout(1, 1));
        p.setLayout(null);

        Username_txt.setBounds(100, 90, 180, 30);
        Password_txt.setBounds(100, 130, 180, 30);

        SignIn.setBounds(100, 180, 180, 30);
        SignUp.setBounds(100, 220, 180, 30);

        lbl.setBounds(85, 270, 400, 30);

        p.add(Username_txt);
        p.add(Password_txt);
        p.add(SignIn);
        p.add(SignUp);
        p.add(lbl);

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

                if (rec.next()) {
                    new Home();
                    fr.dispose();
                } else {
                    lbl.setText("Username and Password not Correct");
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