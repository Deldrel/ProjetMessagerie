import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

public class MessageDAO {
    private static final Connection connection = Database.getConnection();

    public static void add(Message message) {
        try {
            int n = getNumberOfMessages();
            if (n == -1)
                return;

            String request = "INSERT INTO message VALUES (" + n + ", " + message.getUserId() + ", '" + message.getTimestamp() + "', '" + message.getContent() + "')";
            Database.queryDDL(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Message get(int id) {
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
                String content = resultSet.getString("content");

                return new Message(i, user_id, timestamp, content);
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

    public static int getNumberOfMessages() {
        if (connection == null) {
            System.out.println("\033[31mDatabase connection is not established\033[0m");
            return -1;
        }

        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM message");

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
}
