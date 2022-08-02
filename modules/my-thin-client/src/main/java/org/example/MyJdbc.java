package org.example;

import java.sql.*;

public class MyJdbc {
    public static void my_test() throws SQLException, ClassNotFoundException {
        Class.forName("org.apache.ignite.IgniteJdbcDriver");
        Connection conn = DriverManager.getConnection("jdbc:ignite:thin://127.0.0.1:10800/public?lazy=true;userToken=wudafu");
        //Statement stmt = conn.createStatement();

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Categories where CategoryID = ?");

        stmt.setObject(1, 5);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String name = rs.getString(1);
            String desction = rs.getString(2);
            System.out.println(name + " " + desction);
        }
        rs.close();
        stmt.close();
        conn.close();
    }
}
