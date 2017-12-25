package com.valencra.instateam.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Project {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String name;

  @NotNull
  private String description;

  @NotNull
  private String status;

  @ManyToMany(mappedBy = "projects")
  private List<Role> rolesNeeded = new ArrayList<>();

  @ManyToMany(mappedBy = "projects")
  private List<Collaborator> collaborators = new ArrayList<>();

  public Project() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<Role> getRolesNeeded() {
    return rolesNeeded;
  }

  public void setRolesNeeded(List<Role> rolesNeeded) {
    this.rolesNeeded = rolesNeeded;
  }

  public List<Collaborator> getCollaborators() {
    return collaborators;
  }

  public void setCollaborators(List<Collaborator> collaborators) {
    this.collaborators = collaborators;
  }
}
