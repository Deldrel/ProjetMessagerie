import java.sql.*;

public class Database {
    private static final String host = "localhost";
    private static final String port = "3306";
    private static final String user = "root";
    private static final String password = "root";
    private static final String database = "messagerie";
    private static final String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
    private static Connection connection = null;

    public static void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("\033[32mDatabase connection established\033[0m");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\033[31mDatabase connection failed\033[0m");
        }
    }

    public static void disconnect() {
        try {
            connection.close();
            System.out.println("\033[32mDatabase connection terminated\033[0m");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\033[31mDatabase disconnection failed\033[0m");
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void queryDDL(String request) {
        if (connection == null) {
            System.out.println("\033[31mDatabase connection is not established\033[0m");
            return;
        }

        Statement statement = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(request);
            System.out.println("Database updated with request: " + request);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void recreateDatabase() {
        queryDDL("DROP DATABASE IF EXISTS " + database);
        queryDDL("CREATE DATABASE IF NOT EXISTS " + database);
        queryDDL("USE " + database);
        queryDDL("CREATE TABLE IF NOT EXISTS user (id INT NOT NULL, username VARCHAR(255) NOT NULL, first_name VARCHAR(255) NOT NULL, last_name VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL, password VARCHAR(255) NOT NULL, permission INT NOT NULL, last_connection_time VARCHAR(255) NOT NULL, PRIMARY KEY (id))");
        queryDDL("CREATE TABLE IF NOT EXISTS message (id INT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, timestamp DATETIME NOT NULL, content VARCHAR(255) NOT NULL, PRIMARY KEY (id))");
        queryDDL("CREATE TABLE IF NOT EXISTS log (id INT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, timestamp DATETIME NOT NULL, type VARCHAR(255) NOT NULL, PRIMARY KEY (id))");
      }
}
