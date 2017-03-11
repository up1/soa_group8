package com.tytle.kmitlresidence.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tytle.kmitlresidence.model.RoomType;

public class RoomTypeRowMapper implements RowMapper<RoomType> {

    public RoomType mapRow(ResultSet resultSet, int i) throws SQLException {
        RoomType roomType = new RoomType();
        roomType.setRoom_type_id(resultSet.getInt("room_type_id"));
        roomType.setName(resultSet.getString("name"));
        roomType.setDescription(resultSet.getString("description"));
        roomType.setMaximum_guest(resultSet.getInt("maximum_guest"));
        roomType.setPrice(resultSet.getInt("price"));
        return roomType;
    }
}
