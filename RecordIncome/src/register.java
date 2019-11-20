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
    private JPanel p1, p2, p3, p4;
    private JLabel lbl1, lbl2, lbl3;
    private JTextField txt1, txt2;
    private JButton btn1, btn2;
    
    public Register() {
        fr = new JFrame("Login");
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        txt1 = new JTextField();
        txt2 = new JTextField();
        lbl1 = new JLabel("Username");
        lbl2 = new JLabel("Password");
        lbl3 = new JLabel("...");
        btn1 = new JButton("OK");
        btn2 = new JButton("Back");
        
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        
        fr.setLayout(new GridLayout(4,1));
        p1.setLayout(new GridLayout(1,2));
        p2.setLayout(new GridLayout(1,2));
        p3.setLayout(new FlowLayout());
        
        p1.add(lbl1);
        p1.add(txt1);
        
        p2.add(lbl2);
        p2.add(txt2);
        
        p3.add(btn1);
        p3.add(btn2);
        
        p4.add(lbl3);
        
        fr.add(p1);
        fr.add(p2);
        fr.add(p3);
        fr.add(p4);
        
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(400, 200);
        fr.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}