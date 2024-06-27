package jdbcDemo;

import java.sql.*;

public class Test {
        public static void main(String[] args) {
            // JDBC URL, username and password of MySQL server
            String jdbcUrl = "jdbc:mysql://localhost:3306/employ";
            String username = "root";
            String password = "neoteric@123";
         //   String bhagi = "bhagi";

            // Declare the JDBC objects.
        //    Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;


            try {
                // Step 1: Load JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Step 2: Establish the connection to the database
                //connection = DriverManager.getConnection(jdbcUrl, username, password);
                Connection connection = DriverManager.getConnection(jdbcUrl,username,password);
                PreparedStatement ps = connection.prepareStatement("CREATE DATABASE employee");
                ResultSet result = ps.executeQuery("CREATE DATABASE employee");

                // Step 3: Create a statement
            //    statement = connection.createStatement();
              //  String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS " +bhagi;
               // statement.executeUpdate(createDatabaseSQL);


                // Step 4: Execute SQL commands

                // Create table
                String createTableSQL = "CREATE TABLE IF NOT EXISTS employee (" +
                        "eno INT(3), " +
                        "ename VARCHAR(40), " +
                        "esal DOUBLE)";
                statement=connection.prepareStatement("createTableSQL");
            //    statement.executeUpdate(createTableSQL);
               // int result=statement.executeUpdate();

                // Insert data
                String insertDataSQL = "INSERT INTO employee (eno, ename, esal) VALUES (1, 'Uday', 5000.0)";
                statement.executeUpdate(insertDataSQL);

                // Select data
                String selectDataSQL = "SELECT * FROM EMP";
                resultSet = statement.executeQuery(selectDataSQL);

                // Step 5: Process the results
                System.out.println("employee table data:");
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
                  //  if (connection != null) connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }