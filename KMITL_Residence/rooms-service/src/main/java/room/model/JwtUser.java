package room.model;

/**
 * Created by Adisorn on 3/4/2560.
 */
public class JwtUser {

    private String username;
    private String role;

    public JwtUser(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public JwtUser() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String toString() {
        return this.username + ", " + this.role;
    }

}
