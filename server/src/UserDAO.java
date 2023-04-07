import java.sql.*;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public void add(User user) {
        String request = "INSERT INTO user (id, name) VALUES (" + user.getId() + ", '" + user.getName() + "')";
        Database.queryDDL(request);
    }

    public User get(int id) {
        if (connection == null) {
            System.out.println("\033[31mDatabase connection is not established\033[0m");
            return null;
        }

        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE id = " + id);

            if (resultSet.next()) {
                int i = resultSet.getInt("id");
                String name = resultSet.getString("name");
                return new User(i, name);
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
}
