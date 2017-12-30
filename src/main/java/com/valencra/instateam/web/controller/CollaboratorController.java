package com.valencra.instateam.web.controller;

import com.valencra.instateam.model.Collaborator;
import com.valencra.instateam.service.CollaboratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CollaboratorController {
  @Autowired
  private CollaboratorService collaboratorService;

  // Index of collaborators
  @GetMapping("/collaborators")
  public String getCollaborators(Model model) {
    List<Collaborator> collaborators = collaboratorService.findAll();
    model.addAttribute("collaborators", collaborators);
    return "collaborator/index";
  }
}
