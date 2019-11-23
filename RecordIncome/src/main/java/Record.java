import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.*;

public class Record implements ActionListener{

    private JFrame fr;
    private JPanel p;
    private JTextField Money_txt, Cause_txt;
    private JButton Up_btn, Down_btn, Home_btn;
    private JTable Record_table;
    private JScrollPane ScrollPane;

    public Record () {

        fr = new JFrame("RecordIncome");
        p = new JPanel();
        Money_txt = new JTextField();
        Cause_txt = new JTextField();
        Up_btn = new JButton("UP");
        Down_btn = new JButton("DOWN");
        Home_btn = new JButton("Home");
        Record_table = new JTable();
        ScrollPane = new JScrollPane();

        Up_btn.addActionListener(this);
        Down_btn.addActionListener(this);
        Home_btn.addActionListener(this);

        fr.setLayout(new GridLayout(1, 1));
        p.setLayout(null);

        Money_txt.setBounds(220, 15, 150, 50);
        Cause_txt.setBounds(95, 80, 400, 50);
        Up_btn.setBounds(60, 15, 100, 50);
        Down_btn.setBounds(430, 15, 100, 50);
        Home_btn.setBounds(190, 510, 200, 30);
        ScrollPane.setBounds(20, 145, 545, 345);

        Money_txt.setHorizontalAlignment(JTextField.CENTER);
        Cause_txt.setHorizontalAlignment(JTextField.CENTER);

        ScrollPane.setViewportView(Record_table);

        DefaultTableModel model = (DefaultTableModel) Record_table.getModel();
        model.addColumn("ว/ด/ป");
        model.addColumn("เวลา");
        model.addColumn("สาเหตุการใช้จ่าย");
        model.addColumn("จำนวนที่ใช้");
        model.addColumn("ยอดที่เหลือ");

        Record_table.getTableHeader().setReorderingAllowed(false);
        Record_table.getTableHeader().setResizingAllowed(false);
        Record_table.getTableHeader().setFont(new Font("ANGSANA NEW", Font.BOLD, 20));
        Record_table.getTableHeader().setBackground(new Color(255, 255, 255));
        Record_table.setFont(new Font("ANGSANA NEW", Font.BOLD, 20));
        Record_table.setRowHeight(30);
        Record_table.getColumnModel().getColumn(0).setPreferredWidth(25);
        Record_table.getColumnModel().getColumn(1).setPreferredWidth(25);
        Record_table.getColumnModel().getColumn(2).setPreferredWidth(200);
        Record_table.getColumnModel().getColumn(3).setPreferredWidth(25);
        Record_table.getColumnModel().getColumn(4).setPreferredWidth(25);
        Record_table.setEnabled(false);

        p.add(Money_txt);
        p.add(Cause_txt);
        p.add(Up_btn);
        p.add(Down_btn);
        p.add(Home_btn);
        p.add(ScrollPane);

        fr.add(p);

        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(600, 600);
        fr.setVisible(true);
        fr.setLocationRelativeTo(null);
        fr.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(Up_btn)) {

        } else if (ae.getSource().equals(Down_btn)) {

        } else if (ae.getSource().equals(Home_btn)) {
            new Home();
            fr.dispose();
        }
    }
}
