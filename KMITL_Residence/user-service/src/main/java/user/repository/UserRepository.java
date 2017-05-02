package user.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import user.exception.AuthenticationFailedException;
import user.exception.InvalidDataException;
import user.exception.InvalidTokenException;
import user.exception.UserNotFoundException;
import user.jwt.JwtService;
import user.mapper.UserRowMapper;
import user.model.*;
import user.utils.UserUtils;

import java.util.List;

/**
 * Created by Adisorn on 2/4/2560.
 */
@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private JwtService jwtService;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Transactional
    public void createUser(User user) {

        String sql_insert_account = "insert into user_account (username, hash_password, role_id, status) " +
                "values(?, ?, ?, 'Active');";
        String sql_insert_staff = "insert into staff (th_prename, th_name, en_prename, en_name, email, username) " +
                "values(?, ?, ?, ?, ?, ?);";

        if(UserUtils.checkUsername(user.getUsername())) {
            try {
                jdbc.update(sql_insert_account,
                        user.getUsername(),
                        UserUtils.hash(user.getPassword()),
                        user.getRole());
                jdbc.update(sql_insert_staff,
                        user.getTitleNameTh(),
                        user.getFullNameTh(),
                        user.getTitleNameEn(),
                        user.getFullNameEn(),
                        user.getEmail(),
                        user.getUsername());
            } catch (DuplicateKeyException ex) {
                throw new InvalidDataException("Username has already been taken.");
            }
        } else {
            throw new InvalidDataException("Username can be (ENG) alphabet or number.");
        }

    }

    @Transactional
    public void updateUser(String username, User user) {
        if(user.getUsername().equals(username)) {
            String sql_update_user = "UPDATE staff, user_account " +
                    "SET staff.th_prename = ?," +
                    "staff.th_name = ?," +
                    "staff.en_prename = ?," +
                    "staff.en_name = ?," +
                    "staff.email = ?," +
                    "user_account.hash_password = ?," +
                    "user_account.role_id = ? " +
                    "WHERE staff.username = ?";
            jdbc.update(sql_update_user,
                    user.getTitleNameTh(),
                    user.getFullNameTh(),
                    user.getTitleNameEn(),
                    user.getFullNameEn(),
                    user.getEmail(),
                    UserUtils.hash(user.getPassword()),
                    user.getRole(),
                    username);
        } else {
            throw new InvalidDataException("Not allowed to edit username.");
        }
    }

    @Transactional
    public void deleteUser(String username) {
        String sql = "delete from user_account where username = ?;";
        String sql2 = "delete from staff where username = ?;";

        try {
            jdbc.update(sql, username);
            jdbc.update(sql2, username);
        } catch (Exception ex) {
            throw new UserNotFoundException(username);
        }
    }

    @Transactional(readOnly = true)
    public User getUser(String username) {
        User user;

        String sql = "select a.username, a.hash_password, a.role_id, s.th_prename, s.th_name, s.en_prename, s.en_name, s.email " +
                "from user_account a " +
                "join staff s " +
                "on a.username = s.username " +
                "join user_role r " +
                "on r.role_id = a.role_id " +
                "where a.username = ?;";

        try {
            user = jdbc.queryForObject(sql,
                    new Object[] {username},
                    new UserRowMapper());
        }catch (Exception ex) {
            throw new UserNotFoundException(username);
        }

        return user;
    }

    @Transactional(readOnly = true)
    public List<User> getUsers(int page, int item_per_page) {
        List<User> users;
        final String sql_get_all_user = "SELECT a.username, a.hash_password, a.role_id, s.th_prename, s.th_name, s.en_prename," +
                " s.en_name, s.email " +
                "FROM user_account a " +
                "JOIN staff s " +
                "ON a.username = s.username " +
                "JOIN user_role r " +
                "ON r.role_id = a.role_id " +
                "LIMIT ? " +
                "OFFSET ?;";

        try {
            if (item_per_page > 0) {
                int offset = item_per_page * (page - 1);
                users = this.jdbc.query(sql_get_all_user, new Object[]{item_per_page, offset}, new UserRowMapper());
            } else {
                throw new UserNotFoundException();
            }
        } catch(Exception ex) {
            throw new UserNotFoundException();
        }
        return users;
    }

    @Transactional
    public String authenticate(UserLogin login) {
        String token = "";
        User user = getUser(login.getUsername());
        if(user != null) {
            if(user.getPassword().equals(UserUtils.hash(login.getPassword()))) {
                JwtUser jwtUser = new JwtUser(user.getUsername(), UserUtils.getRole(user.getRole()));
                token = jwtService.getToken(jwtUser);
            }
            else {
                throw new AuthenticationFailedException();
            }
        }
        return token;
    }

    @Transactional
    public JwtUser validateToken(String token) {
        JwtUser jwtUser = null;
        try {
            jwtUser = jwtService.getUser(token);
        } catch (Exception ex) {
            throw new InvalidTokenException(token);
        }
        return jwtUser;
    }

}