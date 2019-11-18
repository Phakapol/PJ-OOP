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
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class register implements ActionListener {
    private JFrame fr;
    private JPanel p1, p2, p3, p4;
    private JLabel lbl1, lbl2, lbl3;
    private JTextField txt1, txt2;
    private JButton btn1, btn2;
    String []p;
    
    public register() {
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
       if(e.getSource().equals(btn1)){
            if(txt1.getText().equals("") || txt2.getText().equals("")){
                lbl3.setText("Error");
            }else{
                Person pin = new Person(txt1.getText(), txt2.getText());
                try {
                    FileOutputStream fout = new FileOutputStream("User.dat");
                    ObjectOutputStream oout = new ObjectOutputStream(fout);
                    oout.writeObject(pin);
                    oout.close();
                    fout.close();
                    FileInputStream fin = new FileInputStream("User.dat");
                    ObjectInputStream oin = new ObjectInputStream(fin);
                    Person pout = (Person) oin.readObject();
                    System.out.println(pout.getUser());
                    System.out.println(pout.getPass());
                    oin.close();
                    fin.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
                }
                new login();
                fr.setVisible(false);
            }
        }else if(e.getSource().equals(btn2)){
            new login();
            fr.setVisible(false);
        }
    }
}
