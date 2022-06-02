package com.kata.bootstrap;

import com.kata.bootstrap.model.Role;
import com.kata.bootstrap.model.User;
import com.kata.bootstrap.service.RoleService;
import com.kata.bootstrap.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitUsers {

    private final RoleService roleService;
    private final UserService userService;


    public InitUsers(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        roleService.addRole(roleAdmin);
        Role roleUser = new Role("ROLE_USER");
        roleService.addRole(roleUser);

        User user = new User("sid_h@sexpistols.com", "sid123", 20, "Sid", "Vicious");
        user.getRoleSet().add(roleUser);

        User admin = new User("serega@foxminded.ua", "serega123",  50, "Sergey", "Nemchinskyi");
        admin.getRoleSet().add(roleAdmin);
        admin.getRoleSet().add(roleUser);

        userService.saveUser(admin);
        userService.saveUser(user);
    }
}
