package com.valencra.instateam.web.controller;

import com.valencra.instateam.model.Collaborator;
import com.valencra.instateam.service.CollaboratorService;
import com.valencra.instateam.service.RoleService;
import com.valencra.instateam.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import javax.validation.Valid;

@Controller
public class CollaboratorController {
  @Autowired
  private CollaboratorService collaboratorService;

  @Autowired
  private RoleService roleService;

  // Index of collaborators
  @GetMapping("/collaborators")
  public String getCollaborators(Model model) {
    List<Collaborator> collaborators = collaboratorService.findAll();
    model.addAttribute("collaborators", collaborators);
    return "collaborator/index";
  }

  // View collaborator details
  @GetMapping("/collaborators/{id}")
  public String getCollaboratorById(@PathVariable Long id, Model model) {
    Collaborator collaborator = collaboratorService.findById(id);
    model.addAttribute("collaborator", collaborator);
    return "collaborator/details";
  }

  // Add new collaborator form
  @GetMapping("/collaborators/add")
  public String newCollaboratorForm(Model model) {
    // If no collaborator object exists, instantiate a new instance
    if(!model.containsAttribute("collaborator")) {
      model.addAttribute("collaborator", new Collaborator());
    }
    model.addAttribute("roles", roleService.findAll());
    model.addAttribute("action","/collaborators");
    model.addAttribute("method","post");
    model.addAttribute("heading","New Collaborator");
    model.addAttribute("submit","Add");
    return "collaborator/form";
  }

  // Add new collaborator
  @PostMapping(value = "/collaborators")
  public String addNewCollaborator(@Valid Collaborator collaborator, BindingResult result, RedirectAttributes redirectAttributes) {
    // If result has errors, add results and current collaborator object to the model, then redirect to form
    if(result.hasErrors()) {
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.collaborator", result);
      redirectAttributes.addFlashAttribute("collaborator", collaborator);
      return "redirect:/collaborators/add";
    }
    collaboratorService.save(collaborator);
    redirectAttributes.addFlashAttribute("flash", new FlashMessage("Collaborator successfully added!", FlashMessage.Status.SUCCESS));
    return "redirect:/collaborators";
  }

  // Edit existing collaborator form
  @GetMapping("/collaborators/{id}/edit")
  public String editCollaboratorForm(@PathVariable Long id, Model model) {
    // If no collaborator object exists, get collaborator with given id
    if(!model.containsAttribute("collaborator")) {
      model.addAttribute("collaborator", collaboratorService.findById(id));
    }
    model.addAttribute("roles", roleService.findAll());
    model.addAttribute("action", String.format("/collaborators/%d", id));
    model.addAttribute("method","put");
    model.addAttribute("heading","Edit Collaborator");
    model.addAttribute("submit","Update");
    return "collaborator/form";
  }

  // Edit existing collaborator
  @PutMapping(value = "/collaborators/{id}")
  public String editExistingCollaborator(@Valid Collaborator collaborator, BindingResult result, RedirectAttributes redirectAttributes) {
    // If result has errors, add results and current collaborator object to the model, then redirect to form
    if(result.hasErrors()) {
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.collaborator", result);
      redirectAttributes.addFlashAttribute("collaborator", collaborator);
      return "redirect:/collaborator/{id}/edit";
    }
    collaboratorService.save(collaborator);
    redirectAttributes.addFlashAttribute("flash", new FlashMessage("Collaborator successfully edited!", FlashMessage.Status.SUCCESS));
    return "redirect:/collaborators";
  }

  // Delete existing collaborator form
  @GetMapping("/collaborators/{id}/delete")
  public String deleteCollaboratorForm(@PathVariable Long id, Model model) {
    // If no collaborator object exists, get collaborator with given id
    if(!model.containsAttribute("collaborator")) {
      model.addAttribute("collaborator", collaboratorService.findById(id));
    }
    model.addAttribute("roles", roleService.findAll());
    model.addAttribute("action", String.format("/collaborators/%d", id));
    model.addAttribute("method","delete");
    model.addAttribute("heading","Delete Collaborator");
    model.addAttribute("submit","Delete");
    return "collaborator/form";
  }

  // Delete existing collaborator
  @DeleteMapping(value = "/collaborators/{id}")
  public String deleteExistingCollaborator(@Valid Collaborator collaborator, BindingResult result, RedirectAttributes redirectAttributes) {
    // If result has errors, add results and current collaborator object to the model, then redirect to form
    if(result.hasErrors()) {
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.collaborator", result);
      redirectAttributes.addFlashAttribute("collaborator", collaborator);
      return "redirect:/collaborators/{id}/delete";
    }
    collaboratorService.delete(collaborator);
    redirectAttributes.addFlashAttribute("flash", new FlashMessage("Collaborator successfully deleted!", FlashMessage.Status.SUCCESS));
    return "redirect:/collaborators";
  }
}
