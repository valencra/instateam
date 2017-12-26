package com.valencra.instateam.dao;

import com.valencra.instateam.model.Collaborator;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

@Repository
public class CollaboratorDaoImpl extends AbstractDao<Collaborator> implements CollaboratorDao {
  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public List<Collaborator> findAll() {
    Session session = sessionFactory.openSession();
    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    CriteriaQuery<Collaborator> criteriaQuery = criteriaBuilder.createQuery(Collaborator.class);
    criteriaQuery.from(Collaborator.class);
    List<Collaborator> collaborators = session.createQuery(criteriaQuery).getResultList();
    session.close();
    return collaborators;
  }

  @Override
  public Collaborator findById(Long id) {
    Session session = sessionFactory.openSession();
    Collaborator collaborator = session.get(Collaborator.class, id);
    Hibernate.initialize(collaborator.getProjects());
    session.close();
    return collaborator;
  }
}
