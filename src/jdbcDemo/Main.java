package jdbcDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // JDBC URL, username and password of MySQL server
        String jdbcUrl = "jdbc:mysql://localhost:3306/jdbc";
        String username = "root";
        String password = "neoteric@123";

        // Declare the JDBC objects.
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Step 1: Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish the connection to the database
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Step 3: Create a statement
            statement = connection.createStatement();

            // Step 4: Execute SQL commands

            // Create table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS EMP (" +
                    "eno INT(3), " +
                    "ename VARCHAR(40), " +
                    "esal DOUBLE)";
            statement.executeUpdate(createTableSQL);

            // Insert data
            String insertDataSQL = "INSERT INTO EMP (eno, ename, esal) VALUES (3, 'ashri', 5000.0)";
            statement.executeUpdate(insertDataSQL);

            // Select data
            String selectDataSQL = "SELECT * FROM EMP";
            resultSet = statement.executeQuery(selectDataSQL);

            // Step 5: Process the results
            System.out.println("EMP table data:");
            while (resultSet.next()) {
                int eno = resultSet.getInt("eno");
                String ename = resultSet.getString("ename");
                double esal = resultSet.getDouble("esal");
                System.out.println("eno: " + eno + ", ename: " + ename + ", esal: " + esal);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Step 6: Close the resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
