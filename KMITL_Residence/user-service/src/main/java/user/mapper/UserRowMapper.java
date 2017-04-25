package user.mapper;

import org.springframework.jdbc.core.RowMapper;
import user.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Akkapon on 25/4/2560.
 */
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        User user = new User();

        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("hash_password"));
        user.setRole(rs.getInt("role_id"));
        user.setTitleNameEn(rs.getString("en_prename"));
        user.setFullNameEn(rs.getString("en_name"));
        user.setTitleNameTh(rs.getString("th_prename"));
        user.setFullNameTh(rs.getString("th_name"));
        user.setEmail(rs.getString("email"));

        return user;
    }
}

