package com.valencra.instateam.web;

public enum ProjectStatus {
  ACTIVE("Active"),
  ARCHIVED("Archived"),
  NOT_STARTED("Not Started");

  private String name;

  ProjectStatus(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
