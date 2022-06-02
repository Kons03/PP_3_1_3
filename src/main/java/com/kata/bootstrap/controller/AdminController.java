package com.kata.bootstrap.controller;

import com.kata.bootstrap.model.Role;
import com.kata.bootstrap.model.User;
import com.kata.bootstrap.service.RoleService;
import com.kata.bootstrap.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;


    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String adminPage(Model model, Principal principal) {
        StringBuilder roles = new StringBuilder();
        for (Role role : userService.getUserByUsername(principal.getName()).getRoleSet()) {
            roles.append(role.toString());
            roles.append(" ");
        }

        model.addAttribute("thisUserRoles", roles);
        model.addAttribute("thisUser", userService.getUserByUsername(principal.getName()));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("newUser", new User());
        return "adminPage";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("newUser") User user,
                             @ModelAttribute("roleSet") String[] roles) {
        for (String role : roles) {
            user.getRoleSet().add(roleService.getRole(role));
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute("editUser") User user,
                           @ModelAttribute("roleSet") String[] roles) {
        for (String role : roles) {
            user.getRoleSet().add(roleService.getRole(role));
        }
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
