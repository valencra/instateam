package com.valencra.instateam.dao;

import com.valencra.instateam.model.Project;

import java.util.List;

public interface ProjectDao {
  List<Project> findAll();
  Project findById(Long id);
  void save(Project project);
  void delete(Project project);
}