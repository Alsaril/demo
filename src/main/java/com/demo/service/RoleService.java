package com.demo.service;

import com.demo.model.Role;
import com.demo.model.User;

import java.util.List;

public interface RoleService {
    List<Role> userRoles(User user);

    void setDefaultRole(User user);
}
