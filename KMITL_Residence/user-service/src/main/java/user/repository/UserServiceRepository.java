package user.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import user.model.*;

import java.util.List;

/**
 * Created by Adisorn on 2/4/2560.
 */
public class UserServiceRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public UserServiceRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Transactional
    public void createUser(User user) {

    }

    @Transactional
    public void updateUser(User user) {

    }

    @Transactional
    public void deleteUser(String username) {

    }

    @Transactional(readOnly = true)
    public UserInformation getUser(String username) {
        UserInformation user = null;
        return user;
    }

    @Transactional(readOnly = true)
    public List<UserInformation> getUsers() {
        List<UserInformation> users = null;
        return users;
    }

    @Transactional
    public Token login(UserLogin login) {
        Token token = null;
        return token;
    }

    @Transactional
    public ResultMessage validateToken(String token) {
        ResultMessage message = null;
        return message;
    }


}
