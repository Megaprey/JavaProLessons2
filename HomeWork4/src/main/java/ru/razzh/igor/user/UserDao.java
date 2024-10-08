package ru.razzh.igor.user;

import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Repository
public class UserDao {
    JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createUserTable() {
        jdbcTemplate.execute("CREATE TABLE users (\n" +
                "  id SERIAL PRIMARY KEY, \n" +
                "  username VARCHAR (50) UNIQUE NOT NULL);");
    }

    public void insertUser(String username) {
        String sql = "INSERT INTO users (username) VALUES (?)";
        jdbcTemplate.update(sql, username);
    }

    public void delete(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public User getById(Long id) {
        String sql = "SELECT id, username FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("username")), id);
    }

    public List<User> getAll() {
        String sql = "SELECT id, username FROM users";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("username")));
    }
}
