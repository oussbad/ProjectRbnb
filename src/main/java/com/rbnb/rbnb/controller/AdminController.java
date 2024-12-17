package com.rbnb.rbnb.controller;

import com.rbnb.rbnb.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", adminService.getAllUsers());
        return "user-list";
    }

    @PostMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/properties")
    public String listProperties(Model model) {
        model.addAttribute("properties", adminService.getAllProperties());
        return "admin-property-list";
    }

    @PostMapping("/properties/{id}/delete")
    public String deleteProperty(@PathVariable Long id) {
        adminService.deleteProperty(id);
        return "redirect:/admin/properties";
    }
}

