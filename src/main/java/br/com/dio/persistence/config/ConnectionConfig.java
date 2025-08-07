package br.com.dio.persistence.config;

import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class ConnectionConfig {

    private static final Properties properties = loadProperties();

    public static Connection getConnection() throws SQLException {
        if (DatabaseConfig.isH2()) {
            return getH2Connection();
        } else {
            return getMySQLConnection();
        }
    }

    private static Connection getH2Connection() throws SQLException {
        var url = properties.getProperty("h2.url", "jdbc:h2:mem:board;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        var user = properties.getProperty("h2.username", "sa");
        var password = properties.getProperty("h2.password", "");
        var connection = DriverManager.getConnection(url, user, password);
        connection.setAutoCommit(false);
        return connection;
    }

    private static Connection getMySQLConnection() throws SQLException {
        var url = properties.getProperty("mysql.url", "jdbc:mysql://localhost/board");
        var user = properties.getProperty("mysql.username", "board");
        var password = properties.getProperty("mysql.password", "board");
        var connection = DriverManager.getConnection(url, user, password);
        connection.setAutoCommit(false);
        return connection;
    }

    private static Properties loadProperties() {
        var props = new Properties();
        try (InputStream input = ConnectionConfig.class.getClassLoader()
                .getResourceAsStream("database.properties")) {
            if (input != null) {
                props.load(input);
            }
        } catch (IOException e) {
            System.out.println("Arquivo database.properties não encontrado, usando configurações padrão");
        }
        return props;
    }

}
