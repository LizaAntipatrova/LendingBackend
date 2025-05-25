package com.lending.lendingbackend.data.repository;

import com.lending.lendingbackend.data.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    public static final String FIND_USER_BY_LOGIN_SQL = """
            SELECT u.id, u.login, u.password, u.registration_date, ur.role_name 
            FROM users u
            JOIN user_roles ur ON u.role_id = ur.id
            WHERE u.login = ?
            """;
    public static final String EXISTS_USER_BY_LOGIN_SQL = "SELECT COUNT(*) FROM users WHERE login = ?";
    private final JdbcTemplate jdbcTemplate;

    public Optional<User> findByLogin(String login) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(FIND_USER_BY_LOGIN_SQL, new BeanPropertyRowMapper<>(User.class), login));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public boolean existsByLogin(String login) {
        String sql = EXISTS_USER_BY_LOGIN_SQL;
        return jdbcTemplate.queryForObject(sql, Integer.class, login) > 0;
    }
}