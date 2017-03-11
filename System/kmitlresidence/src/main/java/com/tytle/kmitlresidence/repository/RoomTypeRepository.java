package com.tytle.kmitlresidence.repository;

import java.util.List;

import com.tytle.kmitlresidence.model.rowmapper.RoomTypeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tytle.kmitlresidence.model.RoomType;

@Repository
public class RoomTypeRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoomTypeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // ----------------------------- Retrieve All Room Types -----------------------------
    @Transactional(readOnly = true)
    public List<RoomType> getRoomTypes() {
        try {
            final String SELECT_SQL = "SELECT * FROM room_type";
            return this.jdbcTemplate.query(SELECT_SQL, new RoomTypeRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // ----------------------------- Retrieve Room Type -----------------------------
    @Transactional(readOnly = true)
    public RoomType getRoomTypeByID(int id) {
        try {
            final String SELECT_SQL = "SELECT * FROM room_type WHERE room_type_id = ?";
            return this.jdbcTemplate.queryForObject(SELECT_SQL, new Object[]{id}, new RoomTypeRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}