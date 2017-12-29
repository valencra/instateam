package com.valencra.instateam.web.controller;

import com.valencra.instateam.model.Role;
import com.valencra.instateam.service.RoleService;
import com.valencra.instateam.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import javax.validation.Valid;

@Controller
public class RoleController {
  @Autowired
  private RoleService roleService;

  // Index of roles
  @GetMapping("/roles")
  public String getRoles(Model model) {
    List<Role> roles = roleService.findAll();
    model.addAttribute("roles", roles);
    return "role/index";
  }

  // Add new role form
  @GetMapping("/roles/add")
  public String newRoleForm(Model model) {
    // If no role object exists, instantiate a new instance
    if(!model.containsAttribute("role")) {
      model.addAttribute("role", new Role());
    }
    model.addAttribute("action","/roles");
    model.addAttribute("heading","New Role");
    model.addAttribute("submit","Add");
    return "role/form";
  }

  // Add new role
  @PostMapping(value = "/roles")
  public String addNewRole(@Valid Role role, BindingResult result, RedirectAttributes redirectAttributes) {
    // If result has errors, add results and current role object to the model, then redirect to form
    if(result.hasErrors()) {
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.role", result);
      redirectAttributes.addFlashAttribute("role", role);
      return "redirect:/roles/add";
    }
    roleService.save(role);
    redirectAttributes.addFlashAttribute("flash", new FlashMessage("Role successfully added!", FlashMessage.Status.SUCCESS));
    return "redirect:/roles";
  }

  // Edit existing role form
  @GetMapping("/roles/{id}/edit")
  public String editRoleForm(@PathVariable Long id, Model model) {
    // If no role object exists, get role with given id
    if(!model.containsAttribute("role")) {
      model.addAttribute("role", roleService.findById(id));
    }
    model.addAttribute("action", String.format("/roles/%d", id));
    model.addAttribute("heading","Edit Role");
    model.addAttribute("submit","Update");
    return "role/form";
  }

  // Edit existing role
  @PutMapping(value = "/roles/{id}")
  public String editExistingRole(@Valid Role role, BindingResult result, RedirectAttributes redirectAttributes) {
    // If result has errors, add results and current role object to the model, then redirect to form
    if(result.hasErrors()) {
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.role", result);
      redirectAttributes.addFlashAttribute("role", role);
      return "redirect:/roles/{id}/edit";
    }
    roleService.save(role);
    redirectAttributes.addFlashAttribute("flash", new FlashMessage("Role successfully edited!", FlashMessage.Status.SUCCESS));
    return "redirect:/roles";
  }
}
