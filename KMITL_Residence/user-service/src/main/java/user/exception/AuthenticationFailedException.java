package user.exception;

/**
 * Created by Adisorn on 2/4/2560.
 */

public class AuthenticationFailedException extends RuntimeException {

    public AuthenticationFailedException() {
        super("Username or Password is incorrect");
    }

}
