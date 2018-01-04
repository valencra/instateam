package com.valencra.instateam.web.controller;

import com.valencra.instateam.model.Collaborator;
import com.valencra.instateam.model.Project;
import com.valencra.instateam.model.Role;
import com.valencra.instateam.service.CollaboratorService;
import com.valencra.instateam.service.ProjectService;
import com.valencra.instateam.service.RoleService;
import com.valencra.instateam.web.FlashMessage;
import com.valencra.instateam.web.ProjectStatus;
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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

  // View project details
  @GetMapping("/projects/{id}")
  public String getProjectById(@PathVariable Long id, Model model) {
    Project project = projectService.findById(id);
    Map<Role, Collaborator> roleDesignations = getRoleDesignations(project);
    model.addAttribute("project", project);
    model.addAttribute("roleDesignations", roleDesignations);
    return "project/details";
  }

  private Map<Role, Collaborator> getRoleDesignations(Project project) {
    List<Role> roles = project.getRoles();
    List<Collaborator> collaborators = project.getCollaborators();
    Map<Role, Collaborator> roleDesignations = new LinkedHashMap<>();
    roles.forEach(
        role -> roleDesignations.put(role, collaborators.stream()
            .filter(collaborator -> collaborator.getRole().getId().equals(role.getId()))
            .findFirst()
            .orElseGet(() -> {
              Collaborator collaborator = new Collaborator();
              collaborator.setName("Not Designated");
              return collaborator;
            })
        )
    );
    return roleDesignations;
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

  // Edit existing project form
  @GetMapping("/projects/{id}/edit")
  public String editProjectForm(@PathVariable Long id, Model model) {
    // If no project object exists, get project with given id
    if(!model.containsAttribute("project")) {
      model.addAttribute("project", projectService.findById(id));
    }
    model.addAttribute("statuses", ProjectStatus.values());
    model.addAttribute("roles", roleService.findAll());
    model.addAttribute("action", String.format("/projects/%d", id));
    model.addAttribute("method","put");
    model.addAttribute("heading","Edit Project");
    model.addAttribute("submit","Update");
    return "project/form";
  }

  // Edit existing project
  @PutMapping(value = "/projects/{id}")
  public String editExistingProject(@Valid Project project, BindingResult result, RedirectAttributes redirectAttributes) {
    // If result has errors, add results and current project object to the model, then redirect to form
    if(result.hasErrors()) {
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.project", result);
      redirectAttributes.addFlashAttribute("project", project);
      return "redirect:/projects/{id}/edit";
    }
    projectService.save(project);
    redirectAttributes.addFlashAttribute("flash", new FlashMessage("Project successfully edited!", FlashMessage.Status.SUCCESS));
    return "redirect:/projects/{id}";
  }

  @DeleteMapping(value = "/projects/{id}/delete")
  public String deleteExistingProject(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    Project project = projectService.findById(id);
    projectService.delete(project);
    redirectAttributes.addFlashAttribute("flash", new FlashMessage("Project successfully deleted", FlashMessage.Status.SUCCESS));
    return "redirect:/";
  }
}
