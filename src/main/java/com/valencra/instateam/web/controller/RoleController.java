package com.valencra.instateam.web.controller;

import com.valencra.instateam.model.Role;
import com.valencra.instateam.service.RoleService;
import com.valencra.instateam.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import javax.validation.Valid;

@Controller
public class RoleController {
  @Autowired
  private RoleService roleService;

  @GetMapping("/roles")
  public String listRoles(Model model) {
    List<Role> roles = roleService.findAll();
    model.addAttribute("roles", roles);
    if(!model.containsAttribute("role")) {
      model.addAttribute("role", new Role());
    }
    return "role/roles";
  }

  @PostMapping(value = "/roles")
  public String addRole(@Valid Role role, BindingResult result, RedirectAttributes redirectAttributes) {
    if(result.hasErrors()) {
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category",result);
      redirectAttributes.addFlashAttribute("role", role);
      return "redirect:/roles";
    }
    roleService.save(role);
    redirectAttributes.addFlashAttribute("flash",new FlashMessage("Role successfully added!", FlashMessage.Status.SUCCESS));
    return "redirect:/roles";
  }
}
