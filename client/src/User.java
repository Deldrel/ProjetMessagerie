import java.time.Duration;

public class User {
    private int id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private int permission;
    private Duration lastLogin;
    private boolean banned;
    private enum state {online, offline};
}
