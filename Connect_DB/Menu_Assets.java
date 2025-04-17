/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myproject.Connect_DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author jomsa
 */
public class Menu_Assets {
    public int getUID(String email) {
        // Get user_id based on email
        String query = "SELECT [user_id] FROM [User] WHERE [email] = ?";
        int userId = -1; // Default value when not found

        try (Connection connect = Connect.getConnection(); 
            PreparedStatement pstmt = connect.prepareStatement(query)) {

            // Set the email parameter in the query
            pstmt.setString(1, email.trim()); // Trim to avoid leading/trailing spaces

            // Execute the query and get the ResultSet
            try (ResultSet rs = pstmt.executeQuery()) {
                // Check if the ResultSet has a matching user
                if (rs.next()) {
                    userId = rs.getInt("user_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        }
        return userId; // Return -1 if not found
    }

    public String getName(int userId) {
        // Get the first name based on user_id
        String query = "SELECT [full_name] FROM [User] WHERE [user_id] = ?";
        String firstName = null; // Default value if name not found

        try (Connection connect = Connect.getConnection(); 
            PreparedStatement pstmt = connect.prepareStatement(query)) {

            // Set the user_id parameter in the query
            pstmt.setInt(1, userId);

            // Execute the query and get the ResultSet
            try (ResultSet rs = pstmt.executeQuery()) {
                // Check if the ResultSet has a matching user
                if (rs.next()) {
                    String fullName = rs.getString("full_name");

                    // Handle null or empty full name
                    if (fullName != null && !fullName.trim().isEmpty()) {
                        // Split the full name by space and get the first part
                        if (fullName.contains(" ")) {
                            firstName = fullName.split(" ")[0]; // First name
                        } else {
                            firstName = fullName; // Single word name
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        }
        return firstName; // Return null if not found or name is invalid
    }
    
    public String getFullName(int userId) {
        // Get the full name based on user_id
        String query = "SELECT [full_name] FROM [User] WHERE [user_id] = ?";
        String fullName = null; // Default value if name not found

        try (Connection connect = Connect.getConnection(); 
            PreparedStatement pstmt = connect.prepareStatement(query)) {

            // Set the user_id parameter in the query
            pstmt.setInt(1, userId);

            // Execute the query and get the ResultSet
            try (ResultSet rs = pstmt.executeQuery()) {
                // Check if the ResultSet has a matching user
                if (rs.next()) {
                    fullName = rs.getString("full_name");

                    // Handle null or empty full name
                    if (fullName == null || fullName.trim().isEmpty()) {
                        fullName = null; // Return null if the name is invalid
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        }
        return fullName; // Return the full name or null if not found or invalid
    }
    
    public String getUserName(int userId) {
        // Get the email/username based on user_id
        String query = "SELECT [email] FROM [User] WHERE [user_id] = ?";
        String email = null; // Default value if name not found

        try (Connection connect = Connect.getConnection(); 
            PreparedStatement pstmt = connect.prepareStatement(query)) {

            // Set the user_id parameter in the query
            pstmt.setInt(1, userId);

            // Execute the query and get the ResultSet
            try (ResultSet rs = pstmt.executeQuery()) {
                // Check if the ResultSet has a matching user
                if (rs.next()) {
                    email = rs.getString("email");

                    // Handle null or empty fusername
                    if (email == null || email.trim().isEmpty()) {
                        email = null; // Return null if the username is invalid
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        }
        return email; // Return the username or null if not found or invalid
    }
}
