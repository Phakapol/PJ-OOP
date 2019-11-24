import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;

public class Profile implements ActionListener {

    private JFrame fr;
    private JPanel p;
    private JLabel Username_lbl, Firstname_lbl, Lastname_lbl, Email_lbl;
    private JButton Home_btn;

    private String user;
    private int id;

    public Profile() {
        fr = new JFrame("RecordIncome");
        p = new JPanel();
        Username_lbl = new JLabel("Username : ");
        Firstname_lbl = new JLabel("Firstname : ");
        Lastname_lbl = new JLabel("Lastname : ");
        Email_lbl = new JLabel("Email : ");
        Home_btn = new JButton("Home");

        Home_btn.addActionListener(this);

        fr.setLayout(new GridLayout(1, 1));
        p.setLayout(null);

        Username_lbl.setBounds(90, 70, 200, 100);
        Firstname_lbl.setBounds(90, 100, 200, 100);
        Lastname_lbl.setBounds(90, 130, 200, 100);
        Email_lbl.setBounds(90, 160, 200, 100);
        Home_btn.setBounds(90, 240, 200, 30);

        p.add(Username_lbl);
        p.add(Firstname_lbl);
        p.add(Lastname_lbl);
        p.add(Email_lbl);
        p.add(Home_btn);

        fr.add(p);

        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(400, 400);
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
                Username_lbl.setText("Username : " + rec.getString("user_name"));
                Firstname_lbl.setText("Firstname : " + rec.getString("user_firstname"));
                Lastname_lbl.setText("Lastname : " + rec.getString("user_lastname"));
                Email_lbl.setText("Email : " + rec.getString("user_email"));
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
        if (ae.getSource().equals(Home_btn)) {
            new Home();
            fr.dispose();
        }
    }
}
