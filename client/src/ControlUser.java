import java.time.Duration;

/**
 * This class is the controller of the user.
 * It allows to modify the user.
 */

public class ControlUser {
    private User user;
    private VueUser vue;

    public ControlUser(User user, VueUser vue) {
        this.user = user;
        this.vue = vue;
    }

    public void setUsername(String username) {
        user.setUsername(username);
    }

    public void setFirstname(String firstname) {user.setFirstname(firstname);}

    public void setLastname(String lastname) {user.setLastname(lastname);}

    public void setEmail(String email) {user.setEmail(email);}

    public void setPassword(String password) {user.setPassword(password);}

    public void setPermission(int permission) {user.setPermission(permission);}

    public void setLastConnectionTime(Duration lastConnectionTime) {user.setLastConnectionTime(lastConnectionTime);}


}
