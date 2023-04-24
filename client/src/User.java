import java.time.Duration;

public class User {
    private String password;
    private int id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private int permission;
    private Duration lastLogin;
    private boolean banned;
    private Duration lastConnectionTime;
    private int status;

    public User() {
        this.id = -1;
        this.username = "";
        this.firstname = "";
        this.lastname = "";
        this.email = "";
        this.password = "";
        this.permission = -1;
        this.lastConnectionTime = Duration.ZERO;
        this.status = -1;
    }

    public User(int id, String username, String firstName, String lastName, String email, String password, int permission, Duration lastConnectionTime, int status) {
        this.id = id;
        this.username = username;
        this.firstname = firstName;
        this.lastname = lastName;
        this.email = email;
        this.password = password;
        this.permission = permission;
        this.lastConnectionTime = lastConnectionTime;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
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
    public int getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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
    public void setStatus(int status) {
        this.status = status;
    }

    public String newAccountToString(){
        return "newAccount "+ this.getUsername() +" "+ this.getFirstname()+" "+ this.getLastname()+" "+this.getEmail()+" "+this.getPassword()+" "+this.getPermission()+" "+this.getLastConnectionTime()+" "+this.getStatus();
    }

}
