import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.Duration;
import java.util.ArrayList;

public class UserDAO {
    private static final Connection connection = Database.getConnection();

    public static void add(User user) {
        try {
            int n = getNumberOfUsers();
            if (n == -1)
                return;

            String request = "INSERT INTO user (id, username, first_name, last_name, email, password, permission, last_connection_time) VALUES ('" + n + "', '" + user.getUsername() + "', '" + user.getFirstName() + "', '" + user.getLastName() + "', '" + user.getEmail() + "', '" + sha1(user.getPassword()) + "', " + user.getPermission() + ", '" + user.getLastConnectionTime().toString() + "')";
            Database.queryDDL(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> void modifyUserField(int id, String columnLabel, T value) {
        try {
            if (columnLabel.equals("id"))
                throw new Exception("You can't modify the id of a user");
            if (columnLabel.equals("password"))
                value = (T) sha1((String) value);

            String request = "UPDATE user SET " + columnLabel + " = '" + value + "' WHERE id = " + id;
            Database.queryDDL(request);
        } catch (Exception e) {
            System.out.println("\033[31m" + e.getMessage() + "\033[0m");
        }
    }

    public static <T> User get(T value, String columnLabel) {
        if (connection == null) {
            System.out.println("\033[31mDatabase connection is not established\033[0m");
            return null;
        }

        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE " + columnLabel + " = '" + value + "'");

            if (resultSet.next()) {
                int i = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                int permission = resultSet.getInt("permission");
                Duration lastConnectionTime = Duration.parse(resultSet.getString("last_connection_time"));

                return new User(i, username, firstName, lastName, email, password, permission, lastConnectionTime);
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

    public static int getNumberOfUsers() {
        if (connection == null) {
            System.out.println("\033[31mDatabase connection is not established\033[0m");
            return -1;
        }

        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM user");

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


    public static String getAllUsers() {
        if (connection == null) {
            System.out.println("\033[31mDatabase connection is not established\033[0m");
            return null;
        }

        Statement statement = null;
        ArrayList<User> users = new ArrayList<User>();

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");

            while (resultSet.next()) {
                int i = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                int permission = resultSet.getInt("permission");
                Duration lastConnectionTime = Duration.parse(resultSet.getString("last_connection_time"));

                users.add(new User(i, username, firstName, lastName, email, password, permission, lastConnectionTime));
            }
            StringBuilder result = new StringBuilder();
            for (int i = users.size() - 1; i >= 0; i--) {
                result.append(users.get(i).toString()).append("&");
            }
            return result.toString();

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

    public static String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (byte b : result) {
            sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
