package com.valencra.instateam.dao;

import com.valencra.instateam.model.Project;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

@Repository
public class ProjectDaoImpl extends AbstractDao<Project> implements ProjectDao {
  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public List<Project> findAll() {
    Session session = sessionFactory.openSession();
    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    CriteriaQuery<Project> criteriaQuery = criteriaBuilder.createQuery(Project.class);
    criteriaQuery.from(Project.class);
    List<Project> projects = session.createQuery(criteriaQuery).getResultList();
    session.close();
    return projects;
  }

  @Override
  public Project findById(Long id) {
    Session session = sessionFactory.openSession();
    Project project = session.get(Project.class, id);
    Hibernate.initialize(project.getRoles());
    Hibernate.initialize(project.getCollaborators());
    session.close();
    return project;
  }
}
