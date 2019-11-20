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

public class Register implements ActionListener {
    private JFrame fr;
    private JPanel p1, p2, p3;
    private JLabel Username, Password;
    private JTextField Username_txt, Password_txt;
    private JButton Ok, Back;
    
    public Register() {
        fr = new JFrame("RecordIncome");
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        Username_txt = new JTextField();
        Password_txt = new JTextField();
        Username = new JLabel("Username");
        Password = new JLabel("Password");
        Ok = new JButton("OK");
        Back = new JButton("Back");
        
        Ok.addActionListener(this);
        Back.addActionListener(this);
        
        fr.setLayout(new GridLayout(4,1));
        p1.setLayout(new GridLayout(1,2));
        p2.setLayout(new GridLayout(1,2));
        p3.setLayout(new FlowLayout());
        
        p1.add(Username);
        p1.add(Username_txt);
        
        p2.add(Password);
        p2.add(Password_txt);
        
        p3.add(Ok);
        p3.add(Back);
        
        fr.add(p1);
        fr.add(p2);
        fr.add(p3);
        
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(400, 200);
        fr.setVisible(true);
        fr.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(Ok)) {
            Connection connect = null;
            PreparedStatement pre = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connect = DriverManager.getConnection("jdbc:mysql://localhost/RecordIncome", "root","147258");
                
                String sql = "INSERT INTO user (user_name, user_pass) VALUES (?, ?)";
                pre = connect.prepareStatement(sql);
                
                pre.setString(1, Username_txt.getText());
                pre.setString(2, Password_txt.getText());
                
                pre.executeUpdate();
                System.out.println("Done It");
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
        } else if (ae.getSource().equals(Back)) {
            new Login();
            fr.dispose();
        }
    }
}