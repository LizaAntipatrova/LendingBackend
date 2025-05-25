package com.lending.lendingbackend.data.repository.mapper;

import com.lending.lendingbackend.data.entity.Client;
import com.lending.lendingbackend.data.entity.User;
import com.lending.lendingbackend.data.entity.UserRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientWithUserRowMapper implements RowMapper<Client> {
        @Override
        public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("user_id"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setRegistrationDate(rs.getTimestamp("registration_date").toLocalDateTime());
            user.setRole(UserRole.fromRussianName(rs.getString("role_name")));

            Client client = new Client();
            client.setId(rs.getLong("id"));
            client.setAccountNumber(rs.getString("account_number"));
            client.setLastName(rs.getString("last_name"));
            client.setFirstName(rs.getString("first_name"));
            client.setMiddleName(rs.getString("middle_name"));
            client.setPassportSeries(rs.getString("passport_series"));
            client.setPassportNumber(rs.getString("passport_number"));
            client.setBirthDate(rs.getDate("birth_date").toLocalDate());
            client.setAddress(rs.getString("address"));
            client.setPhone(rs.getString("phone"));
            client.setEmail(rs.getString("email"));
            client.setUser(user);

            return client;
        }
    }