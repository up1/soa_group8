package com.tytle.kmitlresidence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tytle.kmitlresidence.model.RoomType;

@Repository
public class RoomTypeRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public RoomTypeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(readOnly = true)
    public RoomType getRoomTypeByID(int id) {
        try {
            final String SELECT_SQL = "SELECT * FROM room_type WHERE room_type_id = ?";
            return this.jdbcTemplate.queryForObject(SELECT_SQL, new Object[]{id}, new RowMapper<RoomType>() {
                public RoomType mapRow(ResultSet resultSet, int i) throws SQLException {
                    RoomType roomType = new RoomType();
                    roomType.setRoom_type_id(resultSet.getInt("room_type_id"));
                    roomType.setName(resultSet.getString("name"));
                    roomType.setDescription(resultSet.getString("description"));
                    roomType.setMaximum_guest(resultSet.getInt("maximum_guest"));
                    roomType.setPrice(resultSet.getInt("price"));
                    return roomType;
                }
            });
        } catch (EmptyResultDataAccessException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}