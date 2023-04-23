import java.time.Duration;

public class User {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int permission;
    private Duration lastConnectionTime;

    public User() {
        this.id = -1;
        this.username = "";
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.password = "";
        this.permission = -1; //0 = ban; 1 = user; 2 = moderateur; 3 = admin
        this.lastConnectionTime = Duration.ZERO;
    }

    public User(int id, String username, String firstName, String lastName, String email, String password, int permission, Duration lastConnectionTime) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.permission = permission;
        this.lastConnectionTime = lastConnectionTime;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getPermission() {
        return permission;
    }

    public Duration getLastConnectionTime() {
        return lastConnectionTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public void setLastConnectionTime(Duration lastConnectionTime) {
        this.lastConnectionTime = lastConnectionTime;
    }

    @Override
    public String toString() {
        return id + "#" + username + "#"+ lastConnectionTime + "#" + permission;
    }
}
