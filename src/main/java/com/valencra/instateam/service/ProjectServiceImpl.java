package com.valencra.instateam.service;

import com.valencra.instateam.dao.ProjectDao;
import com.valencra.instateam.model.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
  @Autowired
  private ProjectDao projectDao;

  @Override
  public List<Project> findAll() {
    return projectDao.findAll();
  }

  @Override
  public Project findById(Long id) {
    return projectDao.findById(id);
  }

  @Override
  public void save(Project project) {
    projectDao.save(project);
  }

  @Override
  public void delete(Project project) {
    projectDao.delete(project);
  }
}
