package org.example;

import org.example.file.MyFileUtil;

import java.io.File;
import java.io.IOException;
import java.sql.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void my_test() throws SQLException, ClassNotFoundException {
        Class.forName("org.apache.ignite.IgniteJdbcDriver");
        String url = "";
        url = "jdbc:ignite:thin://127.0.0.1:10800/public?lazy=true&distributedJoins=true&autoCloseServerCursor=true";
        url = "jdbc:ignite:thin://127.0.0.1:10800/public?lazy=true";
        url = "jdbc:ignite:thin://127.0.0.1:10800/public?lazy=true&userToken=wudafu";
        url = "jdbc:ignite:thin://127.0.0.1:10800/?userToken=wudafu";
        Connection conn = DriverManager.getConnection(url);
        //Connection conn = DriverManager.getConnection("jdbc:ignite:thin://127.0.0.1:10800/public?lazy=true;");
        //Statement stmt = conn.createStatement();

        DatabaseMetaData metaData = conn.getMetaData();
        System.out.println(metaData);

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

    public static void my_test_1() throws SQLException, ClassNotFoundException
    {
        Class.forName("org.apache.ignite.IgniteJdbcDriver");
        String url = "";
        url = "jdbc:ignite:thin://127.0.0.1:10800/public?lazy=true&userToken=dafu";
        Connection conn = DriverManager.getConnection(url);
        //Connection conn = DriverManager.getConnection("jdbc:ignite:thin://127.0.0.1:10800/public?lazy=true;");
        //Statement stmt = conn.createStatement();

        DatabaseMetaData metaData = conn.getMetaData();
        System.out.println(metaData);

        PreparedStatement stmt = conn.prepareStatement("SELECT CompanyName, ContactName from public.Customers WHERE City = ? AND ContactTitle IN ('Sales Agent', 'Sales Associate')");

        stmt.setObject(1, "London");

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

    public static void main( String[] args ) throws SQLException, ClassNotFoundException, IOException {
        //my_test();

        //MyFileUtil myFileUtil = new MyFileUtil();
        //myFileUtil.myWriter("吴大贵");

        //System.out.println(System.getProperty("user.dir"));
        //System.out.println(System.getProperty("java.class.path"));
        my_test_1();
        System.out.println( "Hello World!" );

    }
}




























