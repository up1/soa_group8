package user.mapper;

import org.springframework.jdbc.core.RowMapper;
import user.model.UserInformation;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Adisorn on 2/4/2560.
 */
public class UserInformationRowMapper implements RowMapper<UserInformation> {


    @Override
    public UserInformation mapRow(ResultSet rs, int i) throws SQLException {

        UserInformation userInfo = new UserInformation();

        userInfo.setUsername(rs.getString("username"));
        userInfo.setHashPassword(rs.getString("hash_password"));
        userInfo.setRole(rs.getString("en_role"));
        userInfo.setFullNameTh(rs.getString("th_prename") + rs.getString("th_name"));
        userInfo.setFullNameEn(rs.getString("en_prename") + rs.getString("en_name"));
        userInfo.setEmail(rs.getString("email"));

        return userInfo;
    }
}
