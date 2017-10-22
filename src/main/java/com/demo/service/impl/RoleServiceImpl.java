package com.demo.service.impl;

import com.demo.model.Role;
import com.demo.model.User;
import com.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private static final RowMapper<Role> ROLE_MAPPER =
            (res, rowNum) -> new Role(res.getInt(1), res.getString(2));
    private JdbcTemplate template;

    @Autowired
    public RoleServiceImpl(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Role> userRoles(User user) {
        return template.query("SELECT `role`.`id`, `role`.`name` FROM `role` JOIN `user_role` ON `role`.`id` = `user_role`.`role_id` WHERE `user_role`.`user_id` = ?",
                ROLE_MAPPER, user.getUsername());
    }

    @Override
    public void setDefaultRole(User user) {
        template.update("INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (?, ?)", user.getId(), 1);
    }
}
