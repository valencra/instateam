package com.valencra.instateam.web.controller;

import com.valencra.instateam.model.Project;
import com.valencra.instateam.service.CollaboratorService;
import com.valencra.instateam.service.ProjectService;
import com.valencra.instateam.service.RoleService;
import com.valencra.instateam.web.FlashMessage;
import com.valencra.instateam.web.ProjectStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import javax.validation.Valid;

@Controller
public class ProjectController {
  @Autowired
  private ProjectService projectService;

  @Autowired
  private CollaboratorService collaboratorService;

  @Autowired
  private RoleService roleService;

  // Index of projects
  @GetMapping("/")
  public String getProjects(Model model) {
    List<Project> projects = projectService.findAll();
    model.addAttribute("projects", projects);
    return "index";
  }

  // Add new project form
  @GetMapping("/projects/add")
  public String newProjectForm(Model model) {
    // If no project object exists, instantiate a new instance
    if(!model.containsAttribute("project")) {
      model.addAttribute("project", new Project());
    }
    model.addAttribute("statuses", ProjectStatus.values());
    model.addAttribute("roles", roleService.findAll());
    model.addAttribute("action","/projects");
    model.addAttribute("method","post");
    model.addAttribute("heading","New Project");
    model.addAttribute("submit","Add");
    return "project/form";
  }

  // Add new project
  @PostMapping(value = "/projects")
  public String addNewProject(@Valid Project project, BindingResult result, RedirectAttributes redirectAttributes) {
    // If result has errors, add results and current project object to the model, then redirect to form
    if(result.hasErrors()) {
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.project", result);
      redirectAttributes.addFlashAttribute("project", project);
      return "redirect:/projects/add";
    }
    projectService.save(project);
    redirectAttributes.addFlashAttribute("flash", new FlashMessage("Project successfully added!", FlashMessage.Status.SUCCESS));
    return "redirect:/";
  }

}
