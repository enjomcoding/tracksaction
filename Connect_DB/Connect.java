package myproject.Connect_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    // Static variables
    private static final String databasePath = "C:\\Users\\jomsa\\OneDrive\\Documents\\Database1.accdb";
    private static final String url = "jdbc:ucanaccess://" + databasePath;

    // Static method
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Establish the connection
            connection = DriverManager.getConnection(url);
            System.out.println("Connection established successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}