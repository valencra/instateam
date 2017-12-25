package com.valencra.instateam.dao;

import com.valencra.instateam.model.Project;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectDaoImpl extends AbstractDao<Project> implements ProjectDao {
  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public List<Project> findAll() {
    return null;
  }

  @Override
  public Project findById(Long id) {
    return null;
  }
}
