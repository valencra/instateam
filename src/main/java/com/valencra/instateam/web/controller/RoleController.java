package com.valencra.instateam.web.controller;

import com.valencra.instateam.model.Role;
import com.valencra.instateam.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RoleController {
  @Autowired
  private RoleService roleService;

  @GetMapping("/roles")
  public String listRoles(Model model) {
    List<Role> roles = roleService.findAll();
    model.addAttribute("roles", roles);
    return "role/roles";
  }
}
