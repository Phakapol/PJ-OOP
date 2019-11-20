/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author acer
 */
import java.sql.*;

public class TestConnection {
    public static void main(String[] args) {
        Connection connect = null;
        Statement s = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/myDB", "root","147258");
            s = connect.createStatement();
            String sql = "insert into employee (emp_user,emp_pass,emp_level,emp_salary) values ('Sara','3333','3',34000)";
            int n = s.executeUpdate(sql);
            System.out.println("Done" + n);
            
            sql = "select * from employee";
            ResultSet rec = s.executeQuery(sql);
            while((rec!=null) && (rec.next())){
                System.out.print(rec.getString("emp_id"));
                System.out.print(rec.getString("emp_user"));
                System.out.println(rec.getString("emp_salary"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } try {
            if (connect != null) {
                s.close();
                connect.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}