package com.kata.bootstrap.service;

import com.kata.bootstrap.model.Role;

import java.util.List;

public interface RoleService {

    Role getRole(String name);

    void addRole(Role role);

    List<Role> getRoles();
}
