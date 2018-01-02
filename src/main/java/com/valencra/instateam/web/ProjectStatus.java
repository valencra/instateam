package com.valencra.instateam.web;

public enum ProjectStatus {
  ACTIVE("Active"),
  ARCHIVED("Archived"),
  NOT_STARTED("Not Started")

  private String status;

  ProjectStatus(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status
  }
}
