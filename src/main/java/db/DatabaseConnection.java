package db;

import models.Contact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {

    public void contactDatabaseRecorder(String id, Contact contact) throws SQLException {
        String url = "";
        String username = "root";
        String password = "";

        Connection connection = DriverManager.getConnection(url, username, password);

        String insertQuery = "INSERT INTO contacts(id, name, lastName, email, phone, address, description)"
                + "VALUES(?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setString(1, id);
        preparedStatement.setString(2, contact.getName());
        preparedStatement.setString(3, contact.getLastName());
        preparedStatement.setString(4, contact.getEmail());
        preparedStatement.setString(5, contact.getPhone());
        preparedStatement.setString(6, contact.getAddress());
        preparedStatement.setString(7, contact.getDescription());

        int rows = preparedStatement.executeUpdate();

        if (rows > 0) {
            System.out.println("Insert successful");
        } else {
            System.out.println("Insert is not successful");
        }

        preparedStatement.close();
        connection.close();
    }

}
