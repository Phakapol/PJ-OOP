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

public class Home {

    private JFrame fr;
    private JPanel p1, p2;
    private JButton Record, History, Balance, Wishlist;

    public Home() {
        fr = new JFrame("RecordIncome");
        p1 = new JPanel();
        p2 = new JPanel();
        Record = new JButton("Record");
        History = new JButton("History");
        Balance = new JButton("Balance");
        Wishlist = new JButton("Wishlist");

        fr.setLayout(new GridLayout(4, 1));
        p1.setLayout(new GridLayout(1, 2));
        p2.setLayout(new GridLayout(1, 2));

        p1.add(Record);
        p1.add(History);

        p2.add(Balance);
        p2.add(Wishlist);

        fr.add(p1);
        fr.add(p2);

        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(500, 500);
        fr.setVisible(true);
        fr.setLocationRelativeTo(null);
    }
}
