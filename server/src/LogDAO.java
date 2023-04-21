import java.sql.*;

public class LogDAO {
    private static final Connection connection = Database.getConnection();

    public static void add(Log log) {
        try {
            int n = getNumberOfLogs();
            if (n == -1)
                return;

            String request = "INSERT INTO log (id, user_id, timestamp, type) VALUES (" + n + ", " + log.getUserId() + ", '" + log.getTimestamp() + "', '" + log.getType() + "')";
            Database.queryDDL(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Log get(int id) {
        if (connection == null) {
            System.out.println("\033[31mDatabase connection is not established\033[0m");
            return null;
        }

        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM log WHERE id = " + id);

            if (resultSet.next()) {
                int i = resultSet.getInt("id");
                int user_id = resultSet.getInt("user_id");
                Timestamp timestamp = resultSet.getTimestamp("timestamp");
                String type = resultSet.getString("type");

                return new Log(i, user_id, timestamp, type);
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static int getNumberOfLogs() {
        if (connection == null) {
            System.out.println("\033[31mDatabase connection is not established\033[0m");
            return -1;
        }

        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM log");

            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return -1;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void printAllLogs() {
        if (connection == null) {
            System.out.println("\033[31mDatabase connection is not established\033[0m");
            return;
        }

        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM log");
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                System.out.print(resultSetMetaData.getColumnName(i) + "\t");
            }
            System.out.println();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
            }
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
}
