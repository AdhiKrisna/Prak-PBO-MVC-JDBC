package connector;

import java.sql.*;

public class Connector {
    String dbUrl = "jdbc:mysql://localhost:3306/biodata_db";
    String dbUsername = "root";
    String dbPassword = "";
    String dbDriver = "com.mysql.cj.jdbc.Driver";

    public Connection conn;
    // Statement statement;
    PreparedStatement preparedStatement;

    public Connector() {
        try {
            Class.forName(dbDriver);
            conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            if (conn != null) {
                System.out.println("Connection to database successfully!");
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
