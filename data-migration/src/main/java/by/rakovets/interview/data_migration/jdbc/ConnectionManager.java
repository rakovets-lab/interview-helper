package by.rakovets.interview.data_migration.jdbc;

import by.rakovets.interview.data_migration.util.PropertiesFileReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private final PropertiesFileReader properties;

    public ConnectionManager() {
        this.properties = PropertiesFileReader.getInstance();
    }

    public static Connection openConnection() {
        PropertiesFileReader properties = PropertiesFileReader.getInstance();
        try {
            return DriverManager.getConnection(
                    properties.getUrlKey(),
                    properties.getUserNameKey(),
                    properties.getPasswordKey()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PropertiesFileReader getProperties() {
        return properties;
    }
}
