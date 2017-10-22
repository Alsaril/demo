package com.demo.service.impl;

import com.demo.model.User;
import com.demo.service.RoleService;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static final RowMapper<User> USER_MAPPER =
            (res, rowNum) -> new User(res.getInt(1),
                    res.getString(2), res.getString(3), res.getInt(4));
    private JdbcTemplate template;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleService roleService;

    @Autowired
    public UserServiceImpl(JdbcTemplate template, BCryptPasswordEncoder bCryptPasswordEncoder, RoleService roleService) {
        this.template = template;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleService = roleService;
    }

    @Override
    public void create(User user) throws DuplicateKeyException {
        KeyHolder holder = new GeneratedKeyHolder();
        template.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO user(`username`, `password`) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, user.getUsername());
                    ps.setString(2, bCryptPasswordEncoder.encode(user.getPassword()));
                    return ps;
                }, holder);
        if (holder.getKey() != null) {
            user.setId(holder.getKey().intValue());
            roleService.setDefaultRole(user);
        }
    }

    @Override
    public User get(String username) {
        try {
            return template.queryForObject("SELECT `id`, `username`, `password`, `balance` FROM user WHERE `username`=?", USER_MAPPER, username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
