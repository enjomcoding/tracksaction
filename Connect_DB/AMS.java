package myproject.Connect_DB;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Base64;
import javax.swing.*;

public class AMS {
    public boolean SignUp(String fullName, String email, String password) {
        // Hash the password before storing it
        String hashedPassword = hashPassword(password);

        // Check if the email already exists in the database
        if (UsernameExist(email)) {
            JOptionPane.showMessageDialog(null, "An account with this email already exists!");
            return false; // Exit if the email exists
        }

        // SQL query to insert a new user
        String query = "INSERT INTO [User] ([full_name], [email], [password]) VALUES (?, ?, ?)";

        // Try-with-resources to manage database connection and statement
        try (Connection connect = Connect.getConnection();
            PreparedStatement pstmt = connect.prepareStatement(query)) {

            // Set values for placeholders
            pstmt.setString(1, fullName);
            pstmt.setString(2, email);
            pstmt.setString(3, hashedPassword);

            // Execute the query
            int rows = pstmt.executeUpdate();

            // Check if insertion was successful
            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Account created successfully!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Signup failed. Please try again.",
                                            "Signup Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println("Error inserting account: " + e.getMessage());
        }
        return false; // Return false if something went wrong
    }

    private boolean verifyAcc(String user, String pass) {
        String query = "SELECT [password] FROM [User] WHERE [email] = ?";
        boolean isAuthenticated = false;

        try (Connection connect = Connect.getConnection();
            PreparedStatement stmt = connect.prepareStatement(query)) {

            stmt.setString(1, user);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHashedPassword = rs.getString("password");

                // Hash the entered password and compare with the stored hash
                if (verifyPassword(pass, storedHashedPassword)) {
                    isAuthenticated = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isAuthenticated;
    }

    private boolean UsernameExist(String email) {
        String query = "SELECT COUNT(*) FROM [User] WHERE [email] = ?";
        boolean exists = false;

        try (Connection connect = Connect.getConnection();
            PreparedStatement pstmt = connect.prepareStatement(query)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                exists = true; // If count > 0, then username exists
            }
        } catch (SQLException e) {
            System.out.println("Error checking username existence: " + e.getMessage());
        }
        return exists;
    }

    // Hash the password using SHA-256
    private String hashPassword(String password) {
        try {
            // Generate a random key (randKey)
            byte[] randKey = new byte[16];
            SecureRandom sr = new SecureRandom();
            sr.nextBytes(randKey);

            // Combine the password with the randKey
            String passwordWithRandKey = password + Base64.getEncoder().encodeToString(randKey);

            // Get the MessageDigest instance for SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Apply the hash function to the password and randKey, then get the byte array
            byte[] hashedBytes = digest.digest(passwordWithRandKey.getBytes());

            // Convert the byte array into a hex string and return it
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                hexString.append(String.format("%02x", b)); // Convert each byte to its hexadecimal 
                                                            //representation
            }

            // Include the randKey with the hash (to store it in the DB)
            return hexString.toString() + ":" + Base64.getEncoder().encodeToString(randKey);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    // Verifies the password entered by the user against the stored hashed password
    private boolean verifyPassword(String enteredPassword, String hashedPass) {
        try {
            // Split the stored value into hash and randKey
            String[] parts = hashedPass.split(":");
            if (parts.length != 2) {
                return false;
            }

            String storedHashedPassword = parts[0];
            byte[] storedRandKey = Base64.getDecoder().decode(parts[1]);

            // Combine the entered password with the stored randKey
            String enteredPasswordWithRandKey = enteredPassword + Base64.getEncoder().encodeToString(storedRandKey);

            // Hash the entered password with randKey
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(enteredPasswordWithRandKey.getBytes());

            // Convert the byte array into a hex string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                hexString.append(String.format("%02x", b));
            }

            // Compare the entered hashed password with the stored one
            return storedHashedPassword.equals(hexString.toString());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error verifying password", e);
        }
    }

    // LogIn method
    public boolean LogIn(String email, String password) {
        try (Connection connect = Connect.getConnection()) {
            // Verifying user credentials
            if (verifyAcc(email, password)) {
                JOptionPane.showMessageDialog(null, "Logged in successfully!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Login failed. Please try again.", "Login Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database connection error: " + e.getMessage());
            return false;
        }
    }
}
