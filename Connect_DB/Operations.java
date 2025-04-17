package myproject.Connect_DB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;

public class Operations {

    public void addTrans(int user_id, String description, String type, String category, int amount, Date transact_date) {
        String query = "INSERT INTO [Transaction] (user_id, description, type, category, amount, transact_date) VALUES (?, ?, ?, ?, ?, ?)";

        // Check if any required fields are null or invalid
        if (description == null || type == null || category == null || transact_date == null) {
            JOptionPane.showMessageDialog(null, "Please fill all required fields.");
            return;
        }

        // Adjust the balance based on transaction type
        int currBal = getCurrBal(user_id);

        try (Connection connect = Connect.getConnection();
            PreparedStatement pstmt = connect.prepareStatement(query)) {

            // Set values for placeholders in the query
            pstmt.setInt(1, user_id);
            pstmt.setString(2, description);
            pstmt.setString(3, type);
            pstmt.setString(4, category);
            pstmt.setInt(5, amount);
            pstmt.setDate(6, transact_date);

            // Execute the query to add the transaction
            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Transaction added successfully!");

                // Adjust the balance based on the transaction type
                if ("Expenses".equalsIgnoreCase(type)) {
                    subBal(user_id, amount, currBal);
                } else if ("Income".equalsIgnoreCase(type)) {
                    addBal(user_id, amount, currBal);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid transaction type.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add the transaction.");
            }
        } catch (SQLException e) {
            // Improved error handling
            System.out.println("Error inserting transaction: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while adding the transaction.");
        }
    }



    private void addBal(int uid, int amount, int currBal){
        currBal = getCurrBal(uid);
        
        int newBal = currBal + amount;
        //query to update the balance
        String updateQuery = "UPDATE [User] SET balance = ? WHERE user_id = ?";
        try (Connection connect = Connect.getConnection();
            PreparedStatement pstmt = connect.prepareStatement(updateQuery)) {

            // Set the parameters for the query
            pstmt.setInt(1, newBal);
            pstmt.setInt(2, uid);

            // Execute the update
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Balance updated successfully.");
            } else {
                System.out.println("Failed to update balance.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        }
    }

    private void subBal(int uid, int amount, int currBal){
        currBal = getCurrBal(uid);
        if (currBal == -1) {
        System.out.println("User not found.");
        return; // Exit if the user doesn't exist
        }
        
        int newBal = currBal - amount;
        //query to update the balance
        String updateQuery = "UPDATE [User] SET balance = ? WHERE user_id = ?";
        try (Connection connect = Connect.getConnection();
            PreparedStatement pstmt = connect.prepareStatement(updateQuery)) {

            // Set the parameters for the query
            pstmt.setInt(1, newBal);
            pstmt.setInt(2, uid);

            // Execute the update
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Balance updated successfully.");
            } else {
                System.out.println("Failed to update balance.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        }
    }


    public int getCurrBal(int uid){
        String query = "SELECT balance FROM [User] WHERE user_id = ?";
        int currBal = -1;
        try (Connection connect = Connect.getConnection(); 
            PreparedStatement pstmt = connect.prepareStatement(query)) {

            // Set the uid parameter in the query
            pstmt.setInt(1, uid);

            // Execute the query and get the ResultSet
            try (ResultSet rs = pstmt.executeQuery()) {
                // Check if the ResultSet has a matching user
                if (rs.next()) {
                    currBal = rs.getInt("balance");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        }
       return currBal; // Return -1 if not found
    }


    public void searchTrans(JTable searchTable, int uid, String keyword, String filter) {
        String sqlQuery = buildSqlQuery(keyword, filter);  // Build the query based on keyword and filter

        try (Connection con = Connect.getConnection();
            PreparedStatement pst = con.prepareStatement(sqlQuery)) {

            // Set the user_id parameter
            pst.setInt(1, uid);

            // Set the keyword parameters if a keyword is provided
            if (keyword != null && !keyword.isEmpty()) {
                String likeKeyword = "%" + keyword + "%";
                pst.setString(2, likeKeyword);
                pst.setString(3, likeKeyword);
                pst.setString(4, likeKeyword);
                pst.setString(5, likeKeyword);
                pst.setString(6, likeKeyword);
            }

            // Execute the query
            try (ResultSet rs = pst.executeQuery()) {
                populateTable(rs, searchTable);  // Populate the table with the result set
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error searching transactions: " + e.getMessage());
        }
    }

    public void loadAllData(JTable searchTable, int uid) { 
        // Shows all the transactions for the given user ID
        String sqlQuery = "SELECT transact_id, type, description, amount, transact_date " +
                            "FROM [Transaction] " +
                            "WHERE user_id = ?;";

        try (Connection con = Connect.getConnection();
            PreparedStatement pst = con.prepareStatement(sqlQuery)) {

            // Set the user_id parameter in the query
            pst.setInt(1, uid);

            try (ResultSet rs = pst.executeQuery()) {
                DefaultTableModel model = (DefaultTableModel) searchTable.getModel();
                model.setRowCount(0); // Clear existing rows

                // Populate the table with data from the ResultSet
                while (rs.next()) {
                    model.addRow(new Object[] {
                        rs.getInt("transact_id"),       // Fetch and add transaction ID
                        rs.getString("type"),           // Fetch and add transaction type
                        rs.getString("description"),    // Fetch and add description
                        rs.getInt("amount"),            // Fetch and add amount
                        rs.getDate("transact_date")     // Fetch and add transaction date
                    });
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, "Error loading transaction data: " + e.getMessage());
        }
    }

    private String buildSqlQuery(String keyword, String filter) {
        StringBuilder sqlQuery = new StringBuilder(
            "SELECT [transact_id], [type], [description], [amount], [transact_date] FROM [Transaction] WHERE [user_id] = ? "
        );

        // Add keyword filter if provided
        if (keyword != null && !keyword.isEmpty()) {
            sqlQuery.append("AND ([transact_id] LIKE ? OR [type] LIKE ? OR [description] LIKE ? OR [amount] LIKE ? OR [transact_date] LIKE ?) ");
        }

        // Add ORDER BY clause based on the filter
        switch (filter) {
            case "Latest":
                sqlQuery.append("ORDER BY [transact_id] DESC "); // Most recent first
                break;
            case "Oldest":
                sqlQuery.append("ORDER BY [transact_id] ASC "); // Oldest first
                break;
            case "Amount":
                sqlQuery.append("ORDER BY [amount] DESC "); // Highest amount first
                break;
            default:
                // No specific order
                break;
        }

        return sqlQuery.toString();
    }

    private void populateTable(ResultSet rs, JTable searchTable) throws SQLException {
        DefaultTableModel model = (DefaultTableModel) searchTable.getModel();
        model.setRowCount(0); // Clear existing rows

        while (rs.next()) {
            model.addRow(new Object[] {
                rs.getString("transact_id"),
                rs.getString("type"),
                rs.getString("description"),
                rs.getBigDecimal("amount"),
                rs.getString("transact_date")
            });
        }
    }

    public void updateTransactionData(int transact_id, int user_id, String description, String newType, String category, int newAmount, Date transact_date) {
        String query = "UPDATE [Transaction] SET " +
                        "description = ?, type = ?, category = ?, amount = ?, transact_date = ? " +
                        "WHERE transact_id = ? AND user_id = ?";

        try (Connection connect = Connect.getConnection();
            PreparedStatement pstmt = connect.prepareStatement(query)) {

            // Set the values in the PreparedStatement
            pstmt.setString(1, description); // description
            pstmt.setString(2, newType); // new type
            pstmt.setString(3, category); // category
            pstmt.setInt(4, newAmount); // new amount

            // Handle transact_date
            if (transact_date instanceof java.sql.Date) {
                pstmt.setDate(5, (java.sql.Date) transact_date); // transact_date
            } else if (transact_date instanceof java.util.Date) {
                pstmt.setDate(5, new java.sql.Date(((java.util.Date) transact_date).getTime())); // Convert to java.sql.Date
            }

            pstmt.setInt(6, transact_id); // transact_id
            pstmt.setInt(7, user_id);     // user_id

            // Execute the update
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Transaction updated successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "No transaction found for the specified transact_id and user_id.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating transaction: " + e.getMessage());
        }
    }

    
    public Object[] getTransactionData(int transact_id, int user_id) {
        String query = "SELECT description, type, category, amount, transact_date " +
                        "FROM [Transaction] WHERE transact_id = ? AND user_id = ?";
        Object[] data = new Object[5]; // Array to store the retrieved data

        try (Connection connect = Connect.getConnection();
            PreparedStatement pstmt = connect.prepareStatement(query)) {

            // Set the parameters for the query
            pstmt.setInt(1, transact_id);
            pstmt.setInt(2, user_id);

            // Execute the query and retrieve the data
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    data[0] = rs.getString("description");    // description
                    data[1] = rs.getString("type");           // type
                    data[2] = rs.getString("category");       // category
                    data[3] = rs.getBigDecimal("amount");     // amount
                    data[4] = rs.getDate("transact_date");    // transact_date
                } else {
                    // Handle case where no data is found
                    return new Object[5]; // Return an empty array if no data found
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error retrieving transaction data: " + e.getMessage());
            return null; // Consider throwing an exception or returning an empty array instead
        }

        return data;
    }


    public void deleteTrans(int transact_id, int uid) {
        String query = "DELETE FROM [Transaction] WHERE transact_id = ? AND user_id = ?";

        try (Connection connect = Connect.getConnection();
            PreparedStatement pstmt = connect.prepareStatement(query)) {

            // Set the parameters for the query
            pstmt.setInt(1, transact_id);
            pstmt.setInt(2, uid);

            // Execute the delete operation
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Transaction deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "No transaction found for the specified transact_id and user_id.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting transaction: " + e.getMessage());
        }
    }


    
    public Object[] getRecentTrans(int uid) {
        // Object array to store the two most recent transactions
        Object[] recData = new Object[2];

        // SQL query to fetch 2 most recent transactions for a user based on transact_id
        String sql = "SELECT category, amount, transact_date FROM Transaction " +
                     "WHERE user_id = ? ORDER BY transact_id DESC LIMIT 2";  // Order by transact_id

        try (Connection conn = Connect.getConnection();  // Assuming Connect.getConnection() provides DB connection
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the user ID parameter
            stmt.setInt(1, uid);

            // Execute the query
            try (ResultSet rs = stmt.executeQuery()) {
                int index = 0;

                // Iterate over the results and populate the recData array
                while (rs.next() && index < 2) {
                    // Fetch transaction data
                    String category = rs.getString("category");
                    BigDecimal amount = rs.getBigDecimal("amount");
                    String transactDate = rs.getString("transact_date");

                    // Directly add the transaction details to the array
                    recData[index] = new Object[] { transactDate, amount, category };
                    index++;
                }

                // If there are fewer than 2 transactions, set the remaining slots to "No transaction available"
                while (index < 2) {
                    recData[index] = new Object[] { "No transaction available", BigDecimal.ZERO, "N/A" };
                    index++;
                }

            } catch (SQLException e) {
                e.printStackTrace();
                // Handle SQL exception (e.g., log it, show a message to the user)
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle connection issues (e.g., log it, show a message to the user)
        }

        // Return the populated recData array
        return recData;
    }
}
