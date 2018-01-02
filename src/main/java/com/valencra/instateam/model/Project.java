package com.valencra.instateam.model;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Project {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "Project name must not be blank")
  @NotEmpty(message = "Project name must not be empty")
  private String name;

  @NotNull(message = "Project description must not be blank")
  @NotEmpty(message = "Project description must not be empty")
  private String description;

  @NotNull(message = "Project status must not be blank")
  private String status;

  @ManyToMany
  @JoinTable(
      name = "Project_Role",
      joinColumns = @JoinColumn(name = "project_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private List<Role> roles = new ArrayList<>();

  @ManyToMany
  @JoinTable(
      name = "Project_Collaborator",
      joinColumns = @JoinColumn(name = "project_id"),
      inverseJoinColumns = @JoinColumn(name = "collaborator_id")
  )
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

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  public List<Collaborator> getCollaborators() {
    return collaborators;
  }

  public void setCollaborators(List<Collaborator> collaborators) {
    this.collaborators = collaborators;
  }
}
