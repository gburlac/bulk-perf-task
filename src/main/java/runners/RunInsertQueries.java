package runners;

import connections.DBconections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RunInsertQueries {

    public static void main(String[] args) {

        DBconections dBconections = new DBconections();
        Connection conn = dBconections.activeConnection();

        try {
            String sqlInsert = "INSERT INTO sales.staffs (first_name, last_name, email, phone, active, store_id, manager_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement2 = conn.prepareStatement(sqlInsert);
            statement2.setString(1, "George");
            statement2.setString(2, "Burlacu");
            statement2.setString(3, "gtb991@gmail.com");
            statement2.setString(4, "37367542822");
            statement2.setString(5, "1");
            statement2.setString(6, "3");
            statement2.setString(7, "2");
            int rowsInserted = statement2.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}