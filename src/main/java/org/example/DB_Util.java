package org.example;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
/**
 * Класс для получения соединения с бд
 * Загружает настройки из файла application.properties при инициализации класса
 */
public class DB_Util {
    private static Connection connection;

    static {
        try {
            Properties properties = new Properties();
            InputStream input = DB_Util.class.getClassLoader().getResourceAsStream("application.properties");
            properties.load(input);

            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");

            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
