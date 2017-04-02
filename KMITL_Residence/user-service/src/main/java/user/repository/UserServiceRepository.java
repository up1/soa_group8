package user.repository;

import org.h2.util.Utils;
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

        String sql_insert_account = "insert into user_account (username, hash_password, role_id, status) " +
                "values(?, ?, ?, 'Active');";
        String sql_insert_staff = "insert into staff (th_prename, th_name, en_prename, en_name, email, username) " +
                "values(?, ?, ?, ?, ?, ?);";

        jdbc.update(sql_insert_account,
                user.getUsername(),
                Utils.hashCode(user.getPassword()),
                user.getRole());
        jdbc.update(sql_insert_staff,
                user.getTitleNameTh(),
                user.getFullNameTh(),
                user.getTitleNameEn(),
                user.getFullNameEn(),
                user.getEmail(),
                user.getUsername());

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

        String sql = "select a.username, r.en_role, s.th_prename, s.th_name, s.en_prename, s.en_name, s.email " +
                "from user_account a " +
                "join staff s " +
                "on a.username = s.username " +
                "join user_role r " +
                "on r.role_id = a.role_id" +
                "where a.username = " + username + ";";

        try {

        }catch (Exception ex) {
            ex.printStackTrace();
        }

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
