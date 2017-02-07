package demo;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public User findById(Long id) {
        try {
            return this.jdbcTemplate.queryForObject("SELECT * FROM USERS WHERE id=?", new Object[]{id}, new UserRowMapper());
        }catch (Exception exception) {
            throw new UserNotFoundException(id);
        }
    }
    
    @Transactional(readOnly = true)
    public List<User> findAll(int page, int item_per_page){
        List<User> usersList;
        final String SELECT_SQL = "SELECT id, firstname, lastname FROM users LIMIT ? OFFSET ?;";

        try {
            if (item_per_page > 0) {
                int offset = item_per_page * (page - 1);
                usersList = this.jdbcTemplate.query(SELECT_SQL, new Object[]{item_per_page, offset}, new UserRowMapper());
            } else {
                throw new UserNotFoundException();
            }
        } catch (Exception ex) {
            throw new UserNotFoundException();
        }
        return usersList;
    }

    @Transactional
    public void save(User user) {
        String sql = "INSERT INTO USERS(id, firstname, lastname) VALUES (?,?,?)";
        this.jdbcTemplate.update(sql, user.getId(), user.getFirstname(), user.getLastname());
    }

    public void delete(Long id) {
        String sql = "DELETE FROM USERS WHERE id=?";
        this.jdbcTemplate.update(sql, id);
    }
}