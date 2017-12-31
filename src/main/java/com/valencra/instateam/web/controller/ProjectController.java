package com.valencra.instateam.web.controller;

import com.valencra.instateam.model.Project;
import com.valencra.instateam.service.CollaboratorService;
import com.valencra.instateam.service.ProjectService;
import com.valencra.instateam.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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

}
